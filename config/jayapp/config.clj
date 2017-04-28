(ns ^:config jayapp.config
  (:require [arachne.core.dsl :as a]
            [arachne.http.dsl :as http]
            [arachne.pedestal.dsl :as ped]
            [arachne.assets.dsl :as assets]
            [arachne.pedestal-assets.dsl :as passets]
            [arachne.sass.dsl :as sass]))

;; Runtime
(a/id ::rt (a/runtime [::server]))

;; Pedestal Server
(a/id ::server
  (ped/server 8080

    (a/id ::asset-interceptor (passets/interceptor :index? false))

    (http/endpoint :get "/" (http/handler 'jayapp.web/index))

    ))

;; Application Assets
(a/id ::public-dir (assets/input-dir "public" :watch? true))
(assets/pipeline [::public-dir ::asset-interceptor])

;; SCSS Pipeline
(a/id ::scss-dir (assets/input-dir "sass" :classpath? true :watch? true))

(a/id ::scss-build (sass/build {:entrypoint "app.scss"
                                :output-to "app.css"
                                :output-dir "css"
                                :source-map true
                                :precision 6}))

(assets/pipeline [::scss-dir ::scss-build]
                 [::scss-build ::asset-interceptor])

;; Boostrap Assets
(a/id ::webjars (assets/input-dir "META-INF/resources/webjars" :classpath? true))
(assets/pipeline [::webjars ::asset-interceptor])



