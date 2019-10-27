CREATE TABLE company (
      companyId SERIAL PRIMARY KEY,
      ticker VARCHAR,
      name VARCHAR,
      sector VARCHAR,
      country VARCHAR,
      searchtext TSVECTOR
);

CREATE INDEX searchtext_gin ON company USING GIN(searchtext);
CREATE INDEX tickers ON company(ticker);

CREATE TRIGGER ts_searchtext BEFORE INSERT OR UPDATE ON company FOR EACH ROW EXECUTE PROCEDURE tsvector_update_trigger('searchtext', 'pg_catalog.english', 'ticker', 'name');

