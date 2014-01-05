package com.next.webserver.http;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import org.junit.Test;

public class HttpRequestTest
{
	private HttpRequest		request;
	private InputStream		is;

	@Test
	public void testGetMethod()
	{
		try
		{
			is = new ByteArrayInputStream( "GET / HTTP/1.1".getBytes("UTF-8") );
			request = new HttpRequest( is );
			assertEquals( HttpMethod.GET, request.getMethod() );
			assertEquals( "/", request.getRequestURI() );
			assertEquals( "HTTP/1.1", request.getHttpVersion() );
		}
		catch (IOException e)
		{
			fail( "IOException: " + e.getMessage() );
		}
		catch (ParseException e)
		{
			fail( "ParseException: " + e.getMessage() );
		}
	}

	@Test
	public void testPostMethod()
	{
		//fail("Not yet implemented");
	}

}
