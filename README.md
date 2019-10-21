# stock-app-backend

This small api serves stock information in a clean format, meant to be used with
a specific frontend.

It basically only exists because I wanted to learn clojure, and hook something
up to a React js and redux frontend

## Plans for the future

Use graphql instead of REST

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

run lein deps ;)
``` sh
lein deps
```

## Database config

you will need a config-folder with a dev environment:

``` sh
config/dev/config.edn
```

it will need these parameters

``` edn
{
 :db {
      :dbtype "postgresql"
      :dbname "db-name"
      :host "localhost or something"
      :port "usually 5432"
      :user "choose"
      :password "verysecret"
      }

 :alphav-api-key "get this from alpha vantage"
 }
```

Also you will need to create the db and schema, found in resources is a sql
script to create the schema. filling it up is up to you, but the tickers must be
compatible with alpha vantage to work. (finding ticker and stock information is
available for free on the internet)

## Running

To start a web server for the application, run:

    lein ring server

## License

Copyright © 2019 Peder August Fasting
