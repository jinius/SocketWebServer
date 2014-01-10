package com.next.webserver.http.util;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

public class HttpDateFormatTest
{
	private final static String dateString = "Mon, 2 Dec 2013 00:03:46 GMT";

	@Test
	public void testParseAndFormat()
	{
		HttpDateFormat dateFormat = new HttpDateFormat();

		try
		{
			Date date = dateFormat.parse( dateString );
			assertEquals( dateString, dateFormat.format( date ) );
		}
		catch (ParseException e)
		{
			fail("Not yet implemented");
		}
	}
}
