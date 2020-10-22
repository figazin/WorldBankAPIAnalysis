package com.alejo.economicdataanalyzer.util;

public class UrlsConstants {
	
	//API Rest Endpoints
	public final static String INGEST_URL = "/ingest";
	public final static String COUNTRIES_TO_INVEST = "/countriesToInvest";
	
	//Wolrd Bank API Endpoints
	public final static String POPULATION_URL = "http://api.worldbank.org/v2/country/all/indicator/SP.POP.TOTL?date=2012:2019&format=json&per_page=20000";
	public final static String GDP_PPP_URL = "http://api.worldbank.org/v2/country/all/indicator/NY.GDP.MKTP.PP.CD?date=2012:2019&format=json&per_page=20000";
	public final static String POPULATION_PPP_URL = "http://api.worldbank.org/v2/country/all/indicator/NY.GDP.MKTP.PP.CD;SP.POP.TOTL?source=2&date=%d:%d&format=json&per_page=20000";

}
