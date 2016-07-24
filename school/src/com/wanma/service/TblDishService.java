package com.wanma.service;

import java.util.List;

import com.wanma.model.TblDish;

public interface TblDishService {
	public List<TblDish> findPage(TblDish tblDish);

	public long findPageCount(TblDish tblDish);

	public int save(TblDish tblDish);

	public TblDish findById(Long pkDish);

	public int update(TblDish tblDish);

	public int remove(Long pkDish);

}
