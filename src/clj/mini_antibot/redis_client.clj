(ns mini-antibot.redis-client
  (:require [taoensso.carmine :as car :refer [wcar]]))

(defonce redis-conn-pool (car/connection-pool {}))
(def redis-conn-spec {:uri "redis://localhost:6379/"}) ;;redis in container
(def redis-wcar-opt {:pool redis-conn-pool :spec redis-conn-spec})

(defmacro wcar* [& body] `(wcar redis-wcar-opt ~@body))

(comment
  (wcar* (car/ping)
         (car/set "foo" "bar")
         (car/get "foo")))
