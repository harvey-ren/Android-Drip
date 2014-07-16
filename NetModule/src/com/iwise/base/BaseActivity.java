package com.iwise.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.iwise.activity.R;
import com.iwise.utils.NetUtils;

/**
 * ����
 * 
 * @ClassName: BaseActivity
 * @Description:
 * @author Harvey
 * @date 2014-7-16 ����10:24:50
 * 
 */
public abstract class BaseActivity extends Activity
{

	protected byte REQUESTCODE = 0;

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
	 * ��ʾ������·����ʾ�Ի���
	 * 
	 * @Title: showSettingNetWorkDialog
	 * @Description:
	 * @param �趨�ļ�
	 * @return void ��������
	 * @throws
	 */
	protected void showSettingNetWorkDialog()
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
