package com.next.webserver.http.header;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Test;

import com.next.webserver.http.header.HttpIfModifiedSinceHeader;

public class HttpIfModifiedSinceHeaderTest
{
	private static final String IF_MODIFIED_SINCE = "If-Modified-Since: Sat, 29 Oct 1994 19:43:31 GMT";
	private static final DateFormat dateFormat = new SimpleDateFormat( "EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH );

	@Test
	public void testParseHeader()
	{
		try
		{
			Date date = HttpIfModifiedSinceHeader.parseHeader( IF_MODIFIED_SINCE );
			dateFormat.setTimeZone( TimeZone.getTimeZone("GMT") );
			assertEquals( "If-Modified-Since: " + dateFormat.format( date ), IF_MODIFIED_SINCE );
		}
		catch (ParseException e)
		{
			fail( e.getMessage() );
		}
	}

}
