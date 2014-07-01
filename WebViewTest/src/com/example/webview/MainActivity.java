package com.example.webview;

import com.example.webdemo.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * 
 * WebView��ʹ�÷���
 * 
 * @ClassName: MainActivity
 * @Description:
 * @author Harvey
 * @date 2014-7-1 ����1:27:41
 * 
 */
public class MainActivity extends Activity
{
	private WebView webView;
	private ProgressBar progressBar;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		webView = (WebView) this.findViewById(R.id.webview);
		progressBar = (ProgressBar) this.findViewById(R.id.progressBar);

		// ����ִ���ܲ���ִ��JavaScript
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setSupportZoom(true);
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		webView.requestFocus();

		webView.loadUrl("http://leyu.miao.cn/weixin/index/list.jhtml");

		webView.setWebViewClient(new WebViewClient()
		{
			// ��ҳ���ؿ�ʼʱ���ã���ʾ������
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon)
			{
				super.onPageStarted(view, url, favicon);
				progressBar.setVisibility(View.VISIBLE);
			}

			// ��ҳ�������ʱ���ã����ؽ�����
			@Override
			public void onPageFinished(WebView view, String url)
			{
				super.onPageFinished(view, url);
				progressBar.setVisibility(View.GONE);
			}

			// ��ҳ����ʧ��ʱ���ã����ؽ�����
			@Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
			{
				super.onReceivedError(view, errorCode, description, failingUrl);
				progressBar.setVisibility(View.GONE);
			}

			// �ڵ�ǰ��WebView����ת���µ�url
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url)
			{
				view.loadUrl(url);
				return true;
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
			case KeyEvent.KEYCODE_BACK:
				webView.goBack();
				return true;

			default:
				break;
		}
		return super.onKeyDown(keyCode, event);
	}
}
