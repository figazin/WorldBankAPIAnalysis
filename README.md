# WorldBankAPIAnalysis

## Starting the Application:
To start the application:
1. Run "mvn clean install"
2. Run "mvn spring-boot:run"

- Endpoints are:
   - localhost:8080/ingest?yearFrom=?&yearTo=?
   - localhost:8080/countriesToInvest?popuLimit=?&gdpLimit=?
   
Query params are optional and default values are those requested by the Challenge.

## Challenge Overview:
We need a program which pull/download WorldBank world population and GDP data into a local database and then query that database to answer two question which are important to our global marketing team:

1. Which 20 countries in the data have the highest population growth rates from 2012 to 2019?
2. Of those 20 countries, when combined with GDP/PPP, which would be the best 5 countries to start investing marketing into for our products?


## Challenge Details:
You will need to write a program that performs the following tasks:

### Data Ingestion
Start by find the best sources for world population by country and GDP/PPP by country in the WorldBank Databank (see “World Development Indicators” or the “SP.POP.TOTL” dataset specifically, although there are probably other datasets with the necessary data).

Your program should:
1. Collect the world population data from the WorldBank Databank through their Data API
2. Collect the world GDP / PPP data from the WorldBank Databank through their Data API
3. Parse those data and store them into an embedded database (if you are not sure what to use, try SQL Server Embedded, HSQLDB, H2 database, or SQLite)

### Data Query
Your program should:
1. Query the world population table for the top 20 countries by population growth from 2012 to 2019 Year-to-Year growth is defined here as the difference between Y_n+1 – Y_n, and highest growth over a time period will be defined as the maximum sum of the Year-to-Year grown differences in that time period.
2. Query the GDP/PPP table for the top 5 countries by GDP growth, limited to the subset of countries from #1. This function should be independent from the function in #1.

## Challenge goals:

1. Data ingestion (Choose API’s to get needed data and use it).
   - Initial ingestion.
   - The ability to re-ingest any time that we want.
   - A mechanism to be sure the data are up-to-date or reload if not (It is not necessary to implement only design the code to be able to implement in a future).
2. Data results. Get data from the database and provide needed results. It must be a API REST:
   - The “population growth” could be calculated either using recursivity concepts or with loop structures. You should provide at least recursivity implementation and use some design pattern to configure which use.
3. Improve your API developed on step 2 to add parameters in terms of business needs. Example, I want the GDP/PPP for the top 3 countries.
4. Provide a document detail of architecture for new release (mainly architecture/design concepts, but you could add some considerations on your current code or add some proof of concepts) to meet the following requirements:
   - Some other API’s to Data API’s should be used. Some of them could be stored on our database like  “SP.POP.TOTL” but others must be considered to make online calls.
   - We must consider to provide at least 10 Rest API’s (Similar than we have already developed) and it will be used with an average of 10 TPS and peaks of 100 TPS
   - We will have a limit of N TPS to send API calls to WorldBank of 20 TPS.
5. Add with a new branch in your current code every POC that you used for step 4. If you don’t implement new features add TODO comments.


## Completed Challenge
A completed challenge ready to submit for review will include:
1. Source code, DDL, notes, etc. All in a git repository. It would be fine to have every commit on your work to check the progress.   
2. It should be clear how to package and deploy your work (You can add a shell script or instructions to do that)
3. A good coverage is a wish.
4. For goal number four (4) we expect a documentation and a new branch at least with comments (it could be a spec or everything you think good to communicate technical information) with your assumptions and your proposal for next releases focusing on architecture and design decisions (Please keep focus on technical concerns, beautiful template is not needed)

