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
import com.wanma.model.Reader;
import com.wanma.model.TblUser;
import com.wanma.model.Student;
import com.wanma.model.Teacher;
import com.wanma.service.MonitorInfoService;
import com.wanma.service.ReaderInfoService;
import com.wanma.service.StudentInfoService;
import com.wanma.service.TeacherInfoService;
import com.wanma.service.impl.StudentInfoServiceImpl;

@Controller	
@RequestMapping("/admin/equipment")
public class CmsEquipInfoController extends BaseController {
	private static Logger log = Logger.getLogger(CmsUserInfoController.class);
	@Autowired
	private ReaderInfoService ReaderInfoService;
	@Autowired
	private MonitorInfoService MonitorInfoService;
	/**
	 * 取得阅读器列表
	 */
	@RequestMapping(value = "/readerList")
	public String getReaderList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute Reader  reader,@ModelAttribute TblUser  user, Model model,
			HttpServletRequest request) {
		// 登录用户信息
		List<Reader> readerList = ReaderInfoService.selectReader(reader);
		long total = ReaderInfoService.getReaderCount();
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		user.setPager(pager);
		pager.setTotal(total);
		// 将用户信息放到会话中
		model.addAttribute("user", user);
		model.addAttribute("pager", pager);
		model.addAttribute("readerList", readerList);
		// 跳转至管理员主页面
		return "backstage/readerInfo/listReaderInfo";
	}
	
	/**
	 * 跳转添加阅读器页面
	 */
	@RequestMapping(value = "/newReader")
	public String newReader(Model model) {
		// 跳转至阅读器添加页面
		return "backstage/readerInfo/newReaderInfo";
	}
	/**
	 * 添加阅读器
	 */
	@RequestMapping(value = "/addReader")
	public String addReader(HttpServletRequest request) {
		DwzAjaxResult dwzResult;
		 dwzResult = new DwzAjaxResult("200", "ok", "userStudentList",
					"closeCurrent", "");
			try {
				String readerName = request.getParameter("readerName");
				String longitude = request.getParameter("longitude");
				String latitude = request.getParameter("latitude");
				String creatTime = request.getParameter("creatTime");

				Map params = new HashMap();
				params.put("readerName", readerName);
				params.put("longitude", longitude);
				params.put("latitude", latitude);
				params.put("creatTime", creatTime);
				ReaderInfoService.addReader(params);
				// 设置成功并返回用户一览画面信息
				dwzResult = new DwzAjaxResult("200", "保存成功", "equipment",
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
	 * 阅读器编辑初始化处理
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
	@RequestMapping(value = "/editReader", method = RequestMethod.GET)
	public String editUser(@RequestParam("pkUserinfo") Integer pkUserinfo, Model model) {
		Reader reader = new Reader();
		reader.setReaderId(pkUserinfo);
		// 取得编辑对象用户信息
		reader = ReaderInfoService.findReaderById(reader);
		model.addAttribute("reader", reader);
		
		// 跳转至用户编辑页面
		return "backstage/readerInfo/editReaderInfo";
	}
	/**
	 * 阅读器编辑处理
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
	@RequestMapping(value = "/modifyReader")
	@ResponseBody
	public String modifyReader(HttpServletRequest request)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		try {
			Map params = new HashMap();
			String readerId = request.getParameter("readerId");
			String readerName = request.getParameter("readerName");
			String longitude = request.getParameter("longitude");
			String latitude = request.getParameter("latitude");
			params.put("readerId", readerId);
			params.put("readerName", readerName);
			params.put("longitude", longitude);
			params.put("latitude", latitude);
			ReaderInfoService.updateReader(params);
			// 设置成功并返回用户一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "equipment",
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
	 * 取得监控卡列表
	 */
	@RequestMapping(value = "/monitorList")
	public String getMonitorList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute Monitor  monitor,@ModelAttribute TblUser  user, Model model,
			HttpServletRequest request) {
		List<Monitor> monitorList = MonitorInfoService.selectMonitor(monitor);
		long total = MonitorInfoService.getMonitorCount();
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		user.setPager(pager);
		pager.setTotal(total);
		// 将用户信息放到会话中
		model.addAttribute("pager", pager);
		model.addAttribute("monitorList", monitorList);
		// 跳转至管理员主页面
		return "backstage/monitorInfo/listMonitorInfo";
	}
	/**
	 * 跳转添加监控卡页面
	 */
	@RequestMapping(value = "/newMonitor")
	public String newMonitor(Model model) {
		// 跳转至阅读器添加页面
		return "backstage/monitorInfo/newMonitorInfo";
	}
	/**
	 * 添加监控卡
	 */
	@RequestMapping(value = "/addMonitor")
	public String addMonitor(@ModelAttribute Monitor monitor,HttpServletRequest request) {
		DwzAjaxResult dwzResult;
			try {
				monitor.setmCode(monitor.getmCode());
				monitor.setLongitude(monitor.getLongitude());
				monitor.setLatitude(monitor.getLatitude());
				MonitorInfoService.addMonitor(monitor);
				// 设置成功并返回用户一览画面信息
				dwzResult = new DwzAjaxResult("200", "保存成功", "monitorList",
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
	 * 监控卡删除
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
	@RequestMapping(value = "/delectMonitor", method = RequestMethod.GET)
	public String delectMonitor(@ModelAttribute Monitor monitor, @RequestParam("pkUserinfo") Integer pkUserinfo, Model model, HttpServletRequest request) {	
		DwzAjaxResult dwzResult = null;
		monitor.setMonitorId(pkUserinfo);
		Monitor monitor1 =MonitorInfoService.findMonitorById(monitor);
		String status = monitor1.getStatus();
		if(status  == "1"){
			dwzResult = new DwzAjaxResult("300", "删除失败，该卡已被绑定", "monitorList",
					"closeCurrent", "");
		}else{
			MonitorInfoService.deleteMonitor(monitor);
			dwzResult = new DwzAjaxResult("200", "删除成功", "monitorList",
					"closeCurrent", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
		
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 监控卡编辑处理
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
	@RequestMapping(value = "/modifyMonitor")
	@ResponseBody
	public String modifyMonitor(HttpServletRequest request)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		try {
			Map params = new HashMap();
			String readerId = request.getParameter("readerId");
			String readerName = request.getParameter("readerName");
			String longitude = request.getParameter("longitude");
			String latitude = request.getParameter("latitude");
			params.put("readerId", readerId);
			params.put("readerName", readerName);
			params.put("longitude", longitude);
			params.put("latitude", latitude);
			ReaderInfoService.updateReader(params);
			// 设置成功并返回用户一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "equipment",
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
