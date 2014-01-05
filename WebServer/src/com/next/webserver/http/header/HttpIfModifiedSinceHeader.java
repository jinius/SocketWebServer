package com.next.webserver.http.header;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.next.webserver.http.util.HttpDateParser;

public class HttpIfModifiedSinceHeader
{
	private static final Pattern If_MODIFIED_SINCE =
		Pattern.compile( "If-Modified-Since" + ":" + "\\s?" + "(.+)" );

	public static Date parseHeader( String header ) throws ParseException
	{
		Matcher matcher = If_MODIFIED_SINCE.matcher( header );

		if( matcher.matches() )
			return HttpDateParser.parse( matcher.group(1) );

		return null;
	}
}
