(ns mini-antibot.core
  (:gen-class)
  (:require [org.httpkit.server :refer [run-server]]
            [reitit.ring :as ring]
            [reitit.core :as r]
            [ring.middleware.resource :refer [wrap-resource]]
            [mini-antibot.routes :refer [auth-routes site-routes]]
            ))

(defonce server (atom nil))

(def app
  (ring/ring-handler
   (ring/router
    [auth-routes site-routes])
   (ring/routes
    (wrap-resource (constantly {:status 404 :body "Not found"}) "public")
    (ring/create-default-handler))))

(comment
  (-> app (ring/get-router) (r/compiled-routes)))

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
