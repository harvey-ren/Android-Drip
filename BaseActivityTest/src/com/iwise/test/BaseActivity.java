package com.iwise.test;

import android.app.Activity;
import android.os.Bundle;

/**
 * ����
 * 
 * @ClassName: BaseActivity
 * @Description: �����Լ��Ļ���
 * @author Harvey
 * @date 2014-7-14 ����9:35:04
 * 
 */
public abstract class BaseActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setLayoutView();
		initialize();
		serviceLogic();
	}

	/**
	 * ���ò����ļ�
	 * 
	 * @Title: setLayoutView
	 * @Description: ���ò����ļ�
	 * @param
	 * @return void ��������
	 * @throws
	 */
	protected abstract void setLayoutView();

	/**
	 * ��ʼ��
	 * 
	 * @Title: initialize
	 * @Description:
	 * @param
	 * @return void ��������
	 * @throws
	 */
	protected void initialize()
	{

	}

	/**
	 * ҵ���߼�����
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

}
