(ns mini-antibot.handlers
  (:require [mini-antibot.db :as db]
            [mini-antibot.utils :refer [create-token]]))

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

(defn login [{:keys [parameters]}]
  (let [data (:body parameters)
        user (db/get-user data)]
    (if (nil? user)
      {:status 404
       :body {:error "Invalid credentials"}}
      {:status 200
       :body {:user user
              :token (create-token user)}}))
  )
