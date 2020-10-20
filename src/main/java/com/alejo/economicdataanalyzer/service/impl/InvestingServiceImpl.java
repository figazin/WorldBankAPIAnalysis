package com.alejo.economicdataanalyzer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejo.economicdataanalyzer.dao.WorldBankAPIDAO;
import com.alejo.economicdataanalyzer.dao.impl.CountriesRepository;
import com.alejo.economicdataanalyzer.entity.Country;
import com.alejo.economicdataanalyzer.entity.Indicator;
import com.alejo.economicdataanalyzer.entity.WBAPIElement;
import com.alejo.economicdataanalyzer.service.InvestingService;

@Service
public class InvestingServiceImpl implements InvestingService {
	
    Logger logger = LoggerFactory.getLogger(InvestingServiceImpl.class);
    
	@Autowired
	WorldBankAPIDAO worldBankAPIDAO;
	
	@Autowired
	CountriesRepository countriesRepository;
	
	@Override
	public void ingestData() throws IngestException {
		
		//Get a list of All the countries with indicators for Total Population and GDP/PPP
		List<WBAPIElement> populationResponse = worldBankAPIDAO.getCountriesPopulationAndGdpPpp();
		if(CollectionUtils.isEmpty(populationResponse)) {
			logger.error("World Bank API communication error");
			throw new IngestException();
		}
		
		//Group the list by Country (loop 1)
		Map<Country, List<WBAPIElement>> populationMap = populationResponse.stream().collect(Collectors.groupingBy(e -> getCountryEntity(e)));
		
		//Build a list for saving to the DB only what's needed (loop 2)
		List<Country> countryList = new ArrayList<Country>();
		populationMap.forEach((k, v) -> countryList.add(assignCountryIndicators(k, v)));
		
		countriesRepository.saveAll(countryList);
		
	}

	private Country assignCountryIndicators(Country k, List<WBAPIElement> v) {
		k.setIndicators(buildIndicatorsList(v));
		return k;
	}

	private Country getCountryEntity(WBAPIElement e) {
		Country country = new Country();
		country.setCode(e.getCountry().getId());
		country.setName(e.getCountry().getValue());
		return country;
	}

	private List<Indicator> buildIndicatorsList(List<WBAPIElement> inputList) {
		//Having each indicator filtered by country, we filter it by year to build our Indicator Entity (loop 3)
		Map<String, List<WBAPIElement>> indicatorsMapByYear = inputList.stream()
					.collect(Collectors.groupingBy(e -> e.getDate()));
		
		// (loop 4)
		List<Indicator> indicatorsList = new ArrayList<Indicator>();
		indicatorsMapByYear.forEach((k, v) -> indicatorsList.add(createIndicator(v)));
		 
		return indicatorsList;
	}

	private Indicator createIndicator(List<WBAPIElement> indicatorsPerYear) {
		Indicator indicator = new Indicator();
		for (WBAPIElement wbapiElement : indicatorsPerYear) {
			indicator.setYear(new Integer(wbapiElement.getDate()));
			if("NY.GDP.MKTP.PP.CD".equals(wbapiElement.getIndicator().getId())){
				indicator.setGdpPpp(wbapiElement.getValue());
			}
			if("SP.POP.TOTL".equals(wbapiElement.getIndicator().getId())) {
				indicator.setPopulation(wbapiElement.getValue());
			}
		}
		return indicator;
	}
	
}
