(ns jayapp.web
  (:require [hiccup.core :as h]))

(defn head
  "Return Hiccup data for the application's HTML head"
  []
  [:head
   [:link {:href "css/app.css" :rel "stylesheet"}]])

(defn- hello-body
  []
  (h/html
    [:html
     (head)
     [:body
      [:h1 "Think of the trees!"]
      [:img {:src "img/trees.jpg"}]]]))

(defn hello
  [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (hello-body)})

