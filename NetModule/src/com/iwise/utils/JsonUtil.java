package com.iwise.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Json������
 * 
 * @ClassName: JsonUtil
 * @Description: 
 * @author Harvey
 * @date 2014-7-16 ����10:32:24
 * 
 */
public class JsonUtil
{

	/**
	 * ��ȡ��json����
	 * 
	 * @param jsonObj
	 * @param key
	 * @param defaultobj
	 * @return
	 */
	public static JSONObject getJSONObject(JSONObject jsonObj, String key, JSONObject defaultobj)
	{
		if (jsonObj != null)
		{
			if (jsonObj.has(key))
			{
				try
				{
					return jsonObj.getJSONObject(key);
				} catch (JSONException e)
				{
					return defaultobj;
				}
			} else
			{
				return defaultobj;
			}
		}
		return defaultobj;
	}

	/**
	 * ��ȡ����
	 * 
	 * @param jsonObj
	 * @param key
	 * @param defaultArray
	 * @return
	 */
	public static JSONArray getJSONArray(JSONObject jsonObj, String key, JSONArray defaultArray)
	{
		if (jsonObj != null)
		{
			if (jsonObj.has(key))
			{
				try
				{
					return jsonObj.getJSONArray(key);
				} catch (JSONException e)
				{
					return defaultArray;
				}
			} else
			{
				return defaultArray;
			}
		}
		return defaultArray;
	}

	/**
	 * ��ȡkey��Ӧ��ֵ
	 * 
	 * @param resultJson
	 * @param key
	 * @param defaultStr
	 * @return
	 */
	public static String getString(JSONObject resultJson, String key, String defaultStr)
	{
		if (resultJson != null)
		{
			try
			{
				if (resultJson.has(key) && resultJson.getString(key) != null)
				{
					return resultJson.getString(key);
				} else
				{
					return defaultStr;
				}
			} catch (JSONException e)
			{
				return defaultStr;
			}
		}
		return defaultStr;
	}

	/**
	 * ����json����int
	 * 
	 * @param json
	 *            json����
	 * @param name
	 *            keyֵ
	 * @param defaultobj
	 *            Ĭ�Ϸ���ֵ
	 * @return
	 */
	public static int getInt(JSONObject resultJson, String key, int defaultobj)
	{
		if (resultJson != null)
		{
			try
			{
				if (resultJson.has(key))
				{
					return resultJson.getInt(key);
				} else
				{
					return defaultobj;
				}
			} catch (JSONException e)
			{
				return defaultobj;
			}
		}
		return defaultobj;
	}

}
