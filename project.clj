(defproject stock-app-backend "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/java.jdbc "0.7.10"]
                 [org.postgresql/postgresql "42.1.3"]
                 [clj-http "3.10.0"]
                 [yogthos/config "1.1.6"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler stock-app-backend.handler/app}
  :profiles
  {:prod {:resource-paths ["config/prod"]}
   :dev  {:resource-paths ["config/dev"]
          :dependencies [[javax.servlet/servlet-api "2.5"]
                         [ring/ring-mock "0.3.2"]]}})
