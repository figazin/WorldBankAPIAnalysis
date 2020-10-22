package com.alejo.economicdataanalyzer.service;

import com.alejo.economicdataanalyzer.entity.InvestingCountriesResponse;
import com.alejo.economicdataanalyzer.exceptions.IngestException;
import com.alejo.economicdataanalyzer.exceptions.InvestServiceException;

public interface InvestingService {

	void ingestData(Integer yearFrom, Integer yearTo) throws IngestException;

	InvestingCountriesResponse listCountriesToInvest(Integer populationCountriesLimit, Integer gdpCountriesLimit) throws InvestServiceException;

}
