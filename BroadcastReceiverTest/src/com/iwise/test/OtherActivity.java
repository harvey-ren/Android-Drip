package com.iwise.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * 
 * 
 * @ClassName: OtherActivity
 * @Description:���͹㲥�Ľ���
 * @author Harvey
 * @date 2014-7-17 ����10:14:14
 * 
 */
public class OtherActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other);
	}

	public void send(View view)
	{
		Intent intent = new Intent();
		intent.setAction(MainActivity.TAG_ACTION);
		sendBroadcast(intent);
		OtherActivity.this.finish();
	}
}
