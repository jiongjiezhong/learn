package com.wanma.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.Student;
import com.wanma.model.Teacher;

public interface TeacherInfoMapper {
	public List<Teacher> selectTeacher(Teacher teacher);
	public long getTeacherCount();
	public int updateTeacher(Teacher teacher);
	public int addTeacher(Teacher teacher);
	public int deleteTeacher(Map<String, Object> params);
	public Teacher findTeacherById(int teacherId);
}
