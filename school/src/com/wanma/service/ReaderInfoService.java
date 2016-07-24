package com.wanma.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.Reader;

public interface ReaderInfoService {
	public List<Reader> selectReader(Reader reader);
	public int updateReader(Map<Object, Object> params);
	public int addReader(Map<String, Object> params);
	public int deleteReader(Map<String, Object> params);
	public long getReaderCount();
	public Reader findReaderById(Reader reader);
	



}
