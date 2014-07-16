package com.iwise.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

public class BitmapLoader
{
	private static final int DOWNLOAD_STATE_DONT_NEED_YET_OR_SUCESS = 1000110;
	private static final int DOWNLOAD_STATE_DOWNLOADING = 1000111;
	private static final int DOWNLOAD_STATE_LOAD_FAILED = 1000112;
	private static final int DOWN_LOAD_IMAGE_COMPLETE = 0;// ������������ͼƬ���
	private ExecutorService executorService = Executors.newFixedThreadPool(Constants.MAX_THREADS);
	private BitmapMemoryCache mBitmapMemoryCache; // �ڴ滺��ͼƬ
	private BitmapFileCache mBitmapFileCache; // �ļ�����ͼƬ
	private static BitmapLoader mBitmapLoader = null;

	public static boolean isCanLoadFromNet = true;
	private int currentDownLoadState = DOWNLOAD_STATE_DONT_NEED_YET_OR_SUCESS;

	public BitmapLoader(Context context)
	{
		mBitmapMemoryCache = BitmapMemoryCache.getInstance(context);
		mBitmapFileCache = BitmapFileCache.getInstance();
	}

	public static BitmapLoader getInstance(Context context)
	{
		if (null == mBitmapLoader)
		{
			mBitmapLoader = new BitmapLoader(context);
		}
		return mBitmapLoader;
	}

	/**
	 * ��Ҫ���ص�ͼƬ��ӵ����������
	 * 
	 * @param url
	 * @param img
	 * @param callback
	 */
	public Bitmap loadBitmap(final String url, final OnBitmapLoadFinishedListener onBitmapLoadFinishedListener)
	{
		Bitmap bitmap;
		// ���ڴ滺���л�ȡͼƬ
		bitmap = mBitmapMemoryCache.getBitmapFromCache(url);
		if (bitmap != null)
		{
			Utils.log("�ӻ��������ȡͼƬ");
			return bitmap;
		} else
		{
			Utils.log("��������û��ͼƬ----");
		}
		// ȡ�ⲿ�洢�ϵ�ͼƬ
		if (null == bitmap)
		{
			bitmap = loadFromFileCache(url);
		}

		final Handler handler = new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				switch (msg.what)
				{
					case DOWN_LOAD_IMAGE_COMPLETE:
						final Bitmap bitmapFromFileCache = (Bitmap) msg.obj;
						if (null != onBitmapLoadFinishedListener)
						{
							onBitmapLoadFinishedListener.onBitmapLoadFinished(bitmapFromFileCache, url);
						}
						break;
				}
			}
		};

		// �ⲿ�洢��ȡ������������ȡ
		if (isCanLoadFromNet == true && null == bitmap)
		{
			executorService.execute(new Runnable()
			{
				@Override
				public void run()
				{
					Bitmap downloadBitmap = BitmapHttpHelper.downloadBitmap(url);
					if (null != downloadBitmap)
					{
						handler.sendMessage(handler.obtainMessage(DOWN_LOAD_IMAGE_COMPLETE, downloadBitmap));
						// ����ļ����治Ϊ�վͰ��ļ�������ӵ��ڴ滺��
						mBitmapMemoryCache.addBitmapToCache(url, downloadBitmap);
						setCurrentDownLoadState(DOWNLOAD_STATE_DONT_NEED_YET_OR_SUCESS);
					} else
					{
						setCurrentDownLoadState(DOWNLOAD_STATE_LOAD_FAILED);
					}
				}
			});
			setCurrentDownLoadState(DOWNLOAD_STATE_DOWNLOADING);
		}
		return bitmap;
	}

	private Bitmap loadFromFileCache(final String url)
	{
		Bitmap bitmapFromSDCard = mBitmapFileCache.getImage(url);
		if (bitmapFromSDCard != null)
		{
			Utils.log("��SD�������ȡͼƬ");
			// ����ļ����治Ϊ�վͰ��ļ�������ӵ��ڴ滺��
			mBitmapMemoryCache.addBitmapToCache(url, bitmapFromSDCard);
		} else
		{
			Utils.log("SD������û��ͼƬ----");
		}
		return bitmapFromSDCard;
	}

	public void stopLoadFromNetWork()
	{
		if (null != executorService)
		{
			executorService.shutdown();
		}
	}

	private synchronized void setCurrentDownLoadState(int downLoadStateCode)
	{
		currentDownLoadState = downLoadStateCode;
	}

	public synchronized int getCurrentDownLoadState()
	{
		return currentDownLoadState;
	}

	/**
	 * ɾ�������ļ�
	 * 
	 * @param url
	 */
	public void deleteImageCache(String url)
	{
		// ���ڴ����Ƴ�
		mBitmapMemoryCache.deleteBitmapFromCache(url);
		// ���ļ����Ƴ�
		mBitmapFileCache.deleteImage(url);
	}

	interface OnBitmapLoadFinishedListener
	{
		public void onBitmapLoadFinished(Bitmap imageBitmap, String imageUrl);
	}
}
