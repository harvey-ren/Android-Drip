package cn.iwise.activity;

import android.view.View;
import cn.iwise.base.BaseActivity;
import cn.iwise.base.RZApplication;
import cn.iwise.data.RZSharedPreferences;
import cn.iwise.net.Request;
import cn.iwise.net.RequestManager;
import cn.iwise.utils.NetUtils;

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
