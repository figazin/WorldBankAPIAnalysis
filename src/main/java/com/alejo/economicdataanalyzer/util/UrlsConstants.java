package com.alejo.economicdataanalyzer.util;

public class UrlsConstants {
	
	public final static String POPULATION_URL = "http://api.worldbank.org/v2/country/all/indicator/SP.POP.TOTL?date=2012:2019&format=json&per_page=20000";
	public final static String GDP_PPP_URL = "http://api.worldbank.org/v2/country/all/indicator/NY.GDP.MKTP.PP.CD?date=2012:2019&format=json&per_page=20000";
	public final static String POPULATION_PPP_URL = "http://api.worldbank.org/v2/country/all/indicator/NY.GDP.MKTP.PP.CD;SP.POP.TOTL?source=2&date=2012:2019&format=json&per_page=20000";

}
