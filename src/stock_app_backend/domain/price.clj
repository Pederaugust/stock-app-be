(ns stock-app-backend.domain.price
  (:require [clojure.string :refer :all]
            [stock-app-backend.adapters.alphavantage.prices :as prices]
            [stock-app-backend.common :refer :all]))

(defn weekly-timeseries
  [ticker]
  "weekly prices of a company from ticker"
  (let [prices (prices/weekly-closing-prices ticker)]
  prices))

(defn last-price-details
  [ticker]
  "last prices of a company from ticker"
  (prices/company-trading-info ticker))

(defn daily-timeseries
  [ticker]
  "daily closing prices of a company from ticker"
  (prices/daily-closing-prices ticker))
