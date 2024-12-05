(ns mini-antibot.core
  (:gen-class)
  (:require [org.httpkit.server :refer [run-server]]
            [reitit.ring :as ring]
            [reitit.coercion.schema]
            [reitit.ring.coercion :refer [coerce-exceptions-middleware
                                          coerce-request-middleware
                                          coerce-response-middleware]]
            [mini-antibot.routes :refer [auth-routes]]
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
     auth-routes]
    {:data {:coercion reitit.coercion.schema/coercion
            :muuntaja m/instance
            :middleware [format-negotiate-middleware
                         format-response-middleware
                         exception-middleware
                         format-request-middleware
                         coerce-exceptions-middleware
                         coerce-request-middleware
                         coerce-response-middleware]}})))

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
