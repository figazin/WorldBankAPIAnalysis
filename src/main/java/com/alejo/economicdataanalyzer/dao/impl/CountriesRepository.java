package com.alejo.economicdataanalyzer.dao.impl;

import org.springframework.data.repository.CrudRepository;

import com.alejo.economicdataanalyzer.entity.Country;

public interface CountriesRepository extends CrudRepository<Country, Integer> {
	
	
}
