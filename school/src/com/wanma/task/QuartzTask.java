/**     
 * @Title:  DataConversionTask.java   
 * @Package com.wanma.task   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2015年11月26日 上午9:44:23   
 * @version V1.0     
 */
package com.wanma.task;

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import circle.Caculate;
import circle.Circle;
import circle.Point;

import com.product.utils.SpringContextHolder;
import com.wanma.model.Monitor;
import com.wanma.model.MonitorHistory;
import com.wanma.model.Reader;
import com.wanma.model.ReaderHistory;
import com.wanma.service.impl.MonitorHistoryServiceImpl;
import com.wanma.service.impl.MonitorInfoServiceImpl;
import com.wanma.service.impl.ReaderHistoryServiceImpl;
import com.wanma.service.impl.ReaderInfoServiceImpl;

/**
 * @author bc
 *
 */
public class QuartzTask {
	private final Logger log = Logger.getLogger(this.getClass());
	private static List<Reader> readerList;
	private static List<Monitor> monitorList;
	private static GeneralPath path;
	private static MonitorInfoServiceImpl monitorInfoService;
	private static MonitorHistoryServiceImpl monitorHistoryService;
	private static ReaderHistoryServiceImpl readerHistoryService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

	public void run1() {
		if (monitorInfoService == null) {
			init();
		}
		if(monitorList!=null&&!monitorList.isEmpty()){
			for (Monitor monitor : monitorList) {
				//随机设置点是否移动
				if(Math.random() > 0.5 ){
					setReaderMonitorDistance(monitor);
				}
			}
		}
		
	}

	public void run2() {
		ReaderHistory readerHistory=new ReaderHistory();
		readerHistory.setUpdateTime(new Date(new Date().getTime()-5000));
		System.out.println(sdf.format(readerHistory.getUpdateTime()));
		System.out.println(readerHistoryService);
		List<ReaderHistory> readerHistoryList=null;
		if(readerHistoryService!=null){
			readerHistoryList=readerHistoryService.selectReaderHistorys(readerHistory);
		}
		if(readerHistoryList!=null&&!readerHistoryList.isEmpty()){
			int monitorId=readerHistoryList.get(0).getMonitorId();
			int temp;
			List<ReaderHistory> tempList= new ArrayList<ReaderHistory>();
			for(ReaderHistory history:readerHistoryList){
				temp=history.getMonitorId();
				if(monitorId!=temp){
					//更新监控器历史
					MonitorHistory monitorHistory=getPointByReaderHistory(tempList);
					if(monitorHistory!=null){
						monitorHistoryService.addMonitorHistory(monitorHistory);
					}
					monitorId=temp;
					tempList=new ArrayList<ReaderHistory>();
				}
				tempList.add(history);
			}
			MonitorHistory monitorHistory=getPointByReaderHistory(tempList);
			if(monitorHistory!=null){
				monitorHistoryService.addMonitorHistory(monitorHistory);
			}
		}
	}
	
	
	private void setReaderMonitorDistance(Monitor monitor){
		ReaderHistory readerHistory = null;
		//监控点随机移动距离
		monitor=getRandomMonitor(monitor);
		Point p=new Point();
		//移动后的点的X,Y
		p.x = monitor.getLongitude().doubleValue();
		p.y = monitor.getLatitude().doubleValue();
		//三点定位的算法
		Caculate c = new Caculate();
		for (Reader reader : readerList) {
			System.out.println(reader);
			Point pi = new Point();
			pi.x = reader.getLongitude().doubleValue();
			pi.y= reader.getLatitude().doubleValue();
			readerHistory = new ReaderHistory();
			readerHistory.setUpdateTime(new Date());
			double r =c.distance(p, pi);
			System.out.println(r);
			readerHistory.setDistance( new BigDecimal(r).setScale(14,BigDecimal.ROUND_DOWN));
			readerHistory.setReaderId(reader.getReaderId());
			readerHistory.setMonitorId(monitor.getMonitorId());;
			readerHistoryService.addReaderHistory(readerHistory);
		}
	}

	private MonitorHistory getPointByReaderHistory(List<ReaderHistory> list) {
		if(list==null||list.isEmpty()){return null;}
		MonitorHistory monitor=new MonitorHistory();
		int i=0;
		String readers = "";
		Circle[] circles = new Circle[list.size()] ;
		Point p=new Point();
		Caculate c = new Caculate();
		for (ReaderHistory reader : list) {
			readers += reader.getReaderId();
			Point pi = new Point();
			Circle ci = new Circle();
			pi.x = reader.getLongitude().doubleValue();
			pi.y= reader.getLatitude().doubleValue();
			ci.center = pi;
			ci.r= reader.getDistance().doubleValue();
			circles[i] = ci;
			i++;
		}
		p = c.getSddwPoint(circles);
		if(p==null){
			return null;
		}
		monitor.setMonitorId(list.get(0).getMonitorId());
		monitor.setLongitude(new BigDecimal(p.x).setScale(7,BigDecimal.ROUND_DOWN));
		monitor.setLatitude(new BigDecimal(p.y).setScale(7,BigDecimal.ROUND_DOWN));
		monitor.setUpdateTime(new Date());
		monitor.setReaders(readers);
		return monitor;
	}
	
	
	//随机改变监控卡的点的位置
	private Monitor getRandomMonitor(Monitor monitor) {
		Point2D.Double point = new Point2D.Double();
		double longtitue, latitude, d;
		do {
			point.setLocation(monitor.getLongitude().doubleValue(), monitor
					.getLatitude().doubleValue());
			longtitue = monitor.getLongitude().doubleValue();
			latitude = monitor.getLatitude().doubleValue();
			d = Math.random() * (Math.random() > 0.5 ? 1 : -1) / 1000;
			longtitue += d;
			monitor.setLongitude(new BigDecimal(longtitue).setScale(7,
					BigDecimal.ROUND_DOWN));
			d = Math.random() * (Math.random() > 0.5 ? 1 : -1) / 10000;
			latitude += d;
			monitor.setLatitude(new BigDecimal(latitude).setScale(7,
					BigDecimal.ROUND_DOWN));
		} while (!path.contains(point));
		return monitor;
	}

	private void init() {
		ApplicationContext context = SpringContextHolder.getSpringContext();
		ReaderInfoServiceImpl readerInfoService = (ReaderInfoServiceImpl) context
				.getBean("readerInfoServiceImpl");
		readerList = readerInfoService.selectReader(null);
		path = new GeneralPath();
		path.moveTo(readerList.get(0).getLongitude().doubleValue(), readerList
				.get(0).getLatitude().doubleValue());
		for (Reader reader : readerList) {
			path.lineTo(reader.getLongitude().doubleValue(), reader
					.getLatitude().doubleValue());
		}
		path.closePath();
		// 初始化学生
		monitorInfoService = (MonitorInfoServiceImpl) context
				.getBean("monitorInfoServiceImpl");
		//监控点记录
		monitorHistoryService = (MonitorHistoryServiceImpl) context
				.getBean("monitorHistoryServiceImpl");
		//阅读器
		readerHistoryService = (ReaderHistoryServiceImpl) context
				.getBean("readerHistoryServiceImpl");
		monitorList = monitorInfoService.selectBindMonitor();
		Point2D.Double point = new Point2D.Double();
		point.setLocation(monitorList.get(0).getLongitude().doubleValue(),
				monitorList.get(0).getLatitude().doubleValue());
		if (!path.contains(point)) {
			monitorInfoService.initMonitors();
		}
	}

}
