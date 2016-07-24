package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.TblBookMapper;
import com.wanma.model.TblBook;
import com.wanma.service.TblBookService;

@Service
public class TblBookServiceImpl implements TblBookService{
	@Autowired
	private TblBookMapper bookDao;
	
	@Override
	public List<TblBook> findPage(TblBook tblBook) {
		return bookDao.findPage(tblBook);
	}

	@Override
	public long findPageCount(TblBook tblBook) {
		return bookDao.findPageCount(tblBook);
	}

	@Override
	public int save(TblBook tblBook) {
		return bookDao.save(tblBook);
	}

	@Override
	public TblBook findById(Long pkBook) {
		return bookDao.findById(pkBook);
	}

	@Override
	public int update(TblBook tblBook) {
		return bookDao.update(tblBook);
	}

	@Override
	public int remove(Long pkBook) {
		return bookDao.remove(pkBook);
	}

}
