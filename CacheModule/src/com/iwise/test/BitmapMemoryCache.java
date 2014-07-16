package com.iwise.test;

import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.text.TextUtils;

public class BitmapMemoryCache
{
	private static LruCache<String, Bitmap> mBitmapLruCache;
	private static ConcurrentHashMap<String, SoftReference<Bitmap>> mBitmapLevelTwoCache;

	private static BitmapMemoryCache mBitmapMemoryCache;

	/**
	 * ͼƬ�Ķ�����������
	 */
	public static final int LEVEL_TWO_CACHE_CAPACITY = 10;

	private BitmapMemoryCache(Context context)
	{
		mBitmapLevelTwoCache = new ConcurrentHashMap<String, SoftReference<Bitmap>>(LEVEL_TWO_CACHE_CAPACITY);

		final int memClass = ((ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
		final int cacheSize = 1024 * 1024 * memClass / 8;
		mBitmapLruCache = new LruCache<String, Bitmap>(cacheSize)
		{
			@Override
			protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue)
			{
				super.entryRemoved(evicted, key, oldValue, newValue);
				mBitmapLevelTwoCache.put(key, new SoftReference<Bitmap>(oldValue));
			}

			@Override
			protected int sizeOf(String key, Bitmap value)
			{
				return value.getRowBytes() * value.getHeight();
			}
		};
	}

	public static BitmapMemoryCache getInstance(Context context)
	{
		if (mBitmapMemoryCache == null)
		{
			mBitmapMemoryCache = new BitmapMemoryCache(context);
		}
		return mBitmapMemoryCache;
	}

	/**
	 * �ӻ����л�ȡͼƬ
	 */
	public Bitmap getBitmapFromCache(String url)
	{
		Bitmap bitmap = null;
		// �ȴ�LruCache�����л�ȡ
		if (mBitmapLruCache != null && !TextUtils.isEmpty(url))
		{
			try
			{
				bitmap = mBitmapLruCache.get(url);
			} catch (OutOfMemoryError e)
			{
				e.printStackTrace();
			}
		}

		if (bitmap == null && mBitmapLevelTwoCache != null && !TextUtils.isEmpty(url))
		{
			// ���LruCache���Ҳ�������������������
			final SoftReference<Bitmap> bitmapReference = mBitmapLevelTwoCache.get(url);

			if (bitmapReference != null)
			{
				try
				{
					bitmap = bitmapReference.get();
					if (bitmap != null)
					{
						// ��ͼƬ�ƻ�Ӳ����
						mBitmapLruCache.put(url, bitmap);
						mBitmapLevelTwoCache.remove(url);
					} else
					{
						mBitmapLevelTwoCache.remove(url);
					}
				} catch (OutOfMemoryError e)
				{
					e.printStackTrace();
				}
			}
		}
		return bitmap;
	}

	/**
	 * ���ͼƬ������
	 * 
	 * @param url
	 * @param bitmap
	 */
	public void addBitmapToCache(String url, Bitmap bitmap)
	{
		if (mBitmapLruCache != null)
		{
			mBitmapLruCache.put(url, bitmap);
		}
	}

	/**
	 * ɾ���ڴ滺���е�ͼƬ
	 * 
	 * @param url
	 */
	public void deleteBitmapFromCache(String url)
	{
		mBitmapLruCache.remove(url);
		mBitmapLevelTwoCache.remove(url);
	}
}
