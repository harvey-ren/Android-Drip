package com.iwise.net;

import java.util.HashMap;

/**
 * ���������
 * 
 * @ClassName: RequestManager
 * @Description:�����������
 * @author Harvey
 * @date 2014-7-14 ����1:10:09
 * 
 */
public class RequestManager
{

	private static RequestManager requestManager = null;

	/**
	 * ˽�еĹ��췽��
	 */
	private RequestManager()
	{
	}

	public static RequestManager getInstance()
	{
		if (requestManager == null)
		{
			requestManager = new RequestManager();
		}
		return requestManager;
	}

	/**
	 * ��ʼ���ӿ�
	 * 
	 * @Title: getInitRequest
	 * @Description:
	 * @param @param phonenumber �ֻ�����
	 * @param @param password ����
	 * @param @param userid �û�id
	 * @param @param appversioncode
	 * @param @return �趨�ļ�
	 * @return Request ��������
	 * @throws
	 */
	public Request getInitRequest(String phonenumber, String password, String userid, int appversioncode)
	{
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("mobile", phonenumber);
		params.put("passwd", password);
		params.put("userid", userid);
		params.put("vercode", appversioncode + "");
		Request request = new Request(RequestUrl.INITINFO, params);
		return request;
	}

}
