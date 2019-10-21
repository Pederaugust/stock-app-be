(ns stock-app-backend.adapters.sql.tickers
  (:require [compojure.core :refer :all]
            [config.core :refer [env]]
            [clojure.edn :as edn]
            [clojure.java.jdbc :as jdbc]
            [clojure.string :refer :all]))

(def psql-db (:db env))

(defn lookup [ticker]
  (jdbc/query psql-db
           ["SELECT name, ticker, sector, country FROM company WHERE ticker = ?" ticker]))

(defn search
  [q]
  (let [wildq (str "%" (upper-case q) "%")]
    (if (empty? q)
      []
      (jdbc/query psql-db
                  ["SELECT name, ticker, sector, country  FROM company WHERE (UPPER(ticker) LIKE ?) OR (UPPER(name) LIKE ?) LIMIT 10" wildq wildq]))))
