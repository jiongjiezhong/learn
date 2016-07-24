package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.ReaderInfoMapper;
import com.wanma.dao.StudentInfoMapper;
import com.wanma.model.Reader;
import com.wanma.service.ReaderInfoService;
@Service
public class ReaderInfoServiceImpl implements ReaderInfoService{
	@Autowired
	ReaderInfoMapper  ReaderInfoMapper;
	@Override
	public List<Reader> selectReader(Reader reader) {
		
		return ReaderInfoMapper.selectReader(reader);
	}

	@Override
	public int updateReader(Map<Object, Object> params) {
		
		return ReaderInfoMapper.updateReader(params);
	}

	@Override
	public int addReader(Map<String, Object> params) {
		
		return ReaderInfoMapper.addReader(params);
	}

	@Override
	public int deleteReader(Map<String, Object> params) {
		
		return ReaderInfoMapper.deleteReader(params);
	}

	@Override
	public long getReaderCount() {
		
		return ReaderInfoMapper.getReaderCount();
	}

	@Override
	public Reader findReaderById(Reader reader) {
		
		return ReaderInfoMapper.findReaderById(reader.getReaderId());
	}

}
