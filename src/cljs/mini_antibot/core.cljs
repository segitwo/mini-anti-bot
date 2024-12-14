(ns mini-antibot.core
  (:require [mini-antibot.components.site.header :refer [header-component]]
            [mini-antibot.components.site.home :refer [home-component]]
            [mini-antibot.components.site.login :refer [login-component]]
            [mini-antibot.router :refer [route]]
            [secretary.core :as secretary :refer-macros [defroute]]
            [reagent.core :as r]
            [reagent.session :as rs]
            [reagent.dom :as rd]))
(secretary/set-config! :prefix "#")

(defroute "/" []
   (rs/put! :current-page #'home-component))

(defn main-compponent []
  [:<>
   [header-component]
   [(cond
       (= @route "home") home-component
       (= @route "login") login-component)]])

(defn ^:export init []
  (rd/render [#'main-compponent]
                      (js/document.getElementById "app")))

(defn ^:dev/after-load re-render []
  (init))

(comment
  (reset! route "home")
  (reset! route "login"))
