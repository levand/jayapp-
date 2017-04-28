(ns jayapp.client
  (:require [rum.core :as rum]))

(rum/defc button < rum/static
  [text]
  [:button.btn.btn-default text])

(rum/defc page < rum/static
  []
  [:div
   [:h1 "Save the trees!"]
   [:img {:src "img/trees.jpg"}]
   (button "Save them!")])

(defn ^:export main
  []
  (rum/mount
    (page)
    (.getElementById js/document "app")))