/**  
 *
 * @File: ResponseListener.java
 * @Package com.iwise.net
 * @Description: TODO(��һ�仰�������ļ���ʲô)
 * @author Harvey  
 * @date 2014-7-14 ����2:34:30
 * 
 */
package com.iwise.net;

public abstract class ResponseListener
{
	/**
	 * ��Ӧ�ɹ�
	 * 
	 * @param result
	 */
	public abstract void onResponseSuccess(NetWorkResponse response);

	/**
	 * ��Ӧʧ��
	 */
	public void onResponseFail()
	{

	}
}
