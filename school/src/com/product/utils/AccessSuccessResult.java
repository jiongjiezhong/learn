/**
 * FileName:AccessSuccessResult.java
 * Author: Administrator
 * Create: 2014年8月10日
 * Last Modified: 2014年8月10日
 * Version: V1.0 
 */
package com.product.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理成功JSON对象
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月10日
 */
public class AccessSuccessResult {
	/** 处理成功标识 */
	private static final int RESULT_OK = 100;
	/** 处理成功信息 */
	private static final String RESULT_OK_MSG = "处理成功";

	private StringBuffer sb;
	

	/**
	 * 构造函数
	 * 
	 * @param obj
	 *            返回信息对象
	 */
	public AccessSuccessResult() {
		handleResult("");
	}

	/**
	 * 构造函数
	 * 
	 * @param obj
	 *            返回信息对象
	 */
	public AccessSuccessResult(Object obj) {
		//handleResult(JSONObject.fromObject(obj).toString());
		handleResult(obj);
	}

	/**
	 * 构造函数
	 * 
	 * @param obj
	 *            返回信息对象
	 */
	public AccessSuccessResult(List<?> obj) {
		if (obj == null) {
			obj = new ArrayList<Object>();
		}
		//handleResult(JSONArray.fromObject(obj).toString());
		handleResult(obj);
	}

	/**
	 * 生成JSON数据（App端调用时使用）
	 * 
	 * @param obj
	 */
	@SuppressWarnings("rawtypes")
	private void handleResult(Object obj) {
		sb = new StringBuffer();
		String data = "";
		List<String> pubList = null;

		Map<Class, List<String>> controlMap = new HashMap<Class, List<String>>();
		if (obj != null) {
			pubList = JsonPublishXmlReader.getPublishList(obj.getClass()
					.getName());
			controlMap.put(obj.getClass(), pubList);
			data = new AppJsonObject(obj, controlMap).toString();
		} else {
			data = "null";
		}
		sb.append("{");
		sb.append("\"status\":" + RESULT_OK + ",");
		sb.append("\"msg\": \"" + RESULT_OK_MSG + "\",");
		sb.append("\"data\":");
		sb.append(data);
		sb.append("}");
		
		/*jsonObj.put("status", RESULT_OK);
		jsonObj.put("msg", RESULT_OK_MSG);
		jsonObj.put("data", data);*/
	}

	/**
	 * 返回JSON文字列
	 */
	public String toString() {
		//return jsonObj.toString();
		return sb.toString();
	}

}
