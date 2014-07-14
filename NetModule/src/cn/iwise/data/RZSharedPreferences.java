package cn.iwise.data;

import android.content.Context;

public class RZSharedPreferences
{

	private static RZSharedPreferences instance = null;

	private RZSharedPreferences()
	{
	}

	public static RZSharedPreferences getInstance()
	{
		if (instance == null)
		{
			instance = new RZSharedPreferences();
		}
		return instance;
	}

	/**
	 * �ļ�����
	 */
	private static final String FILE_NAME = "network_module";

	/**
	 * �û�id��Keyֵ
	 */
	private static final String KEY_USERID = "userid";

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
	 * �û��ֻ������Keyֵ
	 */
	private static final String KEY_PHONE_NUMBER = "phone_number";

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
	 * �����Keyֵ
	 */
	private static final String KEY_PASSWORD = "password";

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
