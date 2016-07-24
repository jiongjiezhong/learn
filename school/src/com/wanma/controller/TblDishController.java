/**
 * FileName:dishController.java
 * Author: Administrator
 * Create: 2014年7月4日
 * Last Modified: 2014年7月4日
 * Version: V1.0 
 */
package com.wanma.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.product.model.common.DwzAjaxResult;
import com.product.model.common.DwzPagerMySQL;
import com.product.utils.JsonObject;
import com.wanma.model.TblDish;
import com.wanma.service.TblDishService;

/**
 * 书本相关处理控制器
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月4日
 */

@Controller
@RequestMapping("/admin/dish")
public class TblDishController {
		
	// 日志输出对象
	private static Logger log = Logger.getLogger(TblDishController.class);
	/** 书本业务处理对象 */
	@Autowired
	private TblDishService dishService;

	/**
	 * 书本列表
	 * @author wbc
	 */
	@RequestMapping("/findDishList")
	public String findDishList(DwzPagerMySQL pager,TblDish dish, Model model) {
		List<TblDish> dishList = null;
		// 取得书本列表
		long total = dishService.findPageCount(dish);
		if(total<=pager.getOffset()){
			pager.setPageNum(1L);
		}
		dish.setPager(pager);
		
		dishList = dishService.findPage(dish);
		pager.setTotal(total);
		// 将书本信息放到会话中
		model.addAttribute("dishList", dishList);
		model.addAttribute("dish", dish);
		model.addAttribute("pager", pager);
		// 跳转至书本主页面
		return "admin/dish/listDish";
	}

	/**
	 * 书本添加初始化处理
	 * 
	 */
	@RequestMapping(value = "/newDish")
	public String newDish(ModelMap map, HttpServletRequest request) {
		// 跳转至书本添加页面
		return "admin/dish/newDish";
	}

	/**
	 * 书本添加处理
	 */
	@RequestMapping(value = "/saveDish")
	@ResponseBody
	public String saveDish(@ModelAttribute("TblDish") TblDish tblDish,
			BindingResult result, HttpServletRequest request, Model model) throws IOException {
		System.out.println(request.getParameter("dishName"));
		// 处理结果信息
		DwzAjaxResult dwzResult;
		try {
			dishService.save(tblDish);
			dwzResult = new DwzAjaxResult("200", "保存成功", "dish", "closeCurrent", "");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "dish", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	


	/**
	 * 书本编辑初始化处理
	 */
	@RequestMapping(value = "/editDish")
	public String editDish(@RequestParam("pkDish") String pkDish, Model model,
			HttpServletRequest request, HttpSession session) {
		TblDish dish = dishService.findById(new Long(pkDish));
		model.addAttribute("dish", dish);
		// 跳转至书本编辑页面
		return "admin/dish/editDish";
		
	}

	/**
	 * 书本编辑处理
	 * 
	 */
	@RequestMapping(value = "/modifyDish")
	@ResponseBody
	public String modifydish(@ModelAttribute("TblDish") TblDish tblDish,
			Model model, HttpServletRequest request) throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult=null;
		try {
			dishService.update(tblDish);
			dwzResult = new DwzAjaxResult("200", "保存成功", "dish", "closeCurrent", "");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "dish", "", "");
		}
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 书本删除处理
	 * 
	 */
	@RequestMapping(value = "/removeDish")
	@ResponseBody
	public String removedish(@RequestParam("pkDish") String pkDish,HttpServletRequest request) {
		DwzAjaxResult dwzResult=null;
		dishService.remove(new Long(pkDish));
		dwzResult = new DwzAjaxResult("200", "删除成功", "dish", "", "");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
//	@RequestMapping(value = "/removeDish")
//	@ResponseBody
//	public String removedish(@ModelAttribute("TblDish") TblDish tblDish,HttpServletRequest request) {
//		DwzAjaxResult dwzResult=null;
//		dishService.remove(tblDish.getPkDish());
//		dwzResult = new DwzAjaxResult("200", "删除成功", "dish", "", "");
//		// 返回处理结果信息
//		return new JsonObject(dwzResult).toString();
//	}

	
}
