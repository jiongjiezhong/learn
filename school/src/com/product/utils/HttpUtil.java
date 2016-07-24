package com.product.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * http工具�?
 */
public class HttpUtil {
	/**
	 * 发�? �?��post请求
	 * @param urlString 请求url
	 * @param paramString 请求参数�?param1=value1&param2=value2
	 * @param charsetEncoding 参数编码
	 * @return 响应�?
	 * @throws IOException
	 */
	public static InputStream sendPostRequest(String urlString, String paramString, String charsetEncoding) throws IOException{
		URL url = new URL(urlString);
		HttpURLConnection conns = (HttpURLConnection)url.openConnection();
		conns.setDoOutput(true);
		conns.setDoInput(true);
		conns.setUseCaches(false);
		conns.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		OutputStreamWriter osws = new OutputStreamWriter(conns.getOutputStream(), charsetEncoding);
		osws.write(paramString);
		osws.flush();
		osws.close();
		conns.disconnect();
		return conns.getInputStream();
	}
	/**
	 * 发�?�?��GET请求
	 * @param urlString 请求url
	 * @return 响应�?
	 * @throws IOException
	 */
	public static InputStream sendGetRequest(String urlString) throws IOException{
		URL url = new URL(urlString);
		return url.openStream();
	}
	/**
	 * 处理http响应流的方法
	 * @param result 响应�?
	 * @param charsetEncoding 字符串编�?
	 * @return 结果字符�?
	 * @throws IOException
	 */
	public static String getResultString(InputStream result, String charsetEncoding) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(result,charsetEncoding));
		StringBuffer sb = new StringBuffer();
		String tmp = null;
		while((tmp = br.readLine())!=null){
			sb.append(tmp);
		}
		br.close();
		return sb.toString();
	}
	
	public static String getParamString(Object entity) throws Exception{
		Class<? extends Object> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<fields.length; i++){
			Field f = fields[i];
			f.setAccessible(true);
			Object val = f.get(entity);
			if(val == null) continue;
			sb.append(f.getName()).append("=").append(val).append("&");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	public static void main(String[] args) throws Exception {
//		String url = "http://api.map.baidu.com/geocoder/v2/?ak=sFjA86evmFPcelGNtd5DWhu8&callback=renderOption&output=json&address=百度大厦&city=北京";
//		InputStream is = sendPostRequest(url, "cn=fdsfds", "UTF-8");
//		
//		System.out.println(getResultString(is,"UTF-8"));
//		
//		is = sendGetRequest(url);
//		
//		System.out.println(getResultString(is,"UTF-8"));
		
		HttpUtil.sendPostRequest("http://localhost:8080/wanma/app/product/findProducts.do", "pageNum=1&pageNumber=20&a=好的", "UTF-8");
	}
}
