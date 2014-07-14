package com.iwise.utils;

import com.iwise.base.RZApplication;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;

/**
 * �Զ���Ի���
 * 
 * @author Harvey
 * 
 * 
 */
public class CustomDialog extends Dialog
{

	/**
	 * ���
	 */
	private static int DEFAULT_WIDTH = LayoutParams.MATCH_PARENT;

	/**
	 * �߶�
	 */
	private static int DEFAULT_HEIGHT = LayoutParams.WRAP_CONTENT;

	/**
	 * ���캯��
	 * 
	 * @param context
	 *            ������
	 * @param layout
	 *            �����ļ�id
	 * @param style
	 *            ����ļ�id
	 */
	public CustomDialog(Context context, int layout, int style)
	{
		this(context, DEFAULT_WIDTH, DEFAULT_HEIGHT, layout, style);
	}

	public CustomDialog(Context context, int width, int height, int layout, int style)
	{
		super(context, style);

		// set content
		// ���ز����ļ�
		setContentView(layout);

		// set window params
		// ����Window����
		Window window = getWindow();

		// �õ�Window����
		WindowManager.LayoutParams params = window.getAttributes();

		// set width,height by density and gravity
		// �õ��ܶ���
		float density = RZApplication.getInstance().getScreenDensity();
		int screen_width = RZApplication.getInstance().getScreenWith();

		// ������С��0
		// �����޸ģ�MATCH_PARENTҲ������һ����
		if (width < 0)
		{
			params.width = (int) (screen_width - 40 * density);
		} else
		{
			params.width = (int) (width * density);
		}

		// ����߶�С��0
		if (height < 0)
		{
			params.height = DEFAULT_HEIGHT;
		} else
		{
			params.height = (int) (height * density);
		}

		// ������ʾ
		params.gravity = Gravity.CENTER;

		// ���������õĲ�����ӵ�Window��
		window.setAttributes(params);
	}
}
