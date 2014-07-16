package com.iwise.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * ������
 * 
 * @ClassName: MainActivity
 * @Description:
 * @author Harvey
 * @date 2014-7-16 ����1:20:37
 * 
 */

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void startService(View view)
	{
		System.out.println("startService");
	}

	public void stopService(View view)
	{
		System.out.println("stopService");
	}

	public void bindService(View view)
	{
		System.out.println("bindService");
	}

	public void unbindService(View view)
	{
		System.out.println("unbindService");
	}

}
