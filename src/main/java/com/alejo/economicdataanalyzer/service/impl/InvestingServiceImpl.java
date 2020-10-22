package com.alejo.economicdataanalyzer.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
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
import com.alejo.economicdataanalyzer.entity.CountryIndicatorResponse;
import com.alejo.economicdataanalyzer.entity.Indicator;
import com.alejo.economicdataanalyzer.entity.InvestingCountriesResponse;
import com.alejo.economicdataanalyzer.entity.WBAPIElement;
import com.alejo.economicdataanalyzer.exceptions.IngestException;
import com.alejo.economicdataanalyzer.service.InvestingService;

@Service
public class InvestingServiceImpl implements InvestingService {
	
    Logger logger = LoggerFactory.getLogger(InvestingServiceImpl.class);
    
    static Integer counter = 0;
	static Double populationGrowth = 0.0;
    
	@Autowired
	WorldBankAPIDAO worldBankAPIDAO;
	
	@Autowired
	CountriesRepository countriesRepository;
	
	@Override
	public void ingestData(Integer yearFrom, Integer yearTo) throws IngestException {
		
		if(validateInput(yearFrom, yearTo)) {
			logger.error("World Bank API communication error");
			throw new IngestException();
		}
		//Get a list of All the countries with indicators for Total Population and GDP/PPP
		List<WBAPIElement> populationResponse = worldBankAPIDAO.getCountriesPopulationAndGdpPpp(yearFrom, yearTo);
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

	private boolean validateInput(Integer yearFrom, Integer yearTo) {
		return (yearFrom < 2012 || yearFrom >2019) || (yearTo < 2012 || yearTo > 2019) || (yearFrom > yearTo);
	}

	private Country assignCountryIndicators(Country country, List<WBAPIElement> elementList) {
		country.setIndicators(buildIndicatorsList(elementList));
		return country;
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
		
		indicatorsList.sort(Comparator.comparing(Indicator::getYear));
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

	@Override
	public InvestingCountriesResponse listCountriesToInvest(Integer popuLimit, Integer gdpLimit) {
		
		InvestingCountriesResponse response = new InvestingCountriesResponse();
		List<Country> countries = countriesRepository.findAllCountries();
		
		calculatePopGrowth(countries);
		countries.sort(Comparator.comparing(Country::getPopulationGrowth).reversed());
		response.setTopPopulationGrowth(createTopPopResponse(countries, popuLimit));
		
		List<Country> higherPopGrowthCountries = countries.subList(0, popuLimit-1);
		calculateGdpPpp(higherPopGrowthCountries);
		higherPopGrowthCountries.sort(Comparator.comparing(Country::getGdpPppGrowth).reversed());
		response.setTopGDPPPP(createTopGdpPppResponse(higherPopGrowthCountries, gdpLimit));
		
		return response;
	}

	private List<CountryIndicatorResponse> createTopGdpPppResponse(List<Country> countries, Integer gdpCountriesLimit) {
		return countries.stream().limit(gdpCountriesLimit).map(e -> createGdpIndicatorResponse(e)).collect(Collectors.toList());
	}

	private CountryIndicatorResponse createGdpIndicatorResponse(Country country) {
		CountryIndicatorResponse countryIndicatorResponse = new CountryIndicatorResponse();
		countryIndicatorResponse.setCountry(country.getName());
		countryIndicatorResponse.setIndicator(country.getGdpPppGrowth().toString());
		return countryIndicatorResponse;
	}

	private List<CountryIndicatorResponse> createTopPopResponse(List<Country> countries, Integer populationCountriesLimit) {
		return countries.stream().limit(populationCountriesLimit).map(e -> createPopIndicatorResponse(e)).collect(Collectors.toList());
	}
	
	private CountryIndicatorResponse createPopIndicatorResponse(Country country) {
		CountryIndicatorResponse countryIndicatorResponse = new CountryIndicatorResponse();
		countryIndicatorResponse.setCountry(country.getName());
		countryIndicatorResponse.setIndicator(country.getPopulationGrowth().toString());
		return countryIndicatorResponse;
	}

	private void calculateGdpPpp(List<Country> countries) {
		for (Country country : countries) {
			country.setGdpPppGrowth(findGdpPPPGrowth(country.getIndicators()));
		}
	}

	private Double findGdpPPPGrowth(List<Indicator> indicators) {
		Double gdpPpp = 0.0;
		for (int i = 0; i < indicators.size()-1; i++) {
			gdpPpp = gdpPpp + indicators.get(i).getGdpPpp();
		}
		gdpPpp = gdpPpp / (indicators.size()-1);
		return gdpPpp;
	}

	private void calculatePopGrowth(List<Country> countries) {
		for (Country country : countries) {
			country.setPopulationGrowth(findMaxPopGrowthRecursive(country.getIndicators()));
		}
	}
	
	//Calculation with recursivity
	private Double findMaxPopGrowthRecursive(List<Indicator> indicators) {
		if(counter == 0) {
			populationGrowth = 0.0;
		}
		if(counter < indicators.size()-2) {
			Double populationAux = indicators.get(counter+1).getPopulation() - indicators.get(counter).getPopulation();
			if(populationAux > populationGrowth) {
				populationGrowth = populationAux;
			}
			counter++;
			findMaxPopGrowthRecursive(indicators);
		}
		counter = 0;
		return populationGrowth;
	}
	
	//Calculation with loop
	@SuppressWarnings("unused")
	private Double findMaxPopGrowth(List<Indicator> indicators) {
		Double populationGrowth = 0.0;
		Double populationAux = 0.0;
		for (int i = 0; i < indicators.size()-1; i++) {
			populationAux = indicators.get(i+1).getPopulation() - indicators.get(i).getPopulation();
			if(populationGrowth < populationAux) {
				populationGrowth = populationAux;
			}
		}
		return populationGrowth;
	}

}
