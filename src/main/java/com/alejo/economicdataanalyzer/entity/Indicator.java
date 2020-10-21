package com.alejo.economicdataanalyzer.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Indicator implements Serializable {

	private static final long serialVersionUID = 1230201489466205309L;
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Country country;
	private Integer year;
	private Double population;
	private Double gdpPpp;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getPopulation() {
		return population;
	}

	public void setPopulation(Double population) {
		this.population = population;
	}

	public Double getGdpPpp() {
		return gdpPpp;
	}

	public void setGdpPpp(Double gdpPpp) {
		this.gdpPpp = gdpPpp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "IndicatorEntity [id=" + id + ", country=" + country.getName() + ", year=" + year + ", population=" + population
				+ ", gdpPpp=" + gdpPpp + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((gdpPpp == null) ? 0 : gdpPpp.hashCode());
		result = prime * result + ((population == null) ? 0 : population.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		Indicator other = (Indicator) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (gdpPpp == null) {
			if (other.gdpPpp != null)
				return false;
		} else if (!gdpPpp.equals(other.gdpPpp))
			return false;
		if (population == null) {
			if (other.population != null)
				return false;
		} else if (!population.equals(other.population))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	public Indicator(Country country, Integer year, Double population, Double gdpPpp) {
		super();
		this.country = country;
		this.year = year;
		this.population = population;
		this.gdpPpp = gdpPpp;
	}
	
	public Indicator() {
		super();
	}

}
