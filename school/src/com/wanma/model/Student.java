package com.wanma.model;

public class Student {
	private int studentId;//学生id
	private String studentName;//学生名字
	private String studentSex;//学生性别
	private String studentAge;//学生年纪
	private String studentGrade;//学生班级
	private String monitorCode;//学生绑定的监控卡号
	private String teacherId;//班主任Id
	private String teacherName;//班主任姓名
	private String parentName;//家长名字
	private String parentPhone;//家长电话
//	private java.math.BigDecimal longitude; // 经度
//	private java.math.BigDecimal latitude; // 纬度
	private String longitude; // 纬度
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	private String latitude; // 纬度

	public String getTeacherName() {
	return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}


	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentSex() {
		return studentSex;
	}
	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}
	public String getStudentAge() {
		return studentAge;
	}
	public void setStudentAge(String studentAge) {
		this.studentAge = studentAge;
	}
	public String getStudentGrade() {
		return studentGrade;
	}
	public void setStudentGrade(String studentGrade) {
		this.studentGrade = studentGrade;
	}
	
	public String getMonitorCode() {
		return monitorCode;
	}
	public void setMonitorCode(String monitorCode) {
		this.monitorCode = monitorCode;
	}

	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getParentPhone() {
		return parentPhone;
	}
	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName="
				+ studentName + ", studentSex=" + studentSex + ", studentAge="
				+ studentAge + ", studentGrade=" + studentGrade
				+ ", monitorCode=" + monitorCode + ", teacherId=" + teacherId
				+ ", teacherName=" + teacherName + ", parentName=" + parentName
				+ ", parentPhone=" + parentPhone + ", longitude=" + longitude
				+ ", latitude=" + latitude + "]";
	}
}
