package com.wanma.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.Student;

public interface StudentInfoService {
	public List<Student> selectStudent(Student student);
	public int updateStudent(Student student);
	public int addStudent(Student student);
	public int deleteStudent(Map<String, Object> params);
	public long getStudentCount();
	public Student findStudentById(Student student);
	



}
