package com.next.webserver.http.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class HttpDateFormat
{
	private final DateFormat dateFormat;

	public HttpDateFormat()
	{
		dateFormat = new SimpleDateFormat( "EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH );
		dateFormat.setTimeZone( TimeZone.getTimeZone( "GMT" ) );
	}

	public Date parse( String dateString ) throws ParseException
	{
		return dateFormat.parse( dateString );
	}
	
	public String format( Date date )
	{
		return dateFormat.format( date );
	}
}
