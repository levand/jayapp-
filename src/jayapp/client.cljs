(ns jayapp.client
  (:require [rum.core :as rum]))

(rum/defc button < rum/static
  [text]
  [:button.btn.btn-default text])

(rum/defc page < rum/static
  []
  [:div
   [:nav.navbar.navbar-inverse
    [:div.container-fluid
     [:div.navbar-header
      [:button {:type "button"}
       [:span "Hello!"]]]]]
   [:h1 "Save the bees!"]

   [:img {:src "img/trees.jpg"}]
   (button "Save them!")])

(defn render []
  (rum/mount
    (page)
    (.getElementById js/document "app")))

(defn ^:export main
  []
  (render))

(defn onload
  []
  (render))
