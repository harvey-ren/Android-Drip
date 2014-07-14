package com.iwise.net;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iwise.utils.JsonUtil;
import com.iwise.utils.ResultCode;

/**
 * ������Ӧ������
 * 
 * @author Harvey
 * 
 */
public class ResponseParser
{

	private static ResponseParser responseParser;

	private ResponseParser()
	{
	}

	public static ResponseParser getInstance()
	{
		if (responseParser == null)
		{
			responseParser = new ResponseParser();
		}
		return responseParser;
	}

	public NetWorkResponse parse(String resultString)
	{
		NetWorkResponse response = new NetWorkResponse();

		JSONObject jsonObj;
		try
		{
			jsonObj = new JSONObject(resultString);

			// �Ƿ���result����
			JSONObject resultJsonObj = JsonUtil.getJSONObject(jsonObj, "result", null);

			if (resultJsonObj != null)
			{
				// ��ȡ�����
				String resultCode = JsonUtil.getString(resultJsonObj, "resultcode", "0");

				// ���ý����
				response.setResultCode(resultCode);

				// �������벻Ϊ�ɹ���
				if (!resultCode.equals(ResultCode.SUCCESS))
				{
					// ���ô�����Ϣ
					response.setInfo(JsonUtil.getString(resultJsonObj, "resultinfo", "δ��ȡ����Ӧ��Ϣ"));
				}
			}

			// ����imei���ʶ����
			response.setImei(JsonUtil.getString(jsonObj, "imei", "0000000000000"));

			// �Ƿ�ɹ�
			response.setIsOk(JsonUtil.getInt(jsonObj, "isok", 0));

			// ƽ̨����汾��
			response.setSv(JsonUtil.getString(jsonObj, "sv", "1.0"));

			// ������url
			response.setPreurl(JsonUtil.getInt(jsonObj, "preurl", 0));

			// �û�ID
			response.setUserId(JsonUtil.getString(jsonObj, "userid", ""));

			// �Ƿ����
			response.setIsUpdate(JsonUtil.getInt(jsonObj, "isupdate", 0));

			// ������ַ
			response.setUpdateUrl(JsonUtil.getString(jsonObj, "updateurl", ""));

			// �ļ���С
			response.setFileSize(JsonUtil.getString(jsonObj, "versionsize", "0"));

			// �汾��
			response.setVersionCode(JsonUtil.getString(jsonObj, "version", "1.0"));

			// �ͻ�������
			response.setVersionDesc(JsonUtil.getString(jsonObj, "versiondesc", ""));

		} catch (JSONException e)
		{
			return response;
		}
		return response;
	}
}
