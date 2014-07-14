package com.iwise.data;

import android.content.Context;

/**
 * SharedPreferences��
 * 
 * @ClassName: RZSharedPreferences
 * @Description:SharedPreferences��
 * @author Harvey
 * @date 2014-7-14 ����11:12:32
 * 
 */
public class RZSharedPreferences
{

	/**
	 * RZSharedPreferencesʵ��
	 */
	private static RZSharedPreferences instance = null;

	/**
	 * �ļ�����
	 */
	private static final String FILE_NAME = "network_module";

	/**
	 * �û�id��Keyֵ
	 */
	private static final String KEY_USERID = "userid";

	/**
	 * �û��ֻ������Keyֵ
	 */
	private static final String KEY_PHONE_NUMBER = "phone_number";

	/**
	 * �����Keyֵ
	 */
	private static final String KEY_PASSWORD = "password";

	// ˽�й��췽��
	private RZSharedPreferences()
	{
	}

	// ����
	public static RZSharedPreferences getInstance()
	{
		if (instance == null)
		{
			instance = new RZSharedPreferences();
		}
		return instance;
	}

	/**
	 * ��ȡ�û���id
	 * 
	 * @param context
	 * @return
	 */
	public static String getUserId(Context context)
	{
		return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(KEY_USERID, "");
	}

	/**
	 * ��ȡ�ֻ�����
	 * 
	 * @param context
	 * @return
	 */
	public static String getPhoneNumber(Context context)
	{
		return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(KEY_PHONE_NUMBER, "");
	}

	/**
	 * ��ȡ����
	 * 
	 * @param context
	 * @return
	 */
	public static String getPassWord(Context context)
	{
		return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(KEY_PASSWORD, "");
	}

}
