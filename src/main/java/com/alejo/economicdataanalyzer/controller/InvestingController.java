package com.alejo.economicdataanalyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejo.economicdataanalyzer.service.InvestingService;
import com.alejo.economicdataanalyzer.service.impl.IngestException;
import com.alejo.economicdataanalyzer.util.UrlsConstants;

@RestController
public class InvestingController {
	
	@Autowired
	InvestingService investingService;
	
	@GetMapping(UrlsConstants.INGEST_URL)
	public ResponseEntity<Object> ingestData(){
		try {
			investingService.ingestData();
		} catch (IngestException e) {
			return new ResponseEntity<Object>("Error during data ingestion", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Object>("Data ingestion finished correctly", HttpStatus.OK);
	}
	
}
