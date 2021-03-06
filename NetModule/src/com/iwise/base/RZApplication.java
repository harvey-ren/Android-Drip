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

/**
 * 重写Application类
 * 
 * @ClassName: RZApplication
 * @Description:
 * @author Harvey
 * @date 2014-7-16 上午10:25:56
 * 
 */
public class RZApplication extends Application
{

	/**
	 * 全局实例
	 */
	private static RZApplication instance;

	/**
	 * 屏幕宽度
	 */
	private int screen_with = 0;

	/**
	 * 屏幕高度
	 */
	private int screen_height = 0;

	/**
	 * 屏幕密度
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

	public static RZApplication getInstance()
	{
		return instance;
	}

	private void getScreenInfo()
	{
		WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metric = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(metric);

		screen_with = metric.widthPixels; // 屏幕宽度（像素）
		screen_height = metric.heightPixels; // 屏幕高度（像素）
		screen_density = metric.density; // 屏幕密度（0.75 / 1.0 / 1.5）

		if (NetUtils.isDebug)
		{
			System.out.println("width===" + screen_with);
			System.out.println("screen_density===" + screen_density);

		}
	}

	/**
	 * 得到屏幕的宽度
	 * 
	 * @Title: getScreenWith
	 * @Description:
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int getScreenWith()
	{
		return screen_with;
	}

	/**
	 * 得到屏幕的高度
	 * 
	 * @Title: getScreenHeight
	 * @Description:
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int getScreenHeight()
	{
		return screen_height;
	}

	/**
	 * 得到屏幕的密度
	 * 
	 * @Title: getScreenDensity
	 * @Description: TODO
	 * @param @return
	 * @return float
	 * @throws
	 */
	public float getScreenDensity()
	{
		return screen_density;
	}

	/**
	 * 版本号
	 * 
	 * @param context
	 * @return
	 */
	public int getAppVersionCode()
	{
		PackageManager packageManager = this.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
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
	 * 版本名
	 * 
	 * @param context
	 * @return
	 */
	public String getAppVersionName()
	{
		// 创建包管理者对象
		PackageManager packageManager = this.getPackageManager();

		String versionName = "";
		try
		{
			// getPackageName()是你当前类的包名，0代表是获取版本信息
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
	 * 获取IMEI
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
	 * @Title: getMacAddress
	 * @Description:
	 * @param @param context
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	private String getMacAddress(Context context)
	{
		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
	}

	/**
	 * 获取操作系统的版本
	 * 
	 * @return
	 */
	public String getOperation()
	{
		return android.os.Build.VERSION.RELEASE;
	}

	/**
	 * 获取手机型号
	 * 
	 * @return
	 */
	public String getPhoneModel()
	{
		return android.os.Build.MODEL;
	}

	/**
	 * 获取网络
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
	 * 获取网络类型
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
	 * 获取运营商名称
	 * 
	 * @return
	 */
	public String getOperatorName()
	{
		TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

		if (telephonyManager == null)
			return "未知";

		return telephonyManager.getSimOperatorName();
	}

}
