package com.product.model.common;

public class DwzPagerMySQL extends DwzPager{
	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = -6995731759024889041L;

	private Long offset = 0L;

	public DwzPagerMySQL() {
	}

	public DwzPagerMySQL(Long pageNum, Long numPerPage, Long total) {
		super(pageNum, numPerPage, total);
	}

	public Long getOffset() {
//		if (0 == total)
//			return 0L;
		offset = numPerPage * (pageNum - 1);
		return offset > 0 ? offset : 0;
	}

	public void setOffset(Long offset) {
		this.offset = offset;
	}
}
