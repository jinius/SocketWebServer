package com.next.webserver;


import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.text.ParseException;

import com.next.webserver.http.HttpRequest;
import com.next.webserver.http.HttpResponse;

public class ServerThread extends Thread
{
	private Socket		socket		= null;
	private String		docRoot		= null;

	public ServerThread( Socket socket, ServerConfig config ) throws IOException
	{
		this.socket		= socket;
		this.docRoot	= config.getDocRoot();
	}

	@Override
	public void run()
	{
		try
		{
			try
			{
				HttpRequest		request		= new HttpRequest( socket.getInputStream() );
				HttpResponse	response	= processRequest( request );
				response.send( new DataOutputStream( socket.getOutputStream() ) );
			}
			catch ( ParseException e )
			{
				e.printStackTrace();
			}

			socket.close();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	protected HttpResponse processRequest( HttpRequest request )
	{
		System.out.println( "Request: "		+ request.getMethod() );
		System.out.println( "RequestURI: "	+ request.getRequestURI() );
		
		HttpResponse	response	= new HttpResponse( request.getHttpVersion() );

		switch ( request.getMethod() )
		{
		case GET:
			return processGetRequest( request, response );
		case POST:
			return processPostRequest( request, response );
		default:
			return null;
		}
	}
	
	protected HttpResponse processGetRequest( HttpRequest request, HttpResponse response )
	{
		String	filePath	= request.getRequestURI();
		if ( filePath.equals( "/" ) )
			filePath = "/index.html";

		File	file		= new File( docRoot + filePath );
		if ( file.isFile() )
		{
			if ( filePath.endsWith( ".html" ) )
				response.setContentType( "text/html;charset=utf-8" );
			else if ( filePath.endsWith( ".jpg" ) )
				response.setContentType( "image/jpeg" );
			else if ( filePath.endsWith( ".png" ) )
				response.setContentType( "image/png" );

			response.setFile( file );
			response.setStatusCode( 200 );
		}
		else
			response.setStatusCode( 404 );
		
		return response;
	}
	
	protected HttpResponse processPostRequest( HttpRequest request, HttpResponse response )
	{
		response.setData( request.getMessageBody().getBytes() );
		
		return processGetRequest( request, response );
	}
}
