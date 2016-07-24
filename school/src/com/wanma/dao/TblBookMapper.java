package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblBook;

public interface TblBookMapper {
	public List<TblBook> findPage(TblBook tblBook);
	public long findPageCount(TblBook tblBook);
	public int save(TblBook tblBook);
	public TblBook findById(Long pkBook);
	public int update(TblBook tblBook);
	public int remove(Long pkBook);
}
