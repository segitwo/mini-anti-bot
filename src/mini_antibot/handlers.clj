(ns mini-antibot.handlers)

(defn ping [_]
  {:status 200
   :body {:hello "World"}})
