package com.next.webserver.http;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.junit.Test;

public class HttpResponseTest
{
	private static final String	CRLF		= "\r\n";
	private HttpResponse		response	= null;
	private DataOutputStream	dos			= null;

	@Test
	public void testHttpResponse()
	{
		response = new HttpResponse( "HTTP/1.1" );
		response.setStatusCode( 200 );

		OutputStream os = new ByteArrayOutputStream(4096);
		dos = new DataOutputStream( os );

		try
		{
			response.send( dos );
			System.out.println( os.toString() );
			assertEquals( "HTTP/1.1 200 OK" + CRLF + CRLF, os.toString() );
		}
		catch (IOException e)
		{
			fail( "IOException: " + e.getMessage() );
		}
	}

}
