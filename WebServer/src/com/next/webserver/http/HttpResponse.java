package com.next.webserver.http;


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class HttpResponse
{
	private static final String CRLF = "\r\n";

	private String	httpVersion		= null;
	private int		statusCode		= 500;
	private String	reasonPhrase	= null;
	private String	contentType		= null;
	private File	file			= null;
	private byte[]	data			= null;
	
	public HttpResponse( String httpVersion )
	{
		this.httpVersion = httpVersion;
	}

	public int getStatusCode()
	{
		return statusCode;
	}

	public void setStatusCode( int statusCode )
	{
		this.statusCode = statusCode;
	}

	public String getReasonPhrase()
	{
		if( reasonPhrase != null )
			return reasonPhrase;
		
		switch( statusCode )
		{
		case 200:
			return "OK";
		case 304:
			return "Not Modified";
		case 400:
			return "Bad Request";
		case 404:
			return "Not Found";
		}

		return "Internal Service Error";
	}
	
	public void setContentType( String contentType )
	{
		this.contentType = contentType;
	}

	public void setReasonPhrase( String reasonPhrase )
	{
		this.reasonPhrase = reasonPhrase;
	}

	public void setFile( File file )
	{
		this.file = file;
	}

	public void setData( byte[] data )
	{
		this.data = data;
	}

	public void send( DataOutputStream	dos ) throws IOException
	{
		dos.writeBytes( httpVersion + " " + statusCode + " " + getReasonPhrase() + CRLF );
		
		if ( contentType != null )
		{
			dos.writeBytes( "Content-Type: " + contentType + CRLF );
		}

		long contentLength = 0;

		if ( data != null )
			contentLength += data.length;

		if ( file != null )
			contentLength += file.length();
		
		dos.writeBytes( "Content-Length: " + contentLength + CRLF );

		// End of Http Header
		dos.writeBytes( CRLF );

		if ( data != null )
		{
			dos.write( data, 0, data.length );
		}

		if ( file != null )
		{
			FileInputStream	fis		= new FileInputStream( file );
			byte[]			buffer	= new byte[4096];
			int				nByte;

			try
			{
				while( ( nByte = fis.read( buffer ) ) > 0 )
				{
					dos.write( buffer, 0, nByte );
				}
			}
			finally
			{
				fis.close();
			}
		}
		else
		{
			dos.writeBytes( CRLF );
		}

		dos.flush();
	}

}
