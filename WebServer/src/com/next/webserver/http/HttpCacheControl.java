package com.next.webserver.http;


public class HttpCacheControl
{
	private String mode;
	private String param;

	public String getMode()
	{
		return mode;
	}

	public void setMode(String mode)
	{
		this.mode = mode;
	}

	public String getParam()
	{
		return param;
	}

	public void setParam(String param)
	{
		this.param = param;
	}

	public HttpCacheControl(String mode, String param)
	{
		this.mode	= mode;
		this.param	= param;
	}

}
