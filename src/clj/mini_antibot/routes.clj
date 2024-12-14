(ns mini-antibot.routes
  (:require [mini-antibot.handlers :as handle]
            [schema.core :as s]
            [mini-antibot.utils :refer [wrap-jwt-authentication auth-middleware]]))
 
(def auth-routes
  [["/users" {:get {:middleware [wrap-jwt-authentication auth-middleware]
                    :handler #'handle/get-all-users}}]

   ["/login" {:post {:parameters {:body {:username s/Str
                                         :password s/Str}}
                     :handler #'handle/login}}]

   ["/register" {:post {:parameters {:body {:username s/Str
                                            :password s/Str
                                            :email s/Str}}
                        :handler #'handle/register}}]])
