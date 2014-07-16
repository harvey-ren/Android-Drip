package com.iwise.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnShowListener;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iwise.activity.R;
import com.iwise.base.RZApplication;
import com.iwise.utils.CustomDialog;

/**
 * 
 * �첽������
 * 
 * @File: NetworkAsyncTask.java
 * @Package com.iwise.net
 * @Description:
 * @author Harvey
 * @date 2014-7-14 ����2:21:39
 * 
 */
public class NetworkAsyncTask extends AsyncTask<Request, Void, String>
{

	/**
	 * ��ʱʱ��
	 */
	private static final int REQUEST_TIME_OUT = 60000;

	/**
	 * ������
	 */
	private Context context;

	/**
	 * ��Ӧ����
	 */
	private ResponseListener responseListener;

	/**
	 * �Ƿ���ʾ�Ի���
	 */
	private boolean isShowPromptDialog = false;

	/**
	 * ��ʾ��Ϣ
	 */
	private String str_prompt_msg = "���Ժ�";

	/**
	 * �Զ���Ի������
	 */
	private CustomDialog progressDialog = null;

	/**
	 * ȡ��
	 */
	private static final String TASK_CANCEL = "task_cancel";

	/**
	 * �Ƿ�ȡ��
	 */
	private boolean isCancelled = false;

	public NetworkAsyncTask(Context context)
	{
		isShowPromptDialog = false;
		this.context = context;
	}

	public NetworkAsyncTask(Context context, String str_msg)
	{
		isShowPromptDialog = true;
		str_prompt_msg = str_msg;
		this.context = context;
	}

	// �첽����֮ǰִ�еķ���
	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();

		if (isShowPromptDialog)
		{
			showProgressDialog();
		}
	}

	/**
	 * ��ʾ���ȶԻ���
	 * 
	 * @Title: showProgressDialog
	 * @Description:
	 * @param
	 * @return void ��������
	 * @throws
	 */

	private void showProgressDialog()
	{
		// ���progressDialogΪ��
		if (progressDialog == null)
			progressDialog = new CustomDialog(context, R.layout.dialog_progress_custom, R.style.CustomDialog);

		// ����ProgressDialog �Ƿ���԰��˻ؼ�ȡ��
		progressDialog.setCancelable(true);

		// ����ProgressDialog ���ܵ����Ļȡ��������
		progressDialog.setCanceledOnTouchOutside(false);

		// �ҵ���ʾ��Ϣ���ı�
		TextView tv_msg = (TextView) progressDialog.findViewById(R.id.tv_msg);
		tv_msg.setText(str_prompt_msg);// ������Ϣ����

		// ͼƬ�ؼ�
		ImageView img_loading = (ImageView) progressDialog.findViewById(R.id.img_loading);
		img_loading.setBackgroundResource(R.anim.please_wait);

		final AnimationDrawable animationDrawable = (AnimationDrawable) img_loading.getBackground();

		progressDialog.setOnShowListener(new OnShowListener()
		{
			@Override
			public void onShow(DialogInterface dialog)
			{
				if (animationDrawable != null)
					animationDrawable.start();
			}
		});
		progressDialog.setOnCancelListener(new OnCancelListener()
		{
			@Override
			public void onCancel(DialogInterface dialog)
			{
				if (animationDrawable != null)
					animationDrawable.stop();
				onCancelled();
			}
		});
		progressDialog.show();
	}

	@Override
	protected void onCancelled()
	{
		super.onCancelled();
		isCancelled = true;
	}

	@Override
	protected String doInBackground(Request... params)
	{
		String str_result = "";

		// �õ�Request����
		Request request = params[0];
		if (request == null)
			return str_result;

		// �õ�url��ַ
		String url = request.getUrl();

		if (url == null || url.equals(""))
			return str_result;

		// �õ������󷽷�
		String method = request.getRequestMethod().getMethodName();

		if (method == null || method.equals(""))
			return str_result;

		if (isCancelled)
		{
			return TASK_CANCEL;
		}

		// �������ΪPOST����
		if (method.equals(RequestMethod.POST.getMethodName()))
		{
			// ����HttpPost����
			HttpPost httpPost = new HttpPost(url);

			// ����
			List<BasicNameValuePair> valueList = new ArrayList<BasicNameValuePair>();

			// �����������ǿյ�
			if (request.getParams() == null || request.getParams().isEmpty())
				return str_result;

			// ������Ӳ���
			for (Map.Entry<String, String> param : request.getParams().entrySet())
			{
				valueList.add(new BasicNameValuePair(param.getKey(), param.getValue()));
			}

			try
			{
				httpPost.setEntity(new UrlEncodedFormEntity(valueList, HTTP.UTF_8));
			} catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
				return str_result;
			}

			// ���ò���
			httpPost.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, REQUEST_TIME_OUT);
			httpPost.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, REQUEST_TIME_OUT);

			// �������ͷ
			httpPost.setHeader("Version", RZApplication.getInstance().getAppVersionName());
			httpPost.setHeader("Imei", RZApplication.getInstance().getIMEI());
			httpPost.setHeader("Operation", RZApplication.getInstance().getOperation());
			httpPost.setHeader("Deviceid", RZApplication.getInstance().getPhoneModel() + "<>" + Build.BRAND);
			httpPost.setHeader("Network", RZApplication.getInstance().getNetwork() + "");
			httpPost.setHeader("Nettype", RZApplication.getInstance().getNetworkTpye() + "");
			httpPost.setHeader("Opname", RZApplication.getInstance().getOperatorName());
			httpPost.setHeader("Devicewidth", RZApplication.getInstance().getScreenWith() + "");
			httpPost.setHeader("Deviceheight", RZApplication.getInstance().getScreenHeight() + "");
			httpPost.setHeader("User-Agent", Header.USER_AGENT);
			httpPost.setHeader("Platform", Header.PLATFORM);
			httpPost.setHeader("Authid", Header.AUTHID);
			httpPost.setHeader("Channelcode", Header.CANNEL_CODE);
			httpPost.setHeader("Type", Header.APP_TYPE);
			httpPost.setHeader("Timestamp", System.currentTimeMillis() + "");

			// ����DefaultHttpClient����
			DefaultHttpClient client = new DefaultHttpClient();

			if (isCancelled)
			{
				return TASK_CANCEL;
			}

			HttpResponse httpResponse;

			try
			{
				httpResponse = client.execute(httpPost);
			} catch (IOException e)
			{
				if (httpPost != null)
				{
					httpPost.abort();// �ͷ���Դ
				}
				return str_result;
			}

			if (isCancelled)
			{
				if (httpPost != null)
				{
					// �ͷ���Դ
					httpPost.abort();
				}
				return TASK_CANCEL;
			}

			// �������ݳɹ�
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				try
				{
					str_result = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);

				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}

			if (httpPost != null)
			{
				httpPost.abort();// �ͷ���Դ
			}
		}
		return str_result;
	}

	// �����������֮��ִ�еķ���
	@SuppressLint("ShowToast")
	@Override
	protected void onPostExecute(String result)
	{
		super.onPostExecute(result);

		if (progressDialog != null && progressDialog.isShowing())
		{
			progressDialog.dismiss();
		}

		if (result == null || result.equals(""))
		{
			if (!isCancelled)
			{
				Toast.makeText(context, "��ȡ����ʧ��,�����ԣ�", Toast.LENGTH_SHORT);
			}
			responseListener.onResponseFail();
		} else if (result.equals(TASK_CANCEL))
		{
			Toast.makeText(context, "����ȡ����", Toast.LENGTH_SHORT);
		} else
		{
			System.out.println(result);
			responseListener.onResponseSuccess(ResponseParser.getInstance().parse(result));
		}
	}

	// �����Ӧ����
	public void setOnResponseListener(ResponseListener responseListener)
	{
		this.responseListener = responseListener;
	}

}
