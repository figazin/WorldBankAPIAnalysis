package com.alejo.economicdataanalyzer.entity;

import java.io.Serializable;

public class WBAPIElement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8649864855030577307L;
	
	private WBAPIIndicator indicator;
	private WBAPICountry country;
	private String countryiso3code;
	private String date;
	private double value;
	private String unit;
	private String obs_status;
	private long decimal;
	private String scale;

	public WBAPIIndicator getIndicator() {
		return indicator;
	}

	public void setIndicator(WBAPIIndicator indicator) {
		this.indicator = indicator;
	}

	public WBAPICountry getCountry() {
		return country;
	}

	public void setCountry(WBAPICountry country) {
		this.country = country;
	}

	public String getCountryiso3code() {
		return countryiso3code;
	}

	public void setCountryiso3code(String countryiso3code) {
		this.countryiso3code = countryiso3code;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public long getDecimal() {
		return decimal;
	}

	public void setDecimal(long decimal) {
		this.decimal = decimal;
	}

	/**
	 * @return the obs_status
	 */
	public String getObs_status() {
		return obs_status;
	}

	/**
	 * @param obs_status the obs_status to set
	 */
	public void setObs_status(String obs_status) {
		this.obs_status = obs_status;
	}

	/**
	 * @return the scale
	 */
	public String getScale() {
		return scale;
	}

	/**
	 * @param scale the scale to set
	 */
	public void setScale(String scale) {
		this.scale = scale;
	}

	public WBAPIElement() {
		super();
	}

	public WBAPIElement(WBAPIIndicator indicator, WBAPICountry country, String countryiso3code, String date,
			double value, String unit, String obs_status, long decimal, String scale) {
		super();
		this.indicator = indicator;
		this.country = country;
		this.countryiso3code = countryiso3code;
		this.date = date;
		this.value = value;
		this.unit = unit;
		this.obs_status = obs_status;
		this.decimal = decimal;
		this.scale = scale;
	}

}
