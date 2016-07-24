package com.wanma.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.Monitor;


public interface MonitorInfoMapper {
	public List<Monitor> selectMonitor(Monitor monitor);
	public List<Monitor> selectUnbindMonitor();
	public int updateMonitor(Monitor monitor);
	public int updateMonitorStatus(Monitor monitor);
	public int deleteMonitor(int monitorId);
	public long getMonitorCount();
	public Monitor findMonitorById(Monitor monitor);
	public List<Monitor> selectBindMonitor();
	public int addMonitor(Monitor monitor);
}
