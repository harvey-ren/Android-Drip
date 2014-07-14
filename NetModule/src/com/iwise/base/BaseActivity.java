package com.iwise.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.iwise.activity.R;

import com.iwise.net.NetworkAsyncTask;
import com.iwise.net.Request;
import com.iwise.net.ResponseListener;
import com.iwise.utils.NetUtils;

/**
 * ������ ����
 * 
 * @author Harvey
 * 
 */
public abstract class BaseActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setLayoutXml();
		init();
		serviceLogic();
	}

	/**
	 * ���ò����ļ�
	 * 
	 * @Title: setLayoutXml
	 * @Description:���ò����ļ�
	 * @param
	 * @return void ��������
	 * @throws
	 */
	protected abstract void setLayoutXml();

	/**
	 * ��ʼ��
	 * 
	 * @Title: init
	 * @Description:
	 * @param
	 * @return void ��������
	 * @throws
	 */
	protected void init()
	{
	}

	/**
	 * ҵ���߼�
	 * 
	 * @Title: serviceLogic
	 * @Description:
	 * @param
	 * @return void ��������
	 * @throws
	 */
	protected void serviceLogic()
	{

	}

	/**
	 * �����첽�߳�
	 * 
	 * @Title: startAsyncTask
	 * @Description:
	 * @param @param networkAsyncTask
	 * @param @param request
	 * @param @param responseListener �趨�ļ�
	 * @return void ��������
	 * @throws
	 */
	protected void startAsyncTask(NetworkAsyncTask networkAsyncTask, Request request, ResponseListener responseListener)
	{
		// ���ȼ�������Ƿ����
		if (NetUtils.isNetworkAvailable(this))
		{
			networkAsyncTask.setOnResponseListener(responseListener);
			networkAsyncTask.execute(request);
		} else
		{
			showSettingNetWorkDialog();
		}
	}

	private void showSettingNetWorkDialog()
	{
		// ��ʾ��������ĶԻ���
		NetUtils.showDialog(this, getString(R.string.no_network_whether_set), new DialogButtonListener()
		{
			public void onPositiveButtonClick()
			{
				// ���ȷ����ť��ת�������������
				startActivityForResult(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS), REQUESTCODE);
			}
		});
	}
}
