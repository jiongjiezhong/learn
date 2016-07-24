package com.wanma.model;

import java.math.BigDecimal;

public class Monitor {
	private int monitorId;//监控卡id
	private String mCode;//卡的编号
	private String status;//卡的状态
	private BigDecimal longitude; //经度
	private BigDecimal latitude; // 纬度
	private String studentName; // 学生名字
	private String studentId; // 学生名字
	private String teacherName; // 老师名字
	private String teacherId; // 老师名字
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	public int getMonitorId() {
		return monitorId;
	}
	public void setMonitorId(int monitorId) {
		this.monitorId = monitorId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getmCode() {
		return mCode;
	}
	public void setmCode(String mCode) {
		this.mCode = mCode;
	}
	@Override
	public String toString() {
		return "Monitor [monitorId=" + monitorId + ", mCode=" + mCode
				+ ", status=" + status + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", studentName=" + studentName
				+ ", studentId=" + studentId + ", teacherName=" + teacherName
				+ ", teacherId=" + teacherId + "]";
	}

	

}
