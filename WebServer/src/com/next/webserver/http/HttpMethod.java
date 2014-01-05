package com.next.webserver.http;

public enum HttpMethod
{
	OPTIONS	("OPTIONS"),
	GET		("GET"),
	HEAD	("HEAD"),
	POST	("POST"),
	PUT		("PUT"),
	DELETE	("DELETE"),
	TRACE	("TRACE"),
	CONNECT	("CONNECT");

	private String name;

	HttpMethod( String name )
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return name;
	}
}
