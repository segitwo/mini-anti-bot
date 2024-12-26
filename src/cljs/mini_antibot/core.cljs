(ns mini-antibot.core
  (:require
   ;; [secretary.core :as secretary :refer-macros [defroute]]
   [reagent.core :as r]
   ;; [reagent.session :as rs]
   [reagent.dom :as rd]
   [mini-antibot.routing :refer [init-routes]]
   [mini-antibot.main-layout :refer [main-layout]]))


;; (secretary/set-config! :prefix "#")

#_(defroute "/" []
   (rs/put! :current-page #'login))

(defn main []
  [:<>
   [main-layout]])

(defn ^:export init []
  (init-routes)
  (rd/render [#'main]
             (.getElementById js/document "app")))

(defn ^:dev/after-load re-render []
  (init))
