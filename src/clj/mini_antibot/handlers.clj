(ns mini-antibot.handlers
  (:require [mini-antibot.db :as db]
            [mini-antibot.utils :refer [create-token unsign-token]]
            [mini-antibot.pages.home-page :refer [home-page]]
            [mini-antibot.pages.login-page :refer [login-page]]
            [mini-antibot.pages.dashboard-page :refer [dashboard-page]]
            [mini-antibot.pages.layouts :refer [main-layout without-header-layout]]
            [taoensso.carmine :as car]
            [mini-antibot.redis-client :refer [wcar*]]
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

(defn add-ip [{:keys [parameters]}]
  (let [ip (get-in parameters [:body :ip])]
    (wcar* (car/set (str "domainip:domain:" ip) "mblock"))
    {:status 200
     :body ip}))

;; Site
(defn login [{:keys [params]}]
  (let [user (db/get-user params)]
    (if (nil? user)
      {:status 404
       :body {:error "Invalid credentials"}}
      {:status 301
       :headers {"Location" "/dashboard"
                 "Set-Cookie" (str "token=" (create-token user) "; Path=/dashboard")}})))

(defn login-form [_]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (-> (login-page)
             (without-header-layout)
             (hc/raw)
             (str))})

(defn logout [_]
  {:status 301 
   :headers {"Location" "/login"
            "Set-Cookie" "token=; Max-Age=0; Path=/dashboard"}})

(defn home [_]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (-> (home-page)
             (main-layout)
             (hc/raw)
             (str))})

(defn dashboard [request]
  (let [token (:value (get-in request [:cookies "token"]))
        redirect-header {"Location" "/login"}]
    (if (nil? token)
      {:status 301
       :headers redirect-header}
      (let [token-user (unsign-token token)
            user (db/get-user-by-email token-user)]
        (if (nil? user)
          {:status 301
           :headers redirect-header}
          {:status 200
           :body (-> (dashboard-page)
                     (hc/raw)
                     (str))})))))

(comment
  (db/get-user (unsign-token "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ1c2VybmFtZSIsImVtYWlsIjoidXNlckBlbWFpbC5jb20ifQ.fNVPGg26Cd7FPu8ycgFYMiQTnYd3J6zLTIVxhi8-uCM")))
