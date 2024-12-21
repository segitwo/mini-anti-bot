(ns mini-antibot.pages.dashboard-page
  (:require
    [hiccup.page :as hp]))

(defn dashboard-page []
  (hp/html5 {:lang "ru"}
    [:head
      [:meta {:charset "UTF-8"}]
      [:title "Title"]
      (hp/include-css "/css/main.css")
      #_(hp/include-js)
      [:body
      [:div "Dashbard page"]]]))
