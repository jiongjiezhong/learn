package com.wanma.dao;

import java.util.List;

import com.wanma.model.MonitorHistory;

public interface MonitorHistoryMapper {

	public int addMonitorHistory(MonitorHistory mHistory);
	public List<MonitorHistory> getMonitorHistoryById(MonitorHistory mHistory);
}
