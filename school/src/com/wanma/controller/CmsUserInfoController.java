package com.wanma.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.product.common.BluemobiCommon;
import com.product.common.ProcessInfoCommon;
import com.product.controller.BaseController;
import com.product.model.RoleModel;
import com.product.model.common.DwzAjaxResult;
import com.product.model.common.DwzPagerMySQL;
import com.product.service.RoleService;
import com.product.service.TblUserService;
import com.product.utils.JsonObject;
import com.product.utils.MD5Util;
import com.wanma.common.WanmaConstants;
import com.wanma.model.Monitor;
import com.wanma.model.TblUser;
import com.wanma.model.Student;
import com.wanma.model.Teacher;
import com.wanma.service.MonitorInfoService;
import com.wanma.service.StudentInfoService;
import com.wanma.service.TeacherInfoService;
import com.wanma.service.impl.StudentInfoServiceImpl;

@Controller	
@RequestMapping("/admin/userManager")
public class CmsUserInfoController extends BaseController {
	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsUserInfoController.class);
	@Autowired
	private TeacherInfoService teacherInfoService;
	@Autowired
	private TblUserService tblUserService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private StudentInfoService StudentInfoService;
	@Autowired
	private MonitorInfoService  MonitorInfoService;
	
	/**
	 * 取得学生列表
	 */
	@RequestMapping(value = "/userStudentList")
	public String getUserList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute Student  student,@ModelAttribute TblUser  user, Model model,
			HttpServletRequest request) {
		// 登录用户信息
		List<Student> studentList = StudentInfoService.selectStudent(student);
		long total = StudentInfoService.getStudentCount();
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		user.setPager(pager);
		pager.setTotal(total);
		// 将用户信息放到会话中
		model.addAttribute("user", user);
		model.addAttribute("pager", pager);
		model.addAttribute("studentList", studentList);
		// 跳转至管理员主页面
		return "backstage/studentInfo/listStudentInfo";
	}

	/**
	 * 跳转添加学生页面
	 */
	@RequestMapping(value = "/newStudent")
	public String newUser(Model model) {
		TblUser user = new TblUser();
		Teacher teacher = new Teacher();
		List<Teacher> teacherList = teacherInfoService.selectTeacher(teacher);
		List<Monitor> MonitorList = MonitorInfoService.selectUnbindMonitor();
		// 将用户信息设置到画面显示对象
		model.addAttribute("user", user);
		model.addAttribute("teacherList", teacherList);
		model.addAttribute("MonitorList", MonitorList);
		// 跳转至用户添加页面
		return "backstage/studentInfo/newStudentInfo";
	}
	/**
	 * 添加学生
	 */
	@RequestMapping(value = "/addStudent")
	@ResponseBody
	public String addStudent(@ModelAttribute Student  student,HttpServletRequest request) {
		DwzAjaxResult dwzResult;
			try {
				 StudentInfoService.addStudent(student);
				// 设置成功并返回用户一览画面信息
				dwzResult = new DwzAjaxResult("200", "保存成功", "userStudentList",
						"closeCurrent", "");
				// 如果更新用户处理不成功
			} catch (Exception e) {
				// 出错日志
				log.error(e.getLocalizedMessage());

				// 设置处理错误信息
				dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "newStudent",
						"", "");
			}
		// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
	}

	/**
	 * 删除用户
	 * 
	 * @author xiay
	 * @param pkUserinfo
	 * @return
	 */
	@RequestMapping("/removeUser")
	@ResponseBody
	public String removeUser(TblUser user) {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		user.setUserStatus(3);
		try {
			// 执行删除处理
			TblUser tblUser1 = tblUserService.findUser(user);
			BigDecimal accountBalance = tblUser1.getNormAccountBalance();
			if (accountBalance == null || accountBalance.doubleValue() == 0.00) {
				int consumeCount = tblUserService.getUserConsumeCount(user);
				if (consumeCount == 0) {
					tblUserService.deleteUser(user);
					// 设置处理结果信息
					dwzResult = new DwzAjaxResult("200", "删除成功",
							"userManagerList", "", "");
				} else {
					dwzResult = new DwzAjaxResult("300",
							"删除失败:用户有过充值消费记录，不能删除", "userManagerList", "", "");
				}

			} else {
				dwzResult = new DwzAjaxResult("300", "删除失败:用户余额不为零",
						"userManagerList", "", "");
			}
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());

			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败:系统错误",
					"userManagerList", "", "");
		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}


	/**
	 * 学生编辑初始化处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param pkUserinfo
	 *            用户ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/editStudent", method = RequestMethod.GET)
	public String editUser(@RequestParam("pkUserinfo") Integer pkUserinfo, Model model) {
		Student student = new Student();
		student.setStudentId(pkUserinfo);
		// 取得编辑对象用户信息
		Teacher teacher = new Teacher();
		student = StudentInfoService.findStudentById(student);
		List<Teacher> teacherList = teacherInfoService.selectTeacher(teacher);
		model.addAttribute("student", student);
		model.addAttribute("teacherList", teacherList);
		
		// 跳转至用户编辑页面
		return "backstage/studentInfo/editStudentInfo";
	}

	/**
	 * 学生编辑处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param TblUser
	 *            用户输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/modifyUser")
	@ResponseBody
	public String modifyStudent(@ModelAttribute Student  student,HttpServletRequest request)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		try {
			StudentInfoService.updateStudent(student);
			// 设置成功并返回用户一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "userManagerList",
					"closeCurrent", "");
			// 如果更新用户处理不成功
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());

			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "userEditPage",
					"", "");
		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	
	/**
	 * 取得教师列表
	 */
	@RequestMapping(value = "/teacherList")
	public String getUserList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute Teacher  teacher,@ModelAttribute TblUser  user, Model model,
			HttpServletRequest request) {
		// 登录用户信息
		List<Teacher> teacherList = teacherInfoService.selectTeacher(teacher);
		long total = teacherInfoService.getTeacherCount();
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		user.setPager(pager);
		pager.setTotal(total);
		// 将用户信息放到会话中
		model.addAttribute("user", user);
		model.addAttribute("pager", pager);
		model.addAttribute("teacherList", teacherList);
		// 跳转至管理员主页面
		return "backstage/teacherInfo/listTeacherInfo";
	}
	
	/**
	 * 跳转添加教师页面
	 */
	@RequestMapping(value = "/newTeacher")
	public String newTeacher(Model model) {
		List<Monitor> monitorList = MonitorInfoService.selectUnbindMonitor();
		model.addAttribute("monitorList", monitorList);
		// 跳转至用户添加页面
		return "backstage/teacherInfo/newTeacherInfo";
	}

	/**
	 * 添加教师
	 */
	@RequestMapping(value = "/addTeacher")
	public String addTeacher(@ModelAttribute Teacher  teacher,HttpServletRequest request) {
		DwzAjaxResult dwzResult;
		 dwzResult = new DwzAjaxResult("200", "ok", "teacherList",
					"closeCurrent", "");
			try {
				teacherInfoService.addTeacher(teacher);
				// 设置成功并返回用户一览画面信息
				dwzResult = new DwzAjaxResult("200", "保存成功", "teacherList",
						"closeCurrent", "");
				// 如果更新用户处理不成功
			} catch (Exception e) {
				// 出错日志
				log.error(e.getLocalizedMessage());

				// 设置处理错误信息
				dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "newStudent",
						"", "");
			}
		// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
	}
	
	/**
	 * 教师编辑初始化处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param pkUserinfo
	 *            用户ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/editTeacher", method = RequestMethod.GET)
	public String editTeacher(@RequestParam("pkUserinfo") Integer pkUserinfo, Model model) {
		Teacher teacher = new Teacher();
		teacher.setTeacherId(pkUserinfo);
		// 取得编辑对象用户信息
		teacher = teacherInfoService.findTeacherById(teacher);
		model.addAttribute("teacher", teacher);
		// 跳转至用户编辑页面
		return "backstage/teacherInfo/editTeacherInfo";
	}

	/**
	 * 教师编辑处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param TblUser
	 *            用户输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/modifyTeacher")
	@ResponseBody
	public String modifyTeacher(@ModelAttribute Teacher teacher ,HttpServletRequest request)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		try {
			teacherInfoService.updateTeacher(teacher);
			// 设置成功并返回用户一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "userManagerList",
					"closeCurrent", "");
			// 如果更新用户处理不成功
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());

			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "userEditPage",
					"", "");
		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}


	
}
