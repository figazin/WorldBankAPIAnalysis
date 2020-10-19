package com.alejo.economicdataanalyzer.entity;

import java.io.Serializable;
import java.util.List;

public class WBAPIElements implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5059680123138439444L;
	private List<WBAPIElement> elements;
	/**
	 * @return the elements
	 */
	public List<WBAPIElement> getElements() {
		return elements;
	}
	/**
	 * @param elements the elements to set
	 */
	public void setElements(List<WBAPIElement> elements) {
		this.elements = elements;
	}

}
