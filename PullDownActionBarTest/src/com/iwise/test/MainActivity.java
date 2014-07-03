package com.iwise.test;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

@SuppressLint("InlinedApi")
public class MainActivity extends Activity
{
	// �õ��ַ�����
	private String[] nameArr;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		nameArr = getResources().getStringArray(R.array.student);
		ArrayList<String> dataList = new ArrayList<String>();

		for (int i = 0; i < nameArr.length; i++)
		{
			dataList.add(nameArr[i]);
		}

		// ����Adapter����
		MyAdapter adapter = new MyAdapter(this);
		adapter.putDataList(dataList);

		// �õ�ActionBar
		ActionBar actionBar = getActionBar();

		// ��ActionBar�Ĳ���ģ������ΪNAVIGATION_MODE_LIST
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		// ΪActionBar���������˵��ͼ�����
		actionBar.setListNavigationCallbacks(adapter, new DropDownListenser());
	}

	/**
	 * ʵ��OnNavigationListener�ӿ�
	 */
	class DropDownListenser implements OnNavigationListener
	{
		// ��ѡ�������˵����ʱ�򣬽�Activity�е������û�Ϊ��Ӧ��Fragment
		public boolean onNavigationItemSelected(int itemPosition, long itemId)
		{
			// ����FragmentManager����
			FragmentManager manager = getFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			// �����Զ���Fragment
			PeopleFragment peopleFragment = new PeopleFragment();
			// ��Activity�е������滻�ɶ�Ӧѡ���Fragment
			transaction.replace(R.id.context, peopleFragment, nameArr[itemPosition]);
			transaction.commit();
			return true;
		}
	}

}
