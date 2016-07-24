package com.wanma.model;

import com.product.model.common.BasicListModel;

public class TblDish extends BasicListModel {
	private Long pkDish;
	private String dishName;
	private Double dishPrice;
	private Integer dishLeibie;
	private Integer dishSeason;

	

	public Integer getDishLeibie() {
		return dishLeibie;
	}

	public void setDishLeibie(Integer dishLeibie) {
		this.dishLeibie = dishLeibie;
	}

	public Integer getDishSeason() {
		return dishSeason;
	}

	public void setDishSeason(Integer dishSeason) {
		this.dishSeason = dishSeason;
	}

	public Long getPkDish() {
		return pkDish;
	}

	public void setPkDish(Long pkDish) {
		this.pkDish = pkDish;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public Double getDishPrice() {
		return dishPrice;
	}

	public void setDishPrice(Double dishPrice) {
		this.dishPrice = dishPrice;
	}

}
