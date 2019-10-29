
(ns stock-app-backend.domain.ticker
  (:require [stock-app-backend.adapters.sql.tickers :as tickers]
            [clojure.string :refer :all]))


(defn company-details
  [ ticker ]
  "company details based on a ticker"
  ( let [ fticker ( upper-case ticker ) ]
   ( tickers/lookup fticker)))

(defn search-companies
 [ query ]
  "returns many companies that match a query"
  (tickers/search query))
