package com.alejo.economicdataanalyzer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.alejo.economicdataanalyzer.dao.WorldBankAPIDAO;
import com.alejo.economicdataanalyzer.entity.WBAPIElement;
import com.alejo.economicdataanalyzer.util.UrlsConstants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class WorldBankAPIDAOImpl implements WorldBankAPIDAO {

	@Autowired
	RestTemplate restTemplate;

	ObjectMapper mapper = new ObjectMapper();

	@Override
	public List<WBAPIElement> getCountriesPopulation() {

		Object[] apiResp = restTemplate.getForObject(UrlsConstants.POPULATION_URL, Object[].class);
		List<WBAPIElement> mappedResponse = mapper.convertValue(apiResp[1], new TypeReference<List<WBAPIElement>>() {});

		return mappedResponse;
	}

	@Override
	public List<WBAPIElement> getCountriesGdpPpp() {
		
		Object[] apiResp = restTemplate.getForObject(UrlsConstants.GDP_PPP_URL, Object[].class);
		List<WBAPIElement> mappedResponse = mapper.convertValue(apiResp[1], new TypeReference<List<WBAPIElement>>() {});

		return mappedResponse;
	}
	
	@Override
	public List<WBAPIElement> getCountriesPopulationAndGdpPpp(Integer yearFrom, Integer yearTo) {
		
		Object[] apiResp = restTemplate.getForObject(String.format(UrlsConstants.POPULATION_PPP_URL, yearFrom, yearTo), Object[].class);
		List<WBAPIElement> mappedResponse = mapper.convertValue(apiResp[1], new TypeReference<List<WBAPIElement>>() {});

		return mappedResponse;
	}
}
