package com.alejo.economicdataanalyzer.dao.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alejo.economicdataanalyzer.entity.Country;

public interface CountriesRepository extends JpaRepository<Country, Integer> {
	
    @Query("SELECT distinct country FROM Country country JOIN FETCH country.indicators")
	List<Country> findAllCountries();
	
}
