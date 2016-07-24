/**
 * FileName:bookController.java
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
import com.wanma.model.TblBook;
import com.wanma.service.TblBookService;

/**
 * 书本相关处理控制器
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月4日
 */

@Controller
@RequestMapping("/admin/book")
public class TblBookController {
		
	// 日志输出对象
	private static Logger log = Logger.getLogger(TblBookController.class);
	/** 书本业务处理对象 */
	@Autowired
	private TblBookService bookService;

	/**
	 * 书本列表
	 * @author wbc
	 */
	@RequestMapping("/findBookList")
	public String findBookList(DwzPagerMySQL pager,TblBook book, Model model) {
		List<TblBook> bookList = null;
		// 取得书本列表
		long total = bookService.findPageCount(book);
		if(total<=pager.getOffset()){
			pager.setPageNum(1L);
		}
		
		book.setPager(pager);
		bookList = bookService.findPage(book);
		pager.setTotal(total);
		// 将书本信息放到会话中
		model.addAttribute("bookList", bookList);
		model.addAttribute("book", book);
		model.addAttribute("pager", pager);
		// 跳转至书本主页面
		return "admin/book/listBook";
	}

	/**
	 * 书本添加初始化处理
	 * 
	 */
	@RequestMapping(value = "/newBook")
	public String newBook(ModelMap map, HttpServletRequest request) {
		// 跳转至书本添加页面
		return "admin/book/newBook";
	}

	/**
	 * 书本添加处理
	 */
	@RequestMapping(value = "/saveBook")
	@ResponseBody
	public String saveBook(@ModelAttribute("TblBook") TblBook tblBook,
			BindingResult result, HttpServletRequest request, Model model) throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		try {
			bookService.save(tblBook);
			dwzResult = new DwzAjaxResult("200", "保存成功", "book", "closeCurrent", "");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "book", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	


	/**
	 * 书本编辑初始化处理
	 */
	@RequestMapping(value = "/editBook")
	public String editBook(@RequestParam("pkBook") String pkBook, Model model,
			HttpServletRequest request, HttpSession session) {
		TblBook book = bookService.findById(new Long(pkBook));
		model.addAttribute("book", book);
		// 跳转至书本编辑页面
		return "admin/book/editBook";
		
	}

	/**
	 * 书本编辑处理
	 * 
	 */
	@RequestMapping(value = "/modifyBook")
	@ResponseBody
	public String modifybook(@ModelAttribute("TblBook") TblBook tblBook,
			Model model, HttpServletRequest request) throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult=null;
		try {
			bookService.update(tblBook);
			dwzResult = new DwzAjaxResult("200", "保存成功", "book", "closeCurrent", "");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "book", "", "");
		}
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 书本删除处理
	 * 
	 */
	@RequestMapping(value = "/removeBook")
	@ResponseBody
	public String removebook(@ModelAttribute("TblBook") TblBook tblBook,HttpServletRequest request) {
		DwzAjaxResult dwzResult=null;
		bookService.remove(tblBook.getPkBook());
		dwzResult = new DwzAjaxResult("200", "删除成功", "book", "", "");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	
}
