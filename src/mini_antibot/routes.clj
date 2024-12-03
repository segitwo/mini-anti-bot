(ns mini-antibot.routes
  (:require [mini-antibot.handlers :as handle]
            [schema.core :as s]))

(defn dummy-handler [{:keys [parameters]}]
  {:status 200
   :body parameters})

(def ping-route
  ["/ping" {:name ::ping
            :get #'handle/ping}])

(def auth-routes
  [["/users" {:get handle/ping}]

   ["/login" {:post {:parameters {:body {:username s/Str
                                          :password s/Str}}
                      :handler dummy-handler}}]

   ["/register" {:post {:parameters {:body {:username s/Str
                                            :password s/Str
                                            :email s/Str}}
                        :handler dummy-handler}}]])
