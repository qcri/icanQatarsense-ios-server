package com.qsense.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Pagination {
	static Logger logger = LogManager.getLogger(Pagination.class);

	private Long offset;
	private Long limit;
	private Enum sortOrder;
	private String sortColumn;
	
	public Pagination() {
		
	}
	
	public Pagination(Long offset, Long limit, Enum sortOrder, String sortColumn) {
		this.offset = offset;
		this.limit = limit;
		this.sortOrder = sortOrder;
		this.sortColumn = sortColumn;
	}
	
	/**
	 * @return the offset
	 */
	public Long getOffset() {
		//Decrease by 1 due to Actual index on pagination offset will start from 0. 
		return (offset == null ? null : offset.longValue()-1);
	}
	
	/**
	 * @return the limit
	 */
	public Long getLimit() {
		return limit;
	}
	
	/**
	 * @return the sortColumn
	 */
	public String getSortColumn() {
		return sortColumn;
	}
	
	/**
	 * @return the sortOrder
	 */
	public Enum getSortOrder() {
		return sortOrder;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(Long offset) {
		this.offset = offset;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(Long limit) {
		this.limit = limit;
	}

	/**
	 * @param sortOrder the sortOrder to set
	 */
	public void setSortOrder(Enum sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * @param sortColumn the sortColumn to set
	 */
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}
	
}