package com.iwise.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.iwise.activity.R;
import com.iwise.base.DialogButtonListener;

/**
 * ������صĹ�����
 * 
 * @author Harvey
 * 
 */
public class NetUtils
{

	/**
	 * �Ƿ����ģʽ
	 */
	public static final boolean isDebug = true;

	/**
	 * �ж������Ƿ����
	 * 
	 * @Title: isNetworkAvailable
	 * @Description:
	 * @param @param context
	 * @param @return
	 * @return boolean ��������
	 * @throws
	 */
	public static boolean isNetworkAvailable(Context context)
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivityManager != null)
		{
			NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

			if (networkInfo != null && networkInfo.length > 0)
			{
				for (int i = 0; i < networkInfo.length; i++)
				{
					if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * ��ʾ�Ի���
	 * 
	 * @Title: showDialog
	 * @Description:
	 * @param @param context
	 * @param @param message
	 * @param @param listener ����
	 * @return void ��������
	 * @throws
	 */
	public static void showDialog(Context context, String message, final DialogButtonListener listener)
	{
		final CustomDialog dialog = new CustomDialog(context, R.layout.dialog_custom, R.style.CustomDialog);
		TextView tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
		tv_msg.setText(message);// ������Ϣ����

		Button btn_confirm = (Button) dialog.findViewById(R.id.btn_confirm);
		Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);

		btn_confirm.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				listener.onPositiveButtonClick();
				dialog.dismiss();
			}
		});

		btn_cancel.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				listener.onNegativeButtonClick();
				dialog.dismiss();
			}
		});
		dialog.show();
	}

}
