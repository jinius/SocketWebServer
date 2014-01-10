package com.next.webserver.http.header;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.next.webserver.http.util.HttpDateFormat;

public class HttpIfModifiedSinceHeader
{
	public static final String FIELD_NAME = "If-Modified-Since";

	private static final Pattern If_MODIFIED_SINCE =
		Pattern.compile( FIELD_NAME + ":" + "\\s?" + "(.+)" );

	public static Date parseHeader( String header ) throws ParseException
	{
		Matcher matcher = If_MODIFIED_SINCE.matcher( header );

		if( matcher.matches() )
			return new HttpDateFormat().parse( matcher.group(1) );

		return null;
	}
}
