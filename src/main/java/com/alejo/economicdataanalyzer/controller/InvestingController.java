package com.alejo.economicdataanalyzer.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alejo.economicdataanalyzer.entity.InvestingCountriesResponse;
import com.alejo.economicdataanalyzer.exceptions.IngestException;
import com.alejo.economicdataanalyzer.exceptions.InvestServiceException;
import com.alejo.economicdataanalyzer.service.InvestingService;
import com.alejo.economicdataanalyzer.util.UrlsConstants;

@RestController
public class InvestingController {

	@Autowired
	InvestingService investingService;

	@GetMapping(UrlsConstants.INGEST_URL)
	public ResponseEntity<Object> ingestData(@RequestParam Optional<Integer> yearFrom, @RequestParam Optional<Integer> yearTo) {
		try {
			investingService.ingestData(yearFrom.orElse(2012), yearTo.orElse(2019));
		} catch (IngestException e) {
			return new ResponseEntity<Object>("Error with data ingestion", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Object>("Data ingestion finished correctly", HttpStatus.OK);
	}

	@GetMapping(UrlsConstants.COUNTRIES_TO_INVEST)
	public ResponseEntity<Object> listCountriesToInvest(@RequestParam Optional<Integer> popuLimit, @RequestParam Optional<Integer> gdpLimit) {
		InvestingCountriesResponse responseList;
		try {
			responseList = investingService.listCountriesToInvest(popuLimit.orElse(20), gdpLimit.orElse(5));
		} catch (InvestServiceException e) {
			return new ResponseEntity<Object>("Error while processing request", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Object>(responseList, HttpStatus.OK);
	}

}
