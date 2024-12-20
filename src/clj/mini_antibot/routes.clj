(ns mini-antibot.routes
  (:require [mini-antibot.handlers :as handle]
            [schema.core :as s]
            [muuntaja.core :as m]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.multipart-params :refer [wrap-multipart-params]]
            [reitit.coercion.schema]
            [reitit.ring.coercion :refer [coerce-exceptions-middleware
                                          coerce-request-middleware
                                          coerce-response-middleware]]
            [reitit.ring.middleware.exception :refer [exception-middleware]]
            [reitit.ring.middleware.muuntaja :refer [format-request-middleware
                                                     format-response-middleware
                                                     format-negotiate-middleware]]
            [mini-antibot.utils :refer [wrap-jwt-authentication auth-middleware]]))

(def auth-routes
  ["/api" {:muuntaja m/instance
           :middleware [format-negotiate-middleware
                        format-response-middleware
                        exception-middleware
                        format-request-middleware]}

   ["/users" {:get {;; :coercion reitit.coercion.schema/coercion
                    :middleware [                                 wrap-jwt-authentication auth-middleware]

                    :handler #'handle/get-all-users}}]])

(def site-routes
  [["/" {:get {:handler #'handle/home}}]
   ["/login" {:post {;; :coercion reitit.coercion.schema/coercion
                     ;; :parameters {:body {:username s/Str
                     ;;                          :password s/Str}}
                     :middleware [wrap-multipart-params
                                  wrap-keyword-params
                                  #_coerce-exceptions-middleware
                                  #_coerce-request-middleware
                                  #_coerce-response-middleware]

                     :handler #'handle/login}
              :get {:handler #'handle/login-form}}]

   ["/register" {:post {:parameters {:body {:username s/Str
                                            :password s/Str
                                            :email s/Str}}
                        :handler #'handle/register}}]])

