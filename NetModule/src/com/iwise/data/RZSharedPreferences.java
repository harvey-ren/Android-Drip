package com.iwise.data;

import android.content.Context;
import android.content.SharedPreferences.Editor;

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

	/**
	 * ˽�й��췽��
	 * 
	 * Title:
	 * 
	 * Description:
	 * 
	 */
	private RZSharedPreferences()
	{
	}

	/**
	 * ����
	 * 
	 * @Title: getInstance
	 * @Description: TODO
	 * @param @return �趨�ļ�
	 * @return RZSharedPreferences ��������
	 * @throws
	 */
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
		return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(KEY_USERID, "2");
	}

	/**
	 * �����û�id��ֵ
	 * 
	 * @Title: putUserId
	 * @Description:
	 * @param @param context
	 * @param @param value �����ֵ
	 * @return void ��������
	 * @throws
	 */
	public static void setUserId(Context context, String value)
	{
		Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
		editor.putString(KEY_USERID, value);
		editor.commit();
	}

	/**
	 * ��ȡ�ֻ�����
	 * 
	 * @param context
	 * @return
	 */
	public static String getPhoneNumber(Context context)
	{
		return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(KEY_PHONE_NUMBER, "13071108110");
	}

	/**
	 * �����ֻ�����
	 * 
	 * @Title: setPhoneNumber
	 * @Description:
	 * @param @param context
	 * @param @param value �趨�ļ�
	 * @return void ��������
	 * @throws
	 */
	public static void setPhoneNumber(Context context, String value)
	{
		Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
		editor.putString(KEY_PHONE_NUMBER, value);
		editor.commit();
	}

	/**
	 * ��ȡ����
	 * 
	 * @param context
	 * @return
	 */
	public static String getPassWord(Context context)
	{
		return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(KEY_PASSWORD, "123456");
	}

	/**
	 * ��������
	 * 
	 * @Title: setPassWord
	 * @Description:
	 * @param @param context
	 * @param @param value �趨�ļ�
	 * @return void ��������
	 * @throws
	 */
	public static void setPassWord(Context context, String value)
	{
		Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
		editor.putString(KEY_PASSWORD, value);
		editor.commit();
	}

}
