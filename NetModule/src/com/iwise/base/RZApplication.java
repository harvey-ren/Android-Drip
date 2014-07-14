package com.iwise.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.iwise.data.RZSharedPreferences;
import com.iwise.utils.NetUtils;

public class RZApplication extends Application
{

	/**
	 * ȫ��ʵ��
	 */
	private static RZApplication instance;

	/**
	 * ��Ļ���
	 */
	private int screen_with = 0;

	/**
	 * ��Ļ�߶�
	 */
	private int screen_height = 0;

	/**
	 * ��Ļ�ܶ�
	 */
	private float screen_density = 0.0f;

	@Override
	public void onCreate()
	{
		super.onCreate();
		instance = this;
		RZSharedPreferences.getInstance();
		getScreenInfo();
	}

	private void getScreenInfo()
	{
		WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metric = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(metric);

		screen_with = metric.widthPixels; // ��Ļ��ȣ����أ�
		screen_height = metric.heightPixels; // ��Ļ�߶ȣ����أ�
		screen_density = metric.density; // ��Ļ�ܶȣ�0.75 / 1.0 / 1.5��

		if (NetUtils.isDebug)
		{
			System.out.println("width===" + screen_with);
			System.out.println("screen_density===" + screen_density);

		}
	}

	public int getScreenWith()
	{
		return screen_with;
	}

	public int getScreenHeight()
	{
		return screen_height;
	}

	public static RZApplication getInstance()
	{
		return instance;
	}

	public float getScreenDensity()
	{
		return screen_density;
	}

	/**
	 * �汾��
	 * 
	 * @param context
	 * @return
	 */
	public int getAppVersionCode()
	{
		PackageManager packageManager = this.getPackageManager();
		// getPackageName()���㵱ǰ��İ�����0�����ǻ�ȡ�汾��Ϣ
		PackageInfo packInfo;
		int versionCode = 0;
		try
		{
			packInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
			versionCode = packInfo.versionCode;
		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
			return 1;
		}
		return versionCode;
	}

	/**
	 * �汾��
	 * 
	 * @param context
	 * @return
	 */
	public String getAppVersionName()
	{
		PackageManager packageManager = this.getPackageManager();
		// getPackageName()���㵱ǰ��İ�����0�����ǻ�ȡ�汾��Ϣ
		String versionName = "";
		try
		{
			PackageInfo packInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
			versionName = packInfo.versionName;
		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
			return "1.0.0";
		}
		return versionName;
	}

	/**
	 * ��ȡIMEI
	 * 
	 * @param context
	 * @return
	 */
	public String getIMEI()
	{
		String deviceId = "";
		TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		if (tm == null || tm.getDeviceId() == null || tm.getDeviceId().equals(""))
		{
			deviceId = getMacAddress(this);
			if (deviceId != null)
				deviceId.replace(":", "");
		} else
		{
			deviceId = tm.getDeviceId();
		}
		return deviceId;
	}

	/**
	 * 
	 * @param context
	 * @return
	 */
	private String getMacAddress(Context context)
	{
		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
	}

	/**
	 * ��ȡ����ϵͳ�İ汾
	 * 
	 * @return
	 */
	public String getOperation()
	{
		return android.os.Build.VERSION.RELEASE;
	}

	/**
	 * ��ȡ�ֻ��ͺ�
	 * 
	 * @return
	 */
	public String getPhoneModel()
	{
		return android.os.Build.MODEL;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	public int getNetwork()
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivityManager == null)
			return 0;

		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

		if (networkInfo == null)
			return 0;
		return networkInfo.getType();
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return
	 */
	public int getNetworkTpye()
	{
		TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

		if (telephonyManager == null)
			return 0;

		return telephonyManager.getNetworkType();

	}

	/**
	 * ��ȡ��Ӫ������
	 * 
	 * @return
	 */
	public String getOperatorName()
	{
		TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

		if (telephonyManager == null)
			return "δ֪";

		return telephonyManager.getSimOperatorName();

	}

}
