(ns stock-app-backend.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [clojure.data.json :as json]
            [stock-app-backend.domain.ticker :refer :all]
            [stock-app-backend.domain.price :as prices]))

(defroutes app-routes

  (GET "/company/_search" [q]
       (search-response q))

  (GET "/company/_lookup/:ticker" [ticker]
       (lookup-response ticker))

  (GET "/daily/:ticker" [ticker]
       (prices/daily-response ticker))

  (GET "/last/:ticker" [ticker]
       (prices/last-response ticker))

  (GET "/weekly/:ticker" [ticker]
       (prices/weekly-response ticker))

  (route/not-found "Not Found"))


(defn content-type-response [response content-type]
  (assoc-in response [:headers "Content-Type"] content-type))

(defn wrap-content-type [handler content-type]
  (fn
    ([request]
      (-> (handler request) (content-type-response content-type)))
    ([request respond raise]
      (handler request #(respond (content-type-response % content-type)) raise))))

(def app
  (wrap-content-type
   (wrap-defaults app-routes site-defaults)
   "application/json"))
