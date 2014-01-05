package com.next.webserver.http.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HttpDateParser
{
	private static final DateFormat dateFormat = new SimpleDateFormat( "EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH );

	public static Date parse(String dateString ) throws ParseException
	{
		return dateFormat.parse( dateString );
	}
}
