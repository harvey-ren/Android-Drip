package com.example.fragmenttest;

import java.io.IOException;
import java.io.InputStream;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 
 * 
 * @ClassName: LeftFragment
 * @Description: ���Fragment
 * @author Harvey
 * @date 2014-6-26 ����5:04:57
 * 
 */
@SuppressLint("NewApi")
public class LeftFragment extends Fragment implements OnItemClickListener
{
	private ListView listView;
	private String[] data = new String[] { "���ս��2", "���ν��3:�º�֮ʱ", "������2" };

	// ������ʱ��ִ��
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.left, null);
		listView = (ListView) view.findViewById(R.id.listView);
		listView.setOnItemClickListener(this);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, data);
		listView.setAdapter(arrayAdapter);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);// ѡ�в�����ѡ�е�λ��
		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		TextView textView = (TextView) getActivity().findViewById(R.id.tv_detail);
		try
		{
			// ������
			InputStream inputStream = getActivity().getResources().getAssets().open("m" + position);
			byte[] buffer = new byte[1024];
			int count = inputStream.read(buffer);
			String detail = new String(buffer, 0, count, "utf-8");
			
			//���textViewΪnull
			if (textView == null)
			{
				Intent intent = new Intent(getActivity(), DetailActivity.class);
				intent.putExtra("detail", detail);
				startActivity(intent);
			} else
			{
				textView.setText(detail);
			}
			if (inputStream != null)
			{
				inputStream.close();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
