package com.iwise.test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;

public class BitmapFileCache
{
	/**
	 * SD������ʣ��ռ�
	 */
	private static final int FREE_SD_SPACE_NEEDED_TO_CACHE = 10;
	private static final int MB = 1024 * 1024;
	private static final int CACHE_SIZE = 10;
	private static BitmapFileCache mBitmapFileCache = null;
	private BufferedOutputStream bos;

	public static BitmapFileCache getInstance()
	{
		if (null == mBitmapFileCache)
		{
			mBitmapFileCache = new BitmapFileCache();
		}
		return mBitmapFileCache;
	}

	private BitmapFileCache()
	{
		// �����ļ�����
		removeCache(getFileCacheDirectory());
	}

	/**
	 * ������
	 * 
	 * ����洢Ŀ¼�µ��ļ���С��
	 * ���ļ��ܴ�С���ڹ涨��CACHE_SIZE����sdcardʣ��ռ�С��FREE_SD_SPACE_NEEDED_TO_CACHE�Ĺ涨
	 * ��ôɾ��40%���û�б�ʹ�õ��ļ�
	 * 
	 * @param dirPath
	 * @param filename
	 */
	private boolean removeCache(String dirPath)
	{
		final File dir = new File(dirPath);
		final File[] files = dir.listFiles();
		if (files == null)
		{
			return true;
		}

		if (!android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
		{
			return false;
		}

		int dirSize = 0;
		for (int i = 0; i < files.length; i++)
		{
			if (files[i].getName().contains(Constants.WHOLESALE_CONV))
			{
				dirSize += files[i].length();
			}
		}

		if (dirSize > CACHE_SIZE * MB || FREE_SD_SPACE_NEEDED_TO_CACHE > freeSpaceOnSd())
		{
			final int removeFactor = (int) ((0.4 * files.length) + 1);

			Arrays.sort(files, new FileLastModifSort());

			// Util.log("�������ļ�");

			for (int i = 0; i < removeFactor; i++)
			{

				if (files[i].getName().contains(Constants.WHOLESALE_CONV))
				{
					files[i].delete();
				}
			}
		}

		if (freeSpaceOnSd() <= CACHE_SIZE)
		{
			return false;
		}
		return true;
	}

	/**
	 * �����ļ�������޸�ʱ���������
	 */
	private class FileLastModifSort implements Comparator<File>
	{
		public int compare(File arg0, File arg1)
		{
			if (arg0.lastModified() > arg1.lastModified())
			{
				return 1;
			} else if (arg0.lastModified() == arg1.lastModified())
			{
				return 0;
			} else
			{
				return -1;
			}
		}
	}

	/**
	 * ��ȡͼƬ
	 * 
	 * @param url
	 * @return
	 */
	public Bitmap getImage(final String url)
	{
		final String path = getFileCacheDirectory() + "/" + convertUrlToFileName(url) + Constants.WHOLESALE_CONV;
		final File file = new File(path);
		Bitmap bmp = null;
		if (file.exists())
		{
			try
			{
				bmp = BitmapFactory.decodeFile(path);
				if (bmp == null)
				{
					file.delete();
				} else
				{
					updateFileTime(path);
				}
			} catch (OutOfMemoryError e)
			{
				e.printStackTrace();
			}
		}
		return bmp;
	}

	/**
	 * ��û���Ŀ¼
	 * 
	 * @return
	 */
	private String getFileCacheDirectory()
	{
		File sdcardDir = Environment.getExternalStorageDirectory();
		String fileDirectory = "/mnt/sdcard";
		if (null != sdcardDir)
		{
			fileDirectory = sdcardDir.getAbsolutePath() + "/" + Constants.IMAGE_CACHE_DIR;
		}
		return fileDirectory;
	}

	/**
	 * �洢ͼƬ������
	 * 
	 * @param url
	 * @param inputStream
	 * @return
	 */
	private void saveToLocal(String url, Bitmap bitmap)
	{

		// 1.�ж��Ƿ����sdcard
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
		{
			String fileDirectory = getFileCacheDirectory();

			// Util.log("fileDirectory === " + fileDirectory);

			File dir = new File(fileDirectory);
			if (!dir.exists())
			{
				dir.mkdirs();
			}

			// Util.log("path.exists() === " + dir.exists());

			final String fileName = convertUrlToFileName(url);
			File file = new File(fileDirectory + "/" + fileName + Constants.WHOLESALE_CONV);
			if (!file.exists())
			{
				try
				{
					file.createNewFile();
					bos = new BufferedOutputStream(new FileOutputStream(file));
					bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
					bos.flush();
				} catch (FileNotFoundException e)
				{
					e.printStackTrace();
				} catch (IOException e)
				{
					e.printStackTrace();
					file.delete();
				} catch (OutOfMemoryError e)
				{
					e.printStackTrace();
					file.delete();
				} finally
				{
					try
					{
						if (bos != null)
						{
							bos.close();
						}
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * ��urlת���ļ���
	 * 
	 * @param url
	 * @return
	 */
	private String convertUrlToFileName(String url)
	{
		String fileName = "";
		try
		{
			fileName = Utils.MD5(url);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return fileName;
	}

	/**
	 * �޸��ļ�������޸�ʱ��
	 * 
	 * @param path
	 */
	public void updateFileTime(String path)
	{
		final File file = new File(path);
		final long newModifiedTime = System.currentTimeMillis();
		file.setLastModified(newModifiedTime);
	}

	/**
	 * ����ͼƬ��SD��
	 * 
	 * @param bm
	 * @param url
	 */
	public void saveInputStreamToLocal(final Bitmap bitmap, final String url)
	{
		if (bitmap == null || null == url || "".equals(url))
		{
			return;
		}
		// SD������10MB�Ͳ���ͼƬ
		if (FREE_SD_SPACE_NEEDED_TO_CACHE > freeSpaceOnSd())
		{
			return;
		}
		new Thread()
		{

			@Override
			public void run()
			{
				saveToLocal(url, bitmap);
			}
		}.start();

	}

	/**
	 * ����sdcard�ϵ�ʣ��ռ�
	 * 
	 * @return
	 */
	private int freeSpaceOnSd()
	{
		final StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
		final double sdFreeMB = ((double) stat.getAvailableBlocks() * (double) stat.getBlockSize()) / MB;
		return (int) sdFreeMB;
	}

	/**
	 * ɾ��������ļ�
	 * 
	 * @param url
	 * @return
	 */
	public boolean deleteImage(final String url)
	{
		final String path = getFileCacheDirectory() + "/" + convertUrlToFileName(url) + Constants.WHOLESALE_CONV;
		final File file = new File(path);
		if (file.exists())
		{
			return file.delete();
		}
		return false;
	}
}
