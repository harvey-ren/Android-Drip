package com.iwise.test;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * �Զ��� Application
 * 
 * @author Harvey
 * 
 */
public class FTApplication extends Application
{

	/**
	 * ȫ��ʵ��
	 */
	private static FTApplication instance;

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

	/**
	 * ��¼���б�������Activity
	 */
	private ArrayList<Activity> activityList = new ArrayList<Activity>();

	/**
	 * androidϵͳ�汾��
	 */
	public int sdkVersion;

	public static FTApplication getInstance()
	{
		return instance;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		instance = this;
		getScreenInfo();
		sdkVersion = Build.VERSION.SDK_INT;
	}

	private void getScreenInfo()
	{
		WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metric = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(metric);

		screen_with = metric.widthPixels; // ��Ļ��ȣ����أ�
		screen_height = metric.heightPixels; // ��Ļ�߶ȣ����أ�
		screen_density = metric.density; // ��Ļ�ܶȣ�0.75 / 1.0 / 1.5��
		int densityDpi = metric.densityDpi; // ��Ļ�ܶ�DPI��120 / 160 / 240��

		if (Utils.isDebug)
		{
			System.out.println("width===" + screen_with);
			System.out.println("height===" + screen_height);
			System.out.println("density===" + screen_density);
			System.out.println("densityDpi===" + densityDpi);
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

	/**
	 * ���Activity
	 * 
	 * @param activity
	 */
	public void addActivity(Activity activity)
	{
		if (activityList != null)
			activityList.add(activity);
	}

	/**
	 * ���б����Ƴ�Activity
	 * 
	 * @param activity
	 */
	public void removeActivity(Activity activity)
	{
		if (activityList != null && !activityList.isEmpty())
			activityList.remove(activity);
	}

	@Override
	public void onTerminate()
	{
		super.onTerminate();
		if (activityList != null && !activityList.isEmpty())
		{
			// �ص����е�Activity
			for (Activity activity : activityList)
			{
				if (activity != null)
				{
					activity.finish();
				}
			}
			// ���װ������Activity��list
			activityList.clear();
		}
		onDestroy();
	}

	/**
	 * �˳�����Ĳ���
	 */
	protected void onDestroy()
	{
		Intent intent = new Intent(Intent.ACTION_MAIN);

		intent.addCategory(Intent.CATEGORY_HOME);

		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		startActivity(intent);

		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
