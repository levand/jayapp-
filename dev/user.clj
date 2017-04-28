(ns user
  (:require [reloaded.repl :refer [system init start stop go reset reset-all]]
            [com.stuartsierra.component :as c]
            [arachne.core :as arachne]
            ))

(defn my-init
  "Initialization function, returns a SierraComponent"
  []
  (let [cfg (arachne/config :jayapp/application)
        rt (arachne/runtime cfg :jayapp.config/rt)]
    rt))

(reloaded.repl/set-init! my-init)