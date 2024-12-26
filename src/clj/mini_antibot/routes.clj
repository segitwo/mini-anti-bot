(ns mini-antibot.routes
  (:require [mini-antibot.handlers :as handle]
            [schema.core :as s]
            [muuntaja.core :as m]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.multipart-params :refer [wrap-multipart-params]]
            [ring.middleware.cookies :refer [wrap-cookies]]
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

   ["/users" {:get {:middleware [wrap-jwt-authentication auth-middleware]
                    :handler #'handle/get-all-users}}]

   ;;TODO: Вынести coerce, jwt
   ["/add-ip" {:post {:coercion reitit.coercion.schema/coercion
                      :parameters {:body {:ip s/Str}}
                      :middleware [wrap-jwt-authentication
                                   auth-middleware
                                   coerce-exceptions-middleware
                                   coerce-request-middleware
                                   coerce-response-middleware]
                      :handler #'handle/add-ip}}]

   ["/add-domain" {:post {:coercion reitit.coercion.schema/coercion
                          :parameters {:body {:domain s/Str
                                              :ip s/Str}}
                          :middleware [wrap-jwt-authentication
                                       auth-middleware
                                       coerce-exceptions-middleware
                                       coerce-request-middleware
                                       coerce-response-middleware]
                          :handler {:status 200
                                    :body "Add domain"}}}]])

(def site-routes
  [["/" {:get {:handler #'handle/home}}]
   ["/login" {:post {;; :coercion reitit.coercion.schema/coercion
                     ;; :parameters {:body {:username s/Str
                     ;;                     :password s/Str}}
                     :middleware [wrap-multipart-params
                                  wrap-keyword-params
                                  #_coerce-exceptions-middleware
                                  #_coerce-request-middleware
                                  #_coerce-response-middleware]

                     :handler #'handle/login}
              :get {:handler #'handle/login-form}}]

   ["/logout" {:get {:handler #'handle/logout}}]

   ["/register" {:post {:parameters {:body {:username s/Str
                                            :password s/Str
                                            :email s/Str}}
                        :handler #'handle/register}}]

   ["/dashboard" {:get {:middleware [wrap-cookies]
                        :handler #'handle/dashboard}}]])

