
(ns stock-app-backend.domain.ticker
  (:require [stock-app-backend.adapters.sql.tickers :as tickers]
            [clojure.string :refer :all]
            [stock-app-backend.common :refer :all]))


(defn lookup-response
  [ ticker ]
  "Returns a standard response containing a lookup result based on a ticker"
  ( let [ fticker ( upper-case ticker ) ]
       (-> ( tickers/lookup fticker )
           ( first ,,,)
           ( get-response ,,,))))

(defn search-response
 [ query ]
  "Returns a standard response containing a search result based on a query"
 (-> (tickers/search query)
     (get-response ,,,)))
