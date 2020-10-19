package com.alejo.economicdataanalyzer.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class WBAPIHeader implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5939956924216903496L;
	private long page;
	private long pages;
	private long perPage;
	private long total;
	private String sourceid;
	private LocalDate lastupdated;

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getPages() {
		return pages;
	}

	public void setPages(long pages) {
		this.pages = pages;
	}

	public long getPerPage() {
		return perPage;
	}

	public void setPerPage(long perPage) {
		this.perPage = perPage;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getSourceid() {
		return sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

	public LocalDate getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(LocalDate lastupdated) {
		this.lastupdated = lastupdated;
	}

}
