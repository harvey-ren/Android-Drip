package com.iwise.net;

/**
 * ö�� ���󷽷�
 * 
 * @ClassName: RequestMethod
 * @Description:
 * @author Harvey
 * @date 2014-7-14 ����1:13:33
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
