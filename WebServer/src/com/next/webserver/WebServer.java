package com.next.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer
{
	private static final ServerConfig config = new ServerConfig();

	public static void main(String[] args) throws IOException
	{
		if ( args.length < 1 )
			config.setDocRoot( "../test03" );
		else
			config.setDocRoot( args[0] );

		ServerSocket serverSocket = new ServerSocket(8080);

		try
		{
			while (true)
			{
				Socket socket = serverSocket.accept();

				ServerThread serverThread = new ServerThread( socket, config );
				serverThread.start();
			}
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			serverSocket.close();
		}
	}

}
