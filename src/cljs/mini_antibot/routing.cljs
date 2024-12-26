(ns mini-antibot.routing
  (:require
   [reagent.core :as r]
   [reitit.frontend :as reitit]
   [reitit.frontend.easy :as reitit-easy]
   [mini-antibot.content.adding-domains :refer [adding-domains]]
   [mini-antibot.content.manual-block :refer [manual-block]]))

(def route (r/atom nil))

(def routes
  [["/"
    {:name :home
     :view #'adding-domains}]
   ["/manual-block"
    {:name :manual-block
     :view #'manual-block}]])

(defn init-routes []
  (reitit-easy/start!
   (reitit/router routes)
   (fn [m _] (reset! route m))
   {:use-fragment true
    :default (fn [_]
               [:div "Not found"])}))
