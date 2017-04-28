(ns ^:config jayapp.config
  (:require [arachne.core.dsl :as a]
            [arachne.http.dsl :as http]
            [arachne.pedestal.dsl :as ped]))

(a/id ::server
  (ped/server 8080
    (http/endpoint :get "/" (http/handler 'jayapp.web/hello))))

(a/id ::rt (a/runtime [::server]))

