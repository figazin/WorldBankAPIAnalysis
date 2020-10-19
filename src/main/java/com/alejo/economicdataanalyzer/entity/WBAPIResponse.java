package com.alejo.economicdataanalyzer.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WBAPIResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7513427739868166694L;
	
	private WBAPIHeader header;
	private List<WBAPIElements> elements;
	
	public WBAPIHeader getHeader() {
		return header;
	}

	public void setHeader(WBAPIHeader header) {
		this.header = header;
	}

	public List<WBAPIElements> getElements() {
		return elements;
	}

	public void setElements(List<WBAPIElements> elements) {
		this.elements = elements;
	}

}
