CREATE TABLE company (
      companyId SERIAL PRIMARY KEY,
      ticker VARCHAR,
      name VARCHAR,
      sector VARCHAR,
      country VARCHAR
);

CREATE INDEX tickers ON company(ticker);
