/**
 * FileName:ObjectUtil.java
 * Author: Administrator
 * Create: 2014年6月30日
 * Last Modified: 2014年6月30日
 * Version: V1.0 
 */
package com.product.utils;

import org.springframework.web.multipart.MultipartFile;

/**
 * 对象工具类
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月30日
 */
public class ObjectUtil {
	/**
	 * 判定对象是否为空
	 * 
	 * @param object
	 * @return true...null或者空 / false...not null或者非空
	 */
	public static boolean isEmpty(Object object) {
		if (object == null) {
			return true;
		}

		if (object instanceof String) {
			if ("".equals(((String) object))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判定对象是否不为空
	 * 
	 * @param object
	 * @return true...not null或者非空 / false...null或者空
	 */
	public static boolean isNotEmpty(Object object) {
		return !(isEmpty(object));
	}

	/**
	 * 判定对象是否不为空
	 * 
	 * @param object
	 * @return true...not null或者非空 / false...null或者空
	 */
	public static boolean isFileNotEmpty(MultipartFile[] files) {
		boolean flag=true;
		if(files!=null&&!files.equals("")){
			if(files.length>0){
				for(MultipartFile file:files){
					if(file.getSize()==0){
						flag=false;
						break;
					}
				}
			}			
		}		
		return flag;
	}
}
