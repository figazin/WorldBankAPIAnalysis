package com.alejo.economicdataanalyzer.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Country implements Serializable {

	private static final long serialVersionUID = 3950114330953154298L;
	@Id
	@GeneratedValue
	private Integer id;
	private String code;
	private String name;
	@OneToMany(mappedBy = "country", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Indicator> indicators = new ArrayList<>();
	@Transient
	private Double populationGrowth;
	@Transient
	private Double totalPopulationGrowth;
	@Transient
	private Double gdpPppGrowth;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Indicator> getIndicators() {
		return indicators;
	}

	public void setIndicators(List<Indicator> indicators) {
		indicators.forEach(e -> e.setCountry(this));
		this.indicators = indicators;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "CountryEntity [code=" + code + ", name=" + name + ", indicators=" + indicators + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * @return the populationGrowth
	 */
	public Double getPopulationGrowth() {
		return populationGrowth;
	}

	/**
	 * @param populationGrowth the populationGrowth to set
	 */
	public void setPopulationGrowth(Double populationGrowth) {
		this.populationGrowth = populationGrowth;
	}

	/**
	 * @return the totalPopulationGrowth
	 */
	public Double getTotalPopulationGrowth() {
		return totalPopulationGrowth;
	}

	/**
	 * @param totalPopulationGrowth the totalPopulationGrowth to set
	 */
	public void setTotalPopulationGrowth(Double totalPopulationGrowth) {
		this.totalPopulationGrowth = totalPopulationGrowth;
	}

	/**
	 * @return the gdpPppGrowth
	 */
	public Double getGdpPppGrowth() {
		return gdpPppGrowth;
	}

	/**
	 * @param gdpPppGrowth the gdpPppGrowth to set
	 */
	public void setGdpPppGrowth(Double gdpPppGrowth) {
		this.gdpPppGrowth = gdpPppGrowth;
	}

	public Country(String code, String name, List<Indicator> indicators) {
		super();
		this.code = code;
		this.name = name;
		this.indicators = indicators;
	}
	
	public Country() {
		super();
	}
	
}
