package com.wanma.service;

import java.util.List;

import com.wanma.model.ReaderHistory;

public interface ReaderHistoryService {
	public int addReaderHistory(ReaderHistory readerHistory);

	public List<ReaderHistory> selectReaderHistorys(ReaderHistory readerHistory);

}
