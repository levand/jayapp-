(ns ^:config jayapp.config
  (:require [arachne.core.dsl :as a]
            [arachne.http.dsl :as http]
            [arachne.pedestal.dsl :as ped]
            [arachne.assets.dsl :as assets]
            [arachne.pedestal-assets.dsl :as passets]
            [arachne.sass.dsl :as sass]
            [arachne.cljs.dsl :as cljs]
            [arachne.figwheel.dsl :as fig]))

(def dev? (constantly true))

;; Runtime
(a/id ::rt (a/runtime [::server]))

;; Pedestal Server
(a/id ::server
  (ped/server 8080

    (a/id ::asset-interceptor (passets/interceptor :index? false))

    (http/endpoint :get "/" (http/handler 'jayapp.web/index))

    (http/endpoint :get "/app" (http/handler 'jayapp.web/app))

    ))

;; Application Assets
(a/id ::public-dir (assets/input-dir "public" :watch? (dev?)))
(assets/pipeline [::public-dir ::asset-interceptor])

;; SCSS Pipeline
(a/id ::scss-dir (assets/input-dir "sass" :classpath? true :watch? (dev?)))

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

;; ClojureScript & Figwheel

(def cljs-opts {:main 'jayapp.client
                :optimizations :none
                :asset-path "js/out"
                :output-to "js/app.js"
                :output-dir "js/out"
                :source-map-timestamp true})

(a/id ::src-dir (assets/input-dir "src" :watch? (dev?)))

(a/id ::cljs-build (cljs/build cljs-opts))
(assets/pipeline [::src-dir ::cljs-build])

(a/id ::figwheel (fig/server cljs-opts :port 8888
                                       :css? true))

(assets/pipeline
  [::src-dir ::figwheel #{:src}]
  [::public-dir ::figwheel #{:public}]
  [::webjars ::figwheel #{:public}]
  [::scss-build ::figwheel #{:public}])

(assets/pipeline [(if (dev?) ::figwheel ::cljs) ::asset-interceptor])
