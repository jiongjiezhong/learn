package com.wanma.model;

import java.math.BigDecimal;
import java.util.Date;

public class MonitorHistory {
	private int mhId;
	private int monitorId;//卡id
	private BigDecimal longitude; //经度
	private BigDecimal latitude; // 纬度
	private Date updateTime;//更新时间
	private String readers;//监控点所依据的阅读器
	public int getMhId() {
		return mhId;
	}
	public void setMhId(int mhId) {
		this.mhId = mhId;
	}
	public int getMonitorId() {
		return monitorId;
	}
	public void setMonitorId(int monitorId) {
		this.monitorId = monitorId;
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

	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getReaders() {
		return readers;
	}
	public void setReaders(String readers) {
		this.readers = readers;
	}
	@Override
	public String toString() {
		return "MonitorHistory [mhId=" + mhId + ", monitorId=" + monitorId
				+ ", longitude=" + longitude + ", latitude=" + latitude
				+ ", updateTime=" + updateTime + ", readers=" + readers + "]";
	}



	
}
