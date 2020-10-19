/*	DROP TABLE IF EXISTS billionaires;
	 
	CREATE TABLE billionaires (
	  id INT AUTO_INCREMENT  PRIMARY KEY,
	  first_name VARCHAR(250) NOT NULL,
	  last_name VARCHAR(250) NOT NULL,
	  career VARCHAR(250) DEFAULT NULL
	);

DROP TABLE IF EXISTS indicators, countries;
--DROP TABLE IF EXISTS countries;

CREATE TABLE countries (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL
	);

CREATE TABLE indicators (
	countryId INT NOT NULL,
	year INT NOT NULL,
	population DOUBLE NOT NULL,
	gdp_ppp DOUBLE NOT NULL,
	FOREIGN KEY (countryId) REFERENCES countries(id),
	PRIMARY KEY (countryId, year)
	);
*/