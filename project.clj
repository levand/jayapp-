(defproject jayapp "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]
                 [reloaded.repl "0.2.3"]
                 [org.arachne-framework/arachne-pedestal "0.2.0-master-0061-e16dc3d"]
                 [org.arachne-framework/pedestal-assets "0.2.0-master-0014-8fd438f"]
                 [org.arachne-framework/arachne-sass "0.2.0-master-0024-51d4bce"]
                 [datascript "0.16.1"]
                 [ch.qos.logback/logback-classic "1.1.3"]
                 [hiccup "1.0.5"]]
  :source-paths ["src" "config"]
  :profiles {:dev {:source-paths ["src" "dev" "config"]}}
  :repositories [["arachne-dev"
                  "http://maven.arachne-framework.org/artifactory/arachne-dev"]])