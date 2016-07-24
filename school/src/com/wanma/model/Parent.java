package com.wanma.model;

public class Parent {
	private int parentId;//家长id
	private String parentName;//家长名字
	private String parentSex;//家长性别
	private String parentPhone;//家长电话
	private String studentId;//孩子的id

	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getParentSex() {
		return parentSex;
	}
	public void setParentSex(String parentSex) {
		this.parentSex = parentSex;
	}
	public String getParentPhone() {
		return parentPhone;
	}
	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
}
