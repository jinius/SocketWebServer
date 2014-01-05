package com.next.webserver.http;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.next.webserver.http.header.HttpCacheControlHeader;
import com.next.webserver.http.header.HttpContentLengthHeader;
import com.next.webserver.http.header.HttpIfModifiedSinceHeader;

public class HttpRequest
{
	private static final Pattern REQUEST_LINE = Pattern.compile( "(\\S+) (\\S+) (\\S+)" );

	private HttpMethod	method				= null;
	private String		requestURI			= null;
	private String		httpVersion			= null;
	private HttpCache	httpCache			= new HttpCache();
	private String		messageBody			= null;
	private int			contentLength		= 0;

	public HttpMethod	getMethod()			{ return method; }
	public String		getRequestURI()		{ return requestURI; }
	public String		getHttpVersion()	{ return httpVersion; }
	public HttpCache	getHttpCache()		{ return httpCache; }
	public String		getMessageBody()	{ return messageBody; }
	
	public HttpRequest( InputStream is ) throws IOException, ParseException
	{
		BufferedReader br = new BufferedReader( new InputStreamReader( is ) );
		String header = br.readLine();
		if ( header == null )
			throw new ParseException( "No Header : Connection Closed?", 0 );

		requestLine( header );

		while(true)
		{
			header = br.readLine();

			if( header == null || header.equals("") )
				break;

			if ( header.startsWith( "Content-Length:" ) )
			{
				contentLength = HttpContentLengthHeader.parseHeader( header );
			}
			httpCache.setIfModifiedSince( HttpIfModifiedSinceHeader.parseHeader( header ) );
			httpCache.setCacheControl( HttpCacheControlHeader.parseHeader( header ) );
		}

		if( getMethod() == HttpMethod.POST )
			readPostData( br );
	}

	public void readPostData( BufferedReader br ) throws IOException
	{
		char[]		buffer	= new char[contentLength];
		int			nRead	= br.read( buffer, 0, contentLength );

		if ( nRead < contentLength )
			throw new IOException( "PostData read error : contentLength(" + contentLength + "), actualRead(" + nRead + ")" );
		
		messageBody = new String( buffer );
	}

	private void requestLine( String line ) throws ParseException
	{
		Matcher match = REQUEST_LINE.matcher( line );
		if( ! match.matches() )
			throw new ParseException( "no http request method", 0 );

		String methodName = match.group(1);
		for( HttpMethod method : HttpMethod.values() )
		{
			if( methodName.equals( method.toString() ) )
				this.method = method;
		}

		if( method != null )
		{
			requestURI	= match.group(2);
			httpVersion	= match.group(3);
		}
		
		if ( requestURI.equals("/") )
			requestURI = "/index.html";
	}
}
