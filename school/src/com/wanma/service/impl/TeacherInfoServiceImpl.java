package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.TeacherInfoMapper;
import com.wanma.model.Student;
import com.wanma.model.Teacher;
import com.wanma.service.TeacherInfoService;

@Service
public class TeacherInfoServiceImpl implements TeacherInfoService{
	@Autowired
	TeacherInfoMapper  teacherInfoMapper;
	@Override
	public List<Teacher> selectTeacher(Teacher teacher) {
		
		return teacherInfoMapper.selectTeacher(teacher);
	}

	@Override
	public int updateTeacher(Teacher teacher) {
		
		return teacherInfoMapper.updateTeacher(teacher);
	}

	@Override
	public int addTeacher(Teacher teacher) {
	
		return teacherInfoMapper.addTeacher(teacher);
	}
	@Override
	public long getTeacherCount() {
		return teacherInfoMapper.getTeacherCount();
	}
	@Override
	public int deleteTeacher(Map<String, Object> params) {
	
		return teacherInfoMapper.deleteTeacher(params);
	}
	@Override
	public Teacher findTeacherById(Teacher teacher) {
		return teacherInfoMapper.findTeacherById(teacher.getTeacherId());
	}

}
