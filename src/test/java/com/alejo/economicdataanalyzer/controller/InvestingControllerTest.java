package com.alejo.economicdataanalyzer.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.alejo.economicdataanalyzer.service.InvestingService;
import com.alejo.economicdataanalyzer.service.impl.IngestException;
import com.alejo.economicdataanalyzer.util.UrlsConstants;

@SpringBootTest
@AutoConfigureMockMvc
public class InvestingControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InvestingService investingService;

	@Test
	public void ingestShouldReturnOKResponse() throws Exception {
		this.mockMvc.perform(get(UrlsConstants.INGEST_URL)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Data ingestion finished correctly")));
	}
	
	@Test
	public void countriesToInvestShouldReturnOKResponse() throws Exception {
		this.mockMvc.perform(get(UrlsConstants.COUNTRIES_TO_INVEST)).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void ingestThrowsExceptionShouldReturnConflictResponse() throws Exception {
		doThrow(IngestException.class).when(investingService).ingestData();
		this.mockMvc.perform(get(UrlsConstants.INGEST_URL)).andDo(print()).andExpect(status().isConflict())
				.andExpect(content().string(containsString("Error during data ingestion")));
	}

}
