package com.alejo.economicdataanalyzer.dao;

import java.util.List;

import com.alejo.economicdataanalyzer.entity.WBAPIElement;

public interface WorldBankAPIDAO {

	List<WBAPIElement> getCountriesPopulation();

	List<WBAPIElement> getCountriesGdpPpp();

	List<WBAPIElement> getCountriesPopulationAndGdpPpp(Integer yearFrom, Integer yearTo);

}
