package com.next.webserver.http.header;

import static org.junit.Assert.*;

import org.junit.Test;

import com.next.webserver.http.HttpCacheControl;

public class HttpCacheControlHeaderTest
{
	private static final String CACHE_CONTROL_NO_CACHE			= "Cache-Control: no-cache";
	private static final String CACHE_CONTROL_NO_STORE			= "Cache-Control: no-store";
	private static final String CACHE_CONTROL_MAX_AGE			= "Cache-Control: max-age=0";
	private static final String CACHE_CONTROL_MAX_STALE			= "Cache-Control: max-stale=0";
	private static final String CACHE_CONTROL_MIN_FRESH			= "Cache-Control: min-fresh=0";
	private static final String CACHE_CONTROL_NO_TRANSFORM		= "Cache-Control: no-transform";
	private static final String CACHE_CONTROL_ONLY_IF_CACHED	= "Cache-Control: only-if-cached";

	@Test
	public void testParseHeader()
	{
		HttpCacheControl cacheControl;

		cacheControl = HttpCacheControlHeader.parseHeader( CACHE_CONTROL_NO_CACHE );
		assertEquals( cacheControl.getMode(), "no-cache" );
		assertEquals( cacheControl.getParam(), "" );

		cacheControl = HttpCacheControlHeader.parseHeader( CACHE_CONTROL_NO_STORE );
		assertEquals( cacheControl.getMode(), "no-store" );
		assertEquals( cacheControl.getParam(), "" );

		cacheControl = HttpCacheControlHeader.parseHeader( CACHE_CONTROL_MAX_AGE );
		assertEquals( cacheControl.getMode(), "max-age" );
		assertEquals( cacheControl.getParam(), "0" );

		cacheControl = HttpCacheControlHeader.parseHeader( CACHE_CONTROL_MAX_STALE );
		assertEquals( cacheControl.getMode(), "max-stale" );
		assertEquals( cacheControl.getParam(), "0" );

		cacheControl = HttpCacheControlHeader.parseHeader( CACHE_CONTROL_MIN_FRESH );
		assertEquals( cacheControl.getMode(), "min-fresh" );
		assertEquals( cacheControl.getParam(), "0" );

		cacheControl = HttpCacheControlHeader.parseHeader( CACHE_CONTROL_NO_TRANSFORM );
		assertEquals( cacheControl.getMode(), "no-transform" );
		assertEquals( cacheControl.getParam(), "" );

		cacheControl = HttpCacheControlHeader.parseHeader( CACHE_CONTROL_ONLY_IF_CACHED );
		assertEquals( cacheControl.getMode(), "only-if-cached" );
		assertEquals( cacheControl.getParam(), "" );
	}

}
