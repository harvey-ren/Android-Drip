package com.iwise.utils;

/**
 * ����볨��
 * 
 * @ClassName: ResultCode
 * @Description: TODO(������һ�仰��������������)
 * @author Harvey
 * @date 2014-7-16 ����10:33:00
 * 
 */
public class ResultCode
{

	/**
	 * ��Ӧ�ɹ���
	 */
	public static final int RESPONSE_SUCCESS_CODE = 1;
	/**
	 * �ɹ�
	 */
	public static final String SUCCESS = "000000";

	/**
	 * �����Ȩδͨ��
	 */
	public static final String SERVE_AUTHENTICA_FAIL = "100000";

	/**
	 * ���������ʽ����
	 */
	public static final String REQUEST_PARAMETER_FORMAT_ERROR = "200000";

	/**
	 * �����ڲ�����
	 */
	public static final String SERVE_INSIDE_ERROR = "300000";

	/**
	 * ����ʧ��
	 */
	public static final String SEND_FAIL = "300001";

	/**
	 * �û����Ѿ�����
	 */
	public static final String USERNAME_ALREADY_EXIST = "300101";

	/**
	 * �û������������
	 */
	public static final String USERNAME_OR_PASSWORD_ERROR = "300102";

	/**
	 * �û�������
	 */
	public static final String USERNAME_NO_EXIST = "300103";

	/**
	 * ���糬ʱ
	 */
	public static final String NETWORK_TIMEOUT = "400000";

	/**
	 * ����JSON��ʽ����
	 */
	public static final String REQUEST_JSON_FORMAT_ERROR = "600000";

	/**
	 * δע��(��ʼ��)
	 */
	public static final String NOT_REGISTERED = "600001";

	/**
	 * ����Ҫ����
	 */
	public static final String NOT_NEED_UPDATE = "600002";

	/**
	 * �û���������Ϊ��
	 */
	public static final String USERNAME_OR_PASSWORD_NULL = "600003";

	/**
	 * ���������
	 */
	public static final String ACTIVE_CODE_EXPIRE = "600102";

	/**
	 * ��������Ч
	 */
	public static final String ACTIVE_CODE_INVALID = "600103";

	/**
	 * ��֤�����
	 */
	public static final String VERIFY_CODE_ERROR = "600104";

	/**
	 * ����headerͷ���ݴ���
	 */
	public static final String REQUEST_HEADER_CONTENT_ERROR = "700000";
}
