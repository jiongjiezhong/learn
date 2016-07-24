package com.wanma.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;










import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import circle.Circle;
import circle.Point;

import com.wanma.common.JsonUtil;
import com.wanma.model.Monitor;
import com.wanma.model.MonitorHistory;
import com.wanma.model.Student;
import com.wanma.service.MonitorHistoryService;
import com.wanma.service.MonitorInfoService;
import com.wanma.service.StudentInfoService;



@Controller
@RequestMapping("/web/map")
public class MapController{
	@Autowired
	private StudentInfoService studentInfoService;
	@Autowired
	private MonitorInfoService monitorInfoService;
	@Autowired
	private MonitorHistoryService monitorHistoryService;
	/**
	 * 地图
	 */
	@RequestMapping("mapDetail")
	public String index(@ModelAttribute  Student student, Model model){
		return "backstage/mapInfo/map";
	}
	/**
	 * 地图信息查询
	 */
	@RequestMapping("getMonitorForMap")
	@ResponseBody
	public String getSchoolForMap(HttpServletRequest request,@RequestParam Map<String, Object> params){
		List<Monitor> monitorList = monitorInfoService.selectBindMonitor();
		return JsonUtil.object2json(monitorList);
		
	}
	/**
	 * 获取坐标
	 */
	@RequestMapping("newMonitorForMap")
	@ResponseBody
	public String newMonitorForMap(HttpServletRequest request,@RequestParam Map<String, Object> params){
		Circle c1 = new Circle(), c2 = new Circle(),c3=new Circle();
		Point p1 = new Point(), p2 = new Point(),p3=new Point();
		return JsonUtil.object2json(null);
	}
	/**
	 * 获取点的历史坐标
	 */
	@RequestMapping("getMonitorHistoryById")
	@ResponseBody
	public String getMonitorHistoryById(HttpServletRequest request,@ModelAttribute Monitor monitor){
		MonitorHistory mHistory = new MonitorHistory();
		mHistory.setMonitorId(monitor.getMonitorId());
		List<MonitorHistory> monitorHistoryList = monitorHistoryService.getMonitorHistoryById(mHistory);
		return JsonUtil.object2json(monitorHistoryList);
		
	}
}
