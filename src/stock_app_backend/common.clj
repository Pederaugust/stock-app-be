(ns stock-app-backend.common
  ( :require [ clojure.data.json :as json ]
             [ clj-http.client :as client]))

(defn get-response [ resp ]
  {:status  200
   :headers { "Access-Control-Allow-Origin"  "*" }
   :body    (json/write-str resp)})

(defn url-to-response-body
  [url]
  "takes a url, performs a request, returns the response body"
  (-> url
      client/get
      :body
      json/read-str))


(defn remove-last-letter
  [element]
  (apply
   str
   (-> element
       (drop-last ,,,))))
