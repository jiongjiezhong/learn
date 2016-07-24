/**     
 * @Title:  TaslUtils.java   
 * @Package com.wanma.task   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2015年11月26日 下午1:57:51   
 * @version V1.0     
 */
package com.wanma.task;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.product.utils.SpringContextHolder;

/**
 * @author bc
 *
 */
public class TaskUtils {
	public final static Logger log = Logger.getLogger(TaskUtils.class);

	/**
	 * 通过反射调用scheduleJob中定义的方法
	 * @param scheduleJob
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void invokMethod(ScheduleJob scheduleJob) {
		Object object = null;
		Class clazz = null;
		// springId不为空先按springId查找bean
		if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
			object = SpringContextHolder.getSpringContext().getBean(
					scheduleJob.getSpringId());
		} else if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
			try {
				clazz = Class.forName(scheduleJob.getBeanClass());
				object = clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("执行任务出错1" + scheduleJob.getJobName()
						+ e.getLocalizedMessage());
			}

		}
		if (object == null) {
			log.error("任务名称 = [" + scheduleJob.getJobName()
					+ "]---------------未启动成功，请检查是否配置正确！！！");
			return;
		}
		clazz = object.getClass();
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(scheduleJob.getMethodName());
		} catch (NoSuchMethodException e) {
			log.error("任务名称 = [" + scheduleJob.getJobName()
					+ "]---------------未启动成功，方法名设置错误！！！");
		} catch (SecurityException e) {
			log.error("执行任务出错2" + scheduleJob.getJobName()
					+ e.getLocalizedMessage());
		}
		if (method != null) {
			try {
				method.invoke(object);
			} catch (IllegalAccessException e) {
				log.error("执行任务出错3" + scheduleJob.getJobName()
						+ e.getLocalizedMessage());
			} catch (IllegalArgumentException e) {
				log.error("执行任务出错4" + scheduleJob.getJobName()
						+ e.getLocalizedMessage());
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				log.error("执行任务出错5" + scheduleJob.getJobName()
						+ e.getLocalizedMessage());
			}
		}

	}
	
	
	public static void main(String[] args) {
		Random r=new Random();
		for(int i=0;i<1000;i++){
			double d=Math.random()*(Math.random()>0.5?1:-1)/10000;
			System.out.println(d );
		}
	}
}
