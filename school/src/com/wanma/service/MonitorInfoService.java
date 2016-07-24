package com.wanma.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.Monitor;
import com.wanma.model.Reader;

public interface MonitorInfoService {
	public List<Monitor> selectMonitor(Monitor monitor);
	public int updateMonitor(Monitor monitor);
	public int addMonitor(Monitor monitor);
	public int deleteMonitor(Monitor monitor);
	public long getMonitorCount();
	public Monitor findMonitorById(Monitor monitor);
	public List<Monitor> selectUnbindMonitor();
	public List<Monitor> selectBindMonitor();
	public void initMonitors();


}
