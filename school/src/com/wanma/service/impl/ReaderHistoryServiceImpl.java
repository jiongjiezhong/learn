package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.ReaderHistoryMapper;
import com.wanma.model.ReaderHistory;
import com.wanma.service.ReaderHistoryService;
@Service
public class ReaderHistoryServiceImpl implements ReaderHistoryService{
	@Autowired
	private ReaderHistoryMapper readerHistoryMapper;

	@Override
	public int addReaderHistory(ReaderHistory readerHistory) {
		return readerHistoryMapper.addReaderHistory(readerHistory);
	}

	@Override
	public List<ReaderHistory> selectReaderHistorys(ReaderHistory readerHistory) {
		return readerHistoryMapper.selectReaderHistorys(readerHistory);
	}



}
