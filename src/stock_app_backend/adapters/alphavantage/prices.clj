(ns stock-app-backend.adapters.alphavantage.prices
  (:require [clojure.string :refer :all]
            [clojure.data.json :as json]
            [stock-app-backend.common :refer :all]))

(def api-key "VLH6ZNI2E67UJSWA")

(def alpha-vantage-url "https://www.alphavantage.co/query?function=")

;; URLs
(defn- daily-url
  [ticker]
  (str alpha-vantage-url "TIME_SERIES_DAILY&symbol=" ticker "&interval=5min&apikey=" api-key))

(defn- weekly-url
  [ticker]
  (str alpha-vantage-url "TIME_SERIES_WEEKLY&symbol=" ticker "&apikey=" api-key))

(defn- last-url
  [ticker]
  (str alpha-vantage-url "GLOBAL_QUOTE&symbol=" ticker "&apikey=" api-key))


;; Client Requests

(defn- daily-time-series
  [ticker]
  (-> (url-to-response-body (daily-url ticker))
      (get ,,, "Time Series (Daily)")))

(defn- weekly-time-series
  [ticker]
  (-> (url-to-response-body (weekly-url ticker))
      (get ,,, "Weekly Time Series")))

(defn last-quote
  [ticker]
  (-> (url-to-response-body
          (last-url ticker))
      (get ,,, "Global Quote")))

;;Formatting
(defn- get-close-from-time-series
  [series]
  (map
   (fn [e]
     {:time  (first e)
      :close (get (nth e 1) "4. close")})
   (seq series)))

(defn daily-closing-prices
  [ticker]
  (-> (daily-time-series ticker)
       get-close-from-time-series))

(defn weekly-closing-prices
  [ticker]
  (-> (weekly-time-series ticker)
      get-close-from-time-series))


(defn company-trading-info [ticker]
  "Maps the information to a clojure object from the alpha vantage api"
  (let [e (last-quote ticker)]
          {:open   (get e "02. open")
           :high   (get e "03. high")
           :low    (get e "04. low")
           :price  (get e "05. price")
           :volume (get e "06. volume")
           :change (get e "09. change")
           :changeInPercent (remove-last-letter (get e "10. change percent"))}))
