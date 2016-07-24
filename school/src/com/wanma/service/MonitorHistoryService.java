package com.wanma.service;

import java.util.List;

import com.wanma.model.MonitorHistory;

public interface MonitorHistoryService {
	public int addMonitorHistory(MonitorHistory mHistory);
	public List<MonitorHistory> getMonitorHistoryById(MonitorHistory mHistory);
}
