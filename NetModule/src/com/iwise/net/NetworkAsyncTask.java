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
package com.iwise.net;

import android.content.Context;
import android.os.AsyncTask;

public class NetworkAsyncTask extends AsyncTask<Request, Void, String>
{

	private Context context;

	public NetworkAsyncTask(Context context)
	{
		this.context = context;
	}

	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(Request... params)
	{
		return null;
	}

	public void setOnResponseListener(ResponseListener responseListener)
	{

	}

}
