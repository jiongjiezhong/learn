package com.wanma.dao;

import java.util.List;

import com.wanma.model.ReaderHistory;

public interface ReaderHistoryMapper {

	public int addReaderHistory(ReaderHistory readerHistory);
	
	public List<ReaderHistory> selectReaderHistorys(ReaderHistory readerHistory);

}
