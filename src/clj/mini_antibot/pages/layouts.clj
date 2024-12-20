(ns mini-antibot.pages.layouts
  (:require
   [hiccup.page :as hp]
   [mini-antibot.pages.common.header :refer [header]]))

(defn main-layout [content]
  (hp/html5 {:lang "ru"}
   [:head
    [:meta {:charset "UTF-8"}]
    [:title "Title"]
    (hp/include-css "/css/main.css")
    #_(hp/include-js)
    [:body
     header
     content]]))

(defn without-header-layout [content]
  (hp/html5 {:lang "ru"}
     [:head
      [:meta {:charset "UTF-8"}]
      [:title "Title"]
       (hp/include-css "/css/main.css")
       #_(hp/include-js)
      [:body content]]))
