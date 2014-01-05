package com.next.webserver.http;


import java.util.Date;

public class HttpCache
{
	private Date				ifModifiedSince = null;
	private HttpCacheControl	cacheControl = null;

	public Date getIfModifiedSince()
	{
		return ifModifiedSince;
	}

	public void setIfModifiedSince(Date ifModifiedSince)
	{
		if( ifModifiedSince != null )
			this.ifModifiedSince = ifModifiedSince;
	}

	public HttpCacheControl getCacheControl()
	{
		return cacheControl;
	}

	public void setCacheControl(HttpCacheControl cacheControl)
	{
		if( cacheControl != null )
			this.cacheControl = cacheControl;
	}
}
