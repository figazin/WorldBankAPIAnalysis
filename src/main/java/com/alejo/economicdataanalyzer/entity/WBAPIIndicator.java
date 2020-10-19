package com.alejo.economicdataanalyzer.entity;

import java.io.Serializable;

public class WBAPIIndicator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -950594648545627700L;
	private String id;
	private String value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
