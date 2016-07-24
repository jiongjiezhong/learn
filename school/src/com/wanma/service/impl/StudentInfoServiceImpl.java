package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.MonitorInfoMapper;
import com.wanma.dao.StudentInfoMapper;
import com.wanma.model.Monitor;
import com.wanma.model.Student;
import com.wanma.service.StudentInfoService;

@Service
public class StudentInfoServiceImpl implements StudentInfoService{
	@Autowired
	StudentInfoMapper  studentInfoMapper;
	@Autowired
	MonitorInfoMapper  monitorInfoMapper;
	
	@Override
	public List<Student> selectStudent(Student student) {
		return studentInfoMapper.selectStudent(student);
	}
	@Override
	public int updateStudent(Student student) {
		return studentInfoMapper.updateStudent(student);
	}
	@Override
	public int addStudent(Student student) {
		Monitor monitor = new Monitor();
		monitor.setmCode(student.getMonitorCode());
		monitorInfoMapper.updateMonitorStatus(monitor);
		return studentInfoMapper.addStudent(student);
	}
	@Override
	public int deleteStudent(Map<String, Object> params) {
		return studentInfoMapper.deleteStudent(params);
	}
	@Override
	public long getStudentCount() {
		return studentInfoMapper.getStudentCount();
	}
	@Override
	public Student findStudentById(Student student) {
		return studentInfoMapper.findStudentById(student.getStudentId());
	}
	
	


	

}