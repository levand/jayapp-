(ns user
  (:require [reloaded.repl :refer [system init start stop go reset reset-all]]
            [com.stuartsierra.component :as c]
            ))

(defn my-init
  "Initialization function, returns a SierraComponent"
  []
  (println "Initializing system...")
  (reify c/Lifecycle
    (start [this]
      (println "Starting system...")
      this)
    (stop [this]
      (println "Stopping system...")
      this)))

(reloaded.repl/set-init! my-init)