package com.wanma.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.Reader;


public interface ReaderInfoMapper {
	public List<Reader> selectReader(Reader reader);
	public int updateReader(Map<Object, Object> params);
	public int addReader(Map<String, Object> params);
	public int deleteReader(Map<String, Object> params);
	public long getReaderCount();
	public Reader findReaderById(int readerId);
}
