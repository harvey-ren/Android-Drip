package com.iwise.net;

/**
 * ���󷽷�
 * 
 * @author Harvey
 * 
 */
public enum RequestMethod
{
	POST("POST"), GET("GET");

	private String method;

	private RequestMethod(String method)
	{
		this.method = method;
	}

	public String getRequestMethod()
	{
		return method;
	}

}
