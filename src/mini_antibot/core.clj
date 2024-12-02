(ns mini-antibot.core
  (:require [org.httpkit.server :refer [run-server]]
            [reitit.ring :as ring]
            [muuntaja.core :as m]
            [reitit.ring.middleware.exception :refer [exception-middleware]]
            [reitit.ring.middleware.muuntaja :refer [format-request-middleware
                                                     format-response-middleware
                                                     format-negotiate-middleware]]))

(defonce server (atom nil))

(def app
  (ring/ring-handler
   (ring/router
    ["/api"
     ["/ping" {:name ::ping
               :get (fn [_]
                      {:status 200
                       :body {:hello "World"}})}]]
    {:data {:muuntaja m/instance 
            :middleware [format-negotiate-middleware
                         format-response-middleware
                         exception-middleware
                         format-request-middleware]}})))

(defn stop-server []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)))

(defn -main
  []
  (println "Server started on port 8080")
  (reset! server (run-server #'app {:port 8080})))

(comment
  @server
  (stop-server)
  (-main))
