package com.wanma.model;

import com.product.model.common.BasicListModel;

public class TblBook extends BasicListModel{
	private Long pkBook;
	private String bookName;
	private Double bookPrice;

	public Long getPkBook() {
		return pkBook;
	}

	public void setPkBook(Long pkBook) {
		this.pkBook = pkBook;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(Double bookPrice) {
		this.bookPrice = bookPrice;
	}

}