package com.iwise.test;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

/**
 * 
 * Timer��ʹ��
 * 
 * @ClassName: MainActivity
 * @Description:������
 * @author Harvey
 * @date 2014-7-4 ����4:23:43
 * 
 */

public class MainActivity extends Activity
{

	public static final byte ENTER = 1;

	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		imageView = (ImageView) this.findViewById(R.id.imgView);

		// ����Timer����
		Timer timer = new Timer();

		// ����TimerTask����
		TimerTask task = new TimerTask()
		{
			@Override
			public void run()
			{
				// ����Message����
				Message message = new Message();
				message.what = ENTER;
				// ������Ϣ
				handler.sendMessage(message);
			}
		};
		if (timer != null)
		{
			timer.schedule(task, 2000);
		}
	}

	Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
				case ENTER:
					// ��ͼƬ��Ϊ���ɼ�
					imageView.setVisibility(View.GONE);
					break;

				default:
					break;
			}
		};
	};

}
