package com.iwise.net;

import java.io.Serializable;

/**
 * ��Ӧ��
 * 
 * @File: NetWorkResponse.java
 * @Package com.iwise.net
 * @Description:
 * @author Harvey
 * @date 2014-7-14 ����2:38:21
 * 
 */
public class NetWorkResponse implements Serializable
{

	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ƽ̨����汾��
	 */
	private String sv = "";

	/**
	 * ��������
	 */
	private String resultCode = "";

	/**
	 * �Ƿ�ɹ�
	 */
	private int isOk = 0;

	/**
	 * �û�ID
	 */
	private String userId = "";

	/**
	 * �Ƿ��Զ���½
	 */
	private int isUser = -1;

	/**
	 * �Ƿ���Ҫ�����ͻ���
	 */
	private int isUpdate = 0;

	/**
	 * �ͻ���������ַ
	 */
	private String updateUrl = "";

	/**
	 * ������url
	 */
	private int preurl = 0;

	/**
	 * ��Ϣ
	 */
	private String info = "";

	/**
	 * �ֻ����ʶ����
	 */
	private String imei = "";

	/**
	 * �ͻ�������
	 */
	private String versionDesc = "";

	/**
	 * �ͻ��˴�С
	 */
	private String fileSize = "";

	/**
	 * �ͻ��˰汾��
	 */
	private String versionCode = "";

	public String getSv()
	{
		return sv;
	}

	public void setSv(String sv)
	{
		this.sv = sv;
	}

	public String getResultCode()
	{
		return resultCode;
	}

	public void setResultCode(String resultCode)
	{
		this.resultCode = resultCode;
	}

	public int getIsOk()
	{
		return isOk;
	}

	public void setIsOk(int isOk)
	{
		this.isOk = isOk;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public int getIsUser()
	{
		return isUser;
	}

	public void setIsUser(int isUser)
	{
		this.isUser = isUser;
	}

	public int getIsUpdate()
	{
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate)
	{
		this.isUpdate = isUpdate;
	}

	public String getUpdateUrl()
	{
		return updateUrl;
	}

	public void setUpdateUrl(String updateUrl)
	{
		this.updateUrl = updateUrl;
	}

	public int getPreurl()
	{
		return preurl;
	}

	public void setPreurl(int preurl)
	{
		this.preurl = preurl;
	}

	public String getInfo()
	{
		return info;
	}

	public void setInfo(String info)
	{
		this.info = info;
	}

	public String getImei()
	{
		return imei;
	}

	public void setImei(String imei)
	{
		this.imei = imei;
	}

	public String getVersionDesc()
	{
		return versionDesc;
	}

	public void setVersionDesc(String versionDesc)
	{
		this.versionDesc = versionDesc;
	}

	public String getFileSize()
	{
		return fileSize;
	}

	public void setFileSize(String fileSize)
	{
		this.fileSize = fileSize;
	}

	public String getVersionCode()
	{
		return versionCode;
	}

	public void setVersionCode(String versionCode)
	{
		this.versionCode = versionCode;
	}

}
