package com.next.webserver.http.header;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpContentLengthHeader
{
	public static final String FIELD_NAME = "Content-Length";

	private static final Pattern CONTENT_LENGTH =
			Pattern.compile( FIELD_NAME + ":" + "\\s?" + "([0-9]+)" );

	public static int parseHeader( String header ) throws ParseException
	{
		Matcher matcher = CONTENT_LENGTH.matcher( header );

		if( matcher.matches() )
		{
			String length = matcher.group( 1 );
			return Integer.parseInt( length );
		}

		throw new ParseException( header, 0 );
	}
}
