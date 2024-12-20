(ns mini-antibot.db
  (:require
   [buddy.hashers :refer [encrypt check]]
   [honeysql.core :as h]
   [honeysql.helpers :as hh]
   [next.jdbc :as jdbc]
   [next.jdbc.result-set :as rs]))

(def db-config
  {:dbtype "postgresql"
   :dbname "mab"
   :host "localhost" ;;postgres 
   :user "mab"
   :password "mab"})

(def db
  (jdbc/get-datasource db-config))

(defn db-query [sql]
  (jdbc/execute! db sql
                 {:return-keys true
                  :builder-fn rs/as-unqualified-maps}))

(defn db-query-one [sql]
  (jdbc/execute-one! db sql
                     {:return-keys true
                      :builder-fn rs/as-unqualified-maps}))

(defn create-user [{:keys [username password email]}]
  (let [hashed-password (encrypt password)
        user (->
              (hh/insert-into :users)
              (hh/columns :email :username :password)
              (hh/values [[email username hashed-password]])
              (h/format)
              (db-query-one))
        sanitazed-user (dissoc user :password)]
    sanitazed-user))

(defn get-user [{:keys [email password]}]
  (let [user (->
              (hh/select :*)
              (hh/from :users)
              (hh/where [:= :email email])
              (h/format)
              (db-query-one))
        sanitized-user (dissoc user :password)]
    (if (and user (check password (:password user)))
      sanitized-user
      nil)))

(defn get-all-users []
  (let [users (->
               (hh/select :*)
               (hh/from :users)
               (h/format)
               (db-query))

        sanitized-users (map #(dissoc % :password) users)]
    sanitized-users))
