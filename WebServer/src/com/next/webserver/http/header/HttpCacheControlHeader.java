package com.next.webserver.http.header;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.next.webserver.http.HttpCacheControl;

public class HttpCacheControlHeader
{
	private static final Pattern CACHE_CONTROL =
		Pattern.compile(
			"Cache-Control" + ":" + "\\s?" + "("
							+ "no-cache()" + "|"
							+ "no-store()" + "|"
							+ "max-age=(.+)" + "|"
							+ "max-stale=(.*)" + "|"
							+ "min-fresh=(.+)" + "|"
							+ "no-transform()" + "|"
							+ "only-if-cached()" + "|"
							+ ")" );

	public static HttpCacheControl parseHeader( String header )
	{
		Matcher matcher = CACHE_CONTROL.matcher( header );

		if( matcher.matches() )
		{
			int groupCount = matcher.groupCount();

			for( int group = 2; group <= groupCount; ++group )
			{
				String param = matcher.group( group );

				if( param == null )
					continue;

				switch( group )
				{
				case 2:
					return new HttpCacheControl("no-cache", param);
				case 3:
					return new HttpCacheControl("no-store", param);
				case 4:
					return new HttpCacheControl("max-age", param);
				case 5:
					return new HttpCacheControl("max-stale", param);
				case 6:
					return new HttpCacheControl("min-fresh", param);
				case 7:
					return new HttpCacheControl("no-transform", param);
				case 8:
					return new HttpCacheControl("only-if-cached", param);
				}
			}
		}

		return null;
	}
}
