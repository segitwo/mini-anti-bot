(ns mini-antibot.handlers
  (:require [mini-antibot.db :as db]
            [mini-antibot.utils :refer [create-token]]
            [mini-antibot.pages.home-page :refer [home-page]]
            [mini-antibot.pages.login-page :refer [login-page]]
            [mini-antibot.pages.layouts :refer [main-layout without-header-layout]]
            [hiccup2.core :as hc]))

;; API
(defn get-all-users [request]
  {:status 200
   :body {:users (db/get-all-users)
          :identity (:identity request "No identity")}})

(defn register [{:keys [parameters]}]
  (let [data (:body parameters)
        user (db/create-user data)]
    {:status 201
     :body {:user user
            :token (create-token user)}}))

(defn login [{:keys [params]}]
  (let [user (db/get-user params)]
    (if (nil? user)
      {:status 404
       :body {:error "Invalid credentials"}}
      {:status 301
       :headers {"Location" "/"
                 "Set-Cookie" (str "token=" (create-token user) "; Path=/; httpOnly")}
       })))
;; Site
(defn login-form [_]
  {:status 200
   :header {"Content-Type" "text/html"}
   :body (-> (login-page)
             (without-header-layout)
             (hc/raw)
             (str))})

(defn home [_]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (-> (home-page)
             (main-layout)
             (hc/raw)
             (str))})
