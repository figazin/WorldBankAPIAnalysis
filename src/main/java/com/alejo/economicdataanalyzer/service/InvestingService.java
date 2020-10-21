package com.alejo.economicdataanalyzer.service;

import com.alejo.economicdataanalyzer.entity.InvestingCountriesResponse;
import com.alejo.economicdataanalyzer.service.impl.IngestException;

public interface InvestingService {

	void ingestData() throws IngestException;

	InvestingCountriesResponse listCountriesToInvest();

}
