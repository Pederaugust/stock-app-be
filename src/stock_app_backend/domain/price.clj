(ns stock-app-backend.domain.price
  (:require [clojure.string :refer :all]
            [stock-app-backend.adapters.alphavantage.prices :as prices]
            [stock-app-backend.common :refer :all]))

(defn weekly-response
  [ticker]
  "returns a response containing the result of finding the weekly prices of a company"
  (get-response (prices/weekly-closing-prices ticker)))

(defn last-response
  [ticker]
  "returns a response containing only the last prices of a company"
  (get-response (prices/company-trading-info ticker)))

(defn daily-response
  [ticker]
  "returns a response containing the daily closing prices of a company"
  (get-response (prices/daily-closing-prices ticker)))
