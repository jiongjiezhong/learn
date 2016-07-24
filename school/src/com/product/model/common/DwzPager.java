package com.product.model.common;

import java.io.Serializable;

public class DwzPager implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String status = "";
	protected String keywords = "";
	protected Long pageNum = 1L;
	protected Long numPerPage = 20L;
	protected Long pageTotal = 0L;
	protected Long total = 0L;
	
	public DwzPager(){}
	
	public DwzPager(Long pageNum, Long numPerPage, Long total){
		if(null != pageNum && pageNum > 0)
			this.pageNum = pageNum;
		if(null != numPerPage && numPerPage > 0)
			this.numPerPage = numPerPage;
		if(null != total && total > 0)
			this.total = total;
		//����
		calculate();
	}
	
	private void calculate(){
		if(total <= 0) return;
		//����ҳ����ҳ���Ƿ�Խ��
		pageTotal = (total + numPerPage - 1) / numPerPage;
		if(pageNum < 1) pageNum = 1L;
		else if(pageNum > pageTotal) pageNum = pageTotal;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public Long getPageNum() {
		return pageNum;
	}
	public void setPageNum(Long pageNum) {
		this.pageNum = pageNum;
		//����
		calculate();
	}
	public Long getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(Long numPerPage) {
		this.numPerPage = numPerPage;
		//����
		calculate();
	}
	public Long getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(Long pageTotal) {
		this.pageTotal = pageTotal;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
		//����
		if(0 >= total) {
			pageNum = 0L;
			pageTotal = 0L;
		}else{
			calculate();
		}
	}
}
