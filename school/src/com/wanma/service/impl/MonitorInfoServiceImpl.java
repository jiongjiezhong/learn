package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.MonitorInfoMapper;
import com.wanma.model.Monitor;
import com.wanma.service.MonitorInfoService;
import com.wanma.service.ReaderInfoService;
@Service
public class MonitorInfoServiceImpl implements MonitorInfoService{
	@Autowired
	MonitorInfoMapper  MonitorInfoMapper;
	@Override
	public List<Monitor> selectMonitor(Monitor monitor) {
		
		return MonitorInfoMapper.selectMonitor(monitor);
	}
	@Override
	public List<Monitor> selectUnbindMonitor() {
		
		return MonitorInfoMapper.selectUnbindMonitor();
	}
	@Override
	public List<Monitor> selectBindMonitor() {
		
		return MonitorInfoMapper.selectBindMonitor();
	}
	@Override
	public int updateMonitor(Monitor monitor) {
		
		return MonitorInfoMapper.updateMonitor(monitor);
	}

	@Override
	public int addMonitor(Monitor monitor) {
		
		return MonitorInfoMapper.addMonitor(monitor);
	}

	@Override
	public int deleteMonitor(Monitor monitor) {
		
		return MonitorInfoMapper.deleteMonitor(monitor.getMonitorId());
	}

	@Override
	public long getMonitorCount() {
	
		return MonitorInfoMapper.getMonitorCount();
	}

	@Override
	public Monitor findMonitorById(Monitor monitor) {
		
		return MonitorInfoMapper.findMonitorById(monitor);
	}
	@Override
	public void initMonitors() {
		
	}
	

}
