package com.iwise.activity;

import com.iwise.base.BaseActivity;
import com.iwise.base.RZApplication;
import com.iwise.data.RZSharedPreferences;
import com.iwise.net.Request;
import com.iwise.net.RequestManager;
import com.iwise.utils.NetUtils;

import android.view.View;
import cn.iwise.activity.R;

/**
 * ��������
 * 
 * @author Harvey
 * 
 */
public class MainActivity extends BaseActivity
{

	@Override
	protected void setLayoutXml()
	{
		setContentView(R.layout.activity_main);
	}

	/**
	 * ��������ķ���
	 * 
	 * @param view
	 */
	public void request(View view)
	{
		System.out.println("request");

		// ���������õ������
		if (NetUtils.isNetworkAvailable(this))
		{
			String userid = RZSharedPreferences.getUserId(this);
			String phonenumber = RZSharedPreferences.getPhoneNumber(this);
			String password = RZSharedPreferences.getPassWord(this);
			Request request = RequestManager.getInstance().getInitRequest(phonenumber, password, userid, RZApplication.getInstance().getAppVersionCode());
		}
	}
}
