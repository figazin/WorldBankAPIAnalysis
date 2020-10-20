package com.alejo.economicdataanalyzer.service;

import com.alejo.economicdataanalyzer.service.impl.IngestException;

public interface InvestingService {

	void ingestData() throws IngestException;

}
