package com.alejo.economicdataanalyzer.entity;

import java.util.List;

public class InvestingCountriesResponse {
	
	private List<CountryIndicatorResponse> topPopulationGrowth;
	private List<CountryIndicatorResponse> topGDPPPP;
	/**
	 * @return the topPopulationGrowth
	 */
	public List<CountryIndicatorResponse> getTopPopulationGrowth() {
		return topPopulationGrowth;
	}
	/**
	 * @param topPopulationGrowth the topPopulationGrowth to set
	 */
	public void setTopPopulationGrowth(List<CountryIndicatorResponse> topPopulationGrowth) {
		this.topPopulationGrowth = topPopulationGrowth;
	}
	/**
	 * @return the topGDPPPP
	 */
	public List<CountryIndicatorResponse> getTopGDPPPP() {
		return topGDPPPP;
	}
	/**
	 * @param topGDPPPP the topGDPPPP to set
	 */
	public void setTopGDPPPP(List<CountryIndicatorResponse> topGDPPPP) {
		this.topGDPPPP = topGDPPPP;
	}
	
}
