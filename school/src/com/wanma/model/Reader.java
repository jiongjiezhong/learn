package com.wanma.model;

import java.math.BigDecimal;

public class Reader {
	private int readerId;//阅读器id
	private String readerName;//阅读器名字
//	private java.math.BigDecimal longitude; // 经度
//	private java.math.BigDecimal latitude; // 纬度
	private String creatTime;//阅读器创建时间
	private BigDecimal longitude;// 经度
	private BigDecimal latitude;// 纬度


	public int getReaderId() {
		return readerId;
	}
	public void setReaderId(int readerId) {
		this.readerId = readerId;
	}
	@Override
	public String toString() {
		return "Reader [readerId=" + readerId + ", readerName=" + readerName
				+ ", longitude=" + longitude + ", latitude=" + latitude
				+ ", creatTime=" + creatTime + "]";
	}
	public String getReaderName() {
		return readerName;
	}
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}

	public String getCreatTime() {
		return creatTime;
	}
	
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	
}
