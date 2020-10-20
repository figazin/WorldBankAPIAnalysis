package com.alejo.economicdataanalyzer.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
		when(worldBankAPIDAO.getCountriesPopulationAndGdpPpp()).thenReturn(wbAPIList );
		
		investingService.ingestData();
		
		ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
		verify(countriesRepository).saveAll(captor.capture());
		//List should have one element for each COUNTRY
		assertEquals(2, captor.getValue().size());
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
