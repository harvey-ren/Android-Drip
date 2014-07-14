package cn.iwise.base;

import android.app.Activity;
import android.os.Bundle;

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
	 */
	protected abstract void setLayoutXml();

	/**
	 * ��ʼ��
	 */
	protected void init()
	{
	}

	/**
	 * ҵ���߼�
	 */
	protected void serviceLogic()
	{

	}
}
