(ns jayapp.web
  (:require [hiccup.core :as h]))

(defn- index-body
  []
  (h/html
    [:html
     [:head
      [:link {:href "bootstrap/3.3.7-1/css/bootstrap.css" :rel "stylesheet"}]
      [:link {:href "css/app.css" :rel "stylesheet"}]]
     [:body
      [:nav.navbar.navbar-inverse
       [:button.btn.btn-default.navbar-btn "Sample Button"]
       [:button.btn.btn-default.navbar-btn "Another Button"]]
      [:img {:src "img/trees.jpg"}]]])

  )

(defn index
  [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (index-body)})


(defn- app-body []
  (h/html
    [:html
     [:head
      [:link {:href "bootstrap/3.3.7-1/css/bootstrap.css" :rel "stylesheet"}]
      [:link {:href "css/app.css" :rel "stylesheet"}]]
     [:body
      [:div#app]
      [:script {:type "text/javascript" :src "js/app.js"}]
      [:script {:type "text/javascript"} "jayapp.client.main();"]]]))

(defn app
  [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (app-body)})

