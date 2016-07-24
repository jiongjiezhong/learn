package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.product.common.ResultResponse;
import com.product.server.ChatServerPool;
import com.wanma.dao.MonitorHistoryMapper;
import com.wanma.dao.MonitorInfoMapper;
import com.wanma.model.Monitor;
import com.wanma.model.MonitorHistory;
import com.wanma.service.MonitorHistoryService;
@Service
public class MonitorHistoryServiceImpl implements MonitorHistoryService{
	@Autowired
	private MonitorInfoMapper monitorMapper;
	@Autowired
	private MonitorHistoryMapper monitorHistoryMapper;
	
	@Override
	public int addMonitorHistory(MonitorHistory mHistory) {
		Monitor monitor=new Monitor();
		monitor.setMonitorId(mHistory.getMonitorId());
		monitor.setLongitude(mHistory.getLongitude());
		monitor.setLatitude(mHistory.getLatitude());
		monitor.setStatus("1");
		monitorMapper.updateMonitor(monitor);
		ChatServerPool.sendMessage(new ResultResponse(monitor).toString());
		return monitorHistoryMapper.addMonitorHistory(mHistory);
	}

	@Override
	public List<MonitorHistory> getMonitorHistoryById(MonitorHistory mHistory) {
		return monitorHistoryMapper.getMonitorHistoryById(mHistory);
	}

}
