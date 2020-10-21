package com.alejo.economicdataanalyzer.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alejo.economicdataanalyzer.dao.WorldBankAPIDAO;
import com.alejo.economicdataanalyzer.dao.impl.CountriesRepository;
import com.alejo.economicdataanalyzer.entity.Country;
import com.alejo.economicdataanalyzer.entity.Indicator;
import com.alejo.economicdataanalyzer.entity.InvestingCountriesResponse;
import com.alejo.economicdataanalyzer.entity.WBAPICountry;
import com.alejo.economicdataanalyzer.entity.WBAPIElement;
import com.alejo.economicdataanalyzer.entity.WBAPIIndicator;

@ExtendWith(MockitoExtension.class)
public class InvestingServiceTest {
	
	@Mock
	WorldBankAPIDAO worldBankAPIDAO;
	
	@Mock
	CountriesRepository countriesRepository;
	
    @InjectMocks
    private InvestingServiceImpl investingService;
    
    @Test
    public void whenAPICallReturnsNullThenThrowException() throws IngestException {
    	when(worldBankAPIDAO.getCountriesPopulationAndGdpPpp()).thenReturn(new ArrayList<WBAPIElement>());
    	Assertions.assertThrows(IngestException.class, () -> investingService.ingestData());
    }
    
    @Test
    public void whenAPICallOkThenCallRepositoryWithRightSizeList() throws IngestException {
    	List<WBAPIElement> wbAPIList = getWBApiList();
		when(worldBankAPIDAO.getCountriesPopulationAndGdpPpp()).thenReturn(wbAPIList);
		
		investingService.ingestData();
		
		ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
		verify(countriesRepository).saveAll(captor.capture());
		//List should have one element for each COUNTRY
		assertEquals(2, captor.getValue().size());
    }
    
    @Test
    public void whenListCountriesThenReturnResponseEntity() {
    	
    	List<Country> repoList = getRepositoryList();
		when(countriesRepository.findAllCountries()).thenReturn(repoList);
		
		InvestingCountriesResponse response = investingService.listCountriesToInvest();
		
		assertNotNull(response);
		assertNotNull(response.getTopGDPPPP());
		assertNotNull(response.getTopPopulationGrowth());
		assertEquals("Brazil", response.getTopPopulationGrowth().get(0).getCountry());
		//Brazil's biggest growth is 7 between 2015 and 2016
		assertEquals("7.0", response.getTopPopulationGrowth().get(0).getIndicator());
		//Brazil's average GDP/PPP should be 2.0
		assertEquals("2.0", response.getTopGDPPPP().get(0).getIndicator());
    }

	private List<Country> getRepositoryList() {
		List<Country> countryList = new ArrayList<Country>();
		List<Indicator> indicatorsList1 = new ArrayList<Indicator>();
		indicatorsList1.add(new Indicator(null, 2012, 500.0, 1.1));
		indicatorsList1.add(new Indicator(null, 2013, 501.0, 1.2));
		indicatorsList1.add(new Indicator(null, 2014, 502.0, 1.3));
		indicatorsList1.add(new Indicator(null, 2015, 503.0, 1.4));
		indicatorsList1.add(new Indicator(null, 2016, 508.0, 1.4));
		indicatorsList1.add(new Indicator(null, 2017, 500.0, 1.3));
		indicatorsList1.add(new Indicator(null, 2018, 503.0, 1.2));
		indicatorsList1.add(new Indicator(null, 2019, 502.0, 1.1));
		countryList.add(new Country("ARG", "Argentina", indicatorsList1));
		List<Indicator> indicatorsList2 = new ArrayList<Indicator>();
		indicatorsList2.add(new Indicator(null, 2012, 600.0, 1.0));
		indicatorsList2.add(new Indicator(null, 2013, 601.0, 2.0));
		indicatorsList2.add(new Indicator(null, 2014, 602.0, 3.0));
		indicatorsList2.add(new Indicator(null, 2015, 603.0, 4.0));
		indicatorsList2.add(new Indicator(null, 2016, 610.0, 1.0));
		indicatorsList2.add(new Indicator(null, 2017, 600.0, 2.0));
		indicatorsList2.add(new Indicator(null, 2018, 603.0, 1.0));
		indicatorsList2.add(new Indicator(null, 2019, 602.0, 2.0));
		countryList.add(new Country("BRA", "Brazil", indicatorsList2));
		countryList.addAll(countryList);
		countryList.addAll(countryList);
		countryList.addAll(countryList);
		countryList.addAll(countryList);
		return countryList;
	}

	private List<WBAPIElement> getWBApiList() {
		List<WBAPIElement> testList = new ArrayList<WBAPIElement>();
		testList.add(new WBAPIElement(new WBAPIIndicator("NY.GDP.MKTP.PP.CD", null), new WBAPICountry(null, "Argentina"), "", "2019", 500.0, "", "", 0L, ""));
		testList.add(new WBAPIElement(new WBAPIIndicator("NY.GDP.MKTP.PP.CD", null), new WBAPICountry(null, "Argentina"), "", "2018", 500.0, "", "", 0L, ""));
		testList.add(new WBAPIElement(new WBAPIIndicator("NY.GDP.MKTP.PP.CD", null), new WBAPICountry(null, "Argentina"), "", "2017", 500.0, "", "", 0L, ""));
		testList.add(new WBAPIElement(new WBAPIIndicator("NY.GDP.MKTP.PP.CD", null), new WBAPICountry(null, "Thailand"), "", "2019", 500.0, "", "", 0L, ""));
		testList.add(new WBAPIElement(new WBAPIIndicator("NY.GDP.MKTP.PP.CD", null), new WBAPICountry(null, "Thailand"), "", "2018", 500.0, "", "", 0L, ""));
		testList.add(new WBAPIElement(new WBAPIIndicator("NY.GDP.MKTP.PP.CD", null), new WBAPICountry(null, "Thailand"), "", "2017", 500.0, "", "", 0L, ""));
		testList.add(new WBAPIElement(new WBAPIIndicator("SP.POP.TOTL", null), new WBAPICountry(null, "Argentina"), "", "2019", 500.0, "", "", 0L, ""));
		testList.add(new WBAPIElement(new WBAPIIndicator("SP.POP.TOTL", null), new WBAPICountry(null, "Argentina"), "", "2018", 500.0, "", "", 0L, ""));
		testList.add(new WBAPIElement(new WBAPIIndicator("SP.POP.TOTL", null), new WBAPICountry(null, "Argentina"), "", "2017", 500.0, "", "", 0L, ""));
		testList.add(new WBAPIElement(new WBAPIIndicator("SP.POP.TOTL", null), new WBAPICountry(null, "Thailand"), "", "2019", 500.0, "", "", 0L, ""));
		testList.add(new WBAPIElement(new WBAPIIndicator("SP.POP.TOTL", null), new WBAPICountry(null, "Thailand"), "", "2018", 500.0, "", "", 0L, ""));
		testList.add(new WBAPIElement(new WBAPIIndicator("SP.POP.TOTL", null), new WBAPICountry(null, "Thailand"), "", "2017", 500.0, "", "", 0L, ""));

		return testList;
	}

}
