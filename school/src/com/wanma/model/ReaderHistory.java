package com.wanma.model;

import java.math.BigDecimal;
import java.util.Date;

public class ReaderHistory {
	private int rhId;
	private int monitorId;//卡id
	private BigDecimal distance; // 距离
	private int  readerId;//阅读器id
	private Date updateTime;//更新时间
	
	//填充字段
	private BigDecimal longitude;// 经度
	private BigDecimal latitude;// 纬度

	public int getRhId() {
		return rhId;
	}
	public void setRhId(int rhId) {
		this.rhId = rhId;
	}
	public BigDecimal getDistance() {
		return distance;
	}
	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
	public int getMonitorId() {
		return monitorId;
	}
	public void setMonitorId(int monitorId) {
		this.monitorId = monitorId;
	}
	public int getReaderId() {
		return readerId;
	}
	public void setReaderId(int readerId) {
		this.readerId = readerId;
	}
	@Override
	public String toString() {
		return "ReaderHistory [rhId=" + rhId + ", monitorId=" + monitorId
				+ ", distance=" + distance + ", readerId=" + readerId
				+ ", updateTime=" + updateTime + ", longitude=" + longitude
				+ ", latitude=" + latitude + "]";
	}
	


	
}
