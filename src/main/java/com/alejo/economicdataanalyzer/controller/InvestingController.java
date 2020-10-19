package com.alejo.economicdataanalyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejo.economicdataanalyzer.service.InvestingService;

@RestController
public class InvestingController {
	
	@Autowired
	InvestingService investingService;
	
	@GetMapping("/")
	public ResponseEntity<Object> ingestData(){
		investingService.ingestData();
		return new ResponseEntity<Object>("Ingest finished correctly", HttpStatus.OK);
	}
	
}
