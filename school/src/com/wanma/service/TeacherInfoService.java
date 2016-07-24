package com.wanma.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.Student;
import com.wanma.model.Teacher;

public interface TeacherInfoService {
	public List<Teacher> selectTeacher(Teacher teacher);
	public int updateTeacher(Teacher teacher);
	public int addTeacher(Teacher teacher);
	public int deleteTeacher(Map<String, Object> params);
	public long getTeacherCount();
	public Teacher findTeacherById(Teacher teacher);
}
