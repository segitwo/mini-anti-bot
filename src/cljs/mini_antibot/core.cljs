(ns mini-antibot.core
  (:require 
            ;; [secretary.core :as secretary :refer-macros [defroute]]
            [reagent.core :as r]
            ;; [reagent.session :as rs]
            [reagent.dom :as rd]))
;; (secretary/set-config! :prefix "#")

#_(defroute "/" []
   (rs/put! :current-page #'login))

(defn main []
  [:<>
    [:div "hello world"]])

(defn ^:export init []
  (rd/render [#'main]
             (.getElementById js/document "app")))

(defn ^:dev/after-load re-render []
  (init))

(comment
  )
