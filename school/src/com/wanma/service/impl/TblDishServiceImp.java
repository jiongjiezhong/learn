package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.TblDishMapper;
import com.wanma.model.TblDish;
import com.wanma.service.TblDishService;

@Service
public class TblDishServiceImp implements TblDishService{
	@Autowired
	private TblDishMapper dishDao;
	
	@Override
	public List<TblDish> findPage(TblDish tblDish) {
		return dishDao.findPage(tblDish);
	}

	@Override
	public long findPageCount(TblDish tblDish) {
		return dishDao.findPageCount(tblDish);
	}

	@Override
	public int save(TblDish tblDish) {
		return dishDao.save(tblDish);
	}

	@Override
	public TblDish findById(Long pkDish) {
		return dishDao.findById(pkDish);
	}

	@Override
	public int update(TblDish tblDish) {
		return dishDao.update(tblDish);
	}

	@Override
	public int remove(Long pkDish) {
		return dishDao.remove(pkDish);
	}

}
