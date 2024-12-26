(ns mini-antibot.main-layout
  (:require
   [mini-antibot.nav-bar :refer [nav-bar]]
   [mini-antibot.left-sidebar :refer [left-sidebar]]
   [reagent.core :as r]))

(defn current-conent []
  (fn []
    (if-let [match @mini-antibot.routing/route]
      [(:view (:data match))]
      [:div "Page not found"])))

(defn main-layout []
  [:<>
   [nav-bar]
   [left-sidebar]
   [:div {:class "p-4 sm:ml-64"}
    [:div {:class "p-4 border-2 border-gray-200 border-dashed rounded-lg dark:border-gray-700 mt-14"}
     ;; [:div {:class "grid grid-cols-3 gap-4 mb-4"}
     ;;  [:div {:class "flex items-center justify-center h-24 rounded bg-gray-50 dark:bg-gray-800"}
     ;;   [:p {:class "text-2xl text-gray-400 dark:text-gray-500"}
     ;;    [:svg {:class "w-3.5 h-3.5", :aria-hidden "true", :xmlns "http://www.w3.org/2000/svg", :fill "none", :viewBox "0 0 18 18"}
     ;;     [:path {:stroke "currentColor", :stroke-linecap "round", :stroke-linejoin "round", :stroke-width "2", :d "M9 1v16M1 9h16"}]]]]
     ;;  [:div {:class "flex items-center justify-center h-24 rounded bg-gray-50 dark:bg-gray-800"}
     ;;   [:p {:class "text-2xl text-gray-400 dark:text-gray-500"}
     ;;    [:svg {:class "w-3.5 h-3.5", :aria-hidden "true", :xmlns "http://www.w3.org/2000/svg", :fill "none", :viewBox "0 0 18 18"}
     ;;     [:path {:stroke "currentColor", :stroke-linecap "round", :stroke-linejoin "round", :stroke-width "2", :d "M9 1v16M1 9h16"}]]]]
     ;;  [:div {:class "flex items-center justify-center h-24 rounded bg-gray-50 dark:bg-gray-800"}
     ;;   [:p {:class "text-2xl text-gray-400 dark:text-gray-500"}
     ;;    [:svg {:class "w-3.5 h-3.5", :aria-hidden "true", :xmlns "http://www.w3.org/2000/svg", :fill "none", :viewBox "0 0 18 18"}
     ;;     [:path {:stroke "currentColor", :stroke-linecap "round", :stroke-linejoin "round", :stroke-width "2", :d "M9 1v16M1 9h16"}]]]]]
     ;; [:h3 "Block IPs by Country"]
     ;; [:div {:class "flex h-48 mb-4 rounded bg-gray-50 dark:bg-gray-800"}
     ;;  [:div {:class "mx-5"}
     ;;   [:div
     ;;    [:select {:id "countries", :class "bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"}
     ;;     [:option {:selected ""} "Choose a country"]
     ;;     [:option {:value "US"} "United States"]
     ;;     [:option {:value "CA"} "Canada"]
     ;;     [:option {:value "FR"} "France"]
     ;;     [:option {:value "DE"} "Germany"]]]]
     ;;  [:div
     ;;   [:button "Add Country"]]]

     [current-conent]
;; [:div {:class "grid grid-cols-2 gap-4 mb-4"}
     ;;  [:div {:class "flex items-center justify-center rounded bg-gray-50 h-28 dark:bg-gray-800"}
     ;;   [:p {:class "text-2xl text-gray-400 dark:text-gray-500"}
     ;;    [:svg {:class "w-3.5 h-3.5", :aria-hidden "true", :xmlns "http://www.w3.org/2000/svg", :fill "none", :viewBox "0 0 18 18"}
     ;;     [:path {:stroke "currentColor", :stroke-linecap "round", :stroke-linejoin "round", :stroke-width "2", :d "M9 1v16M1 9h16"}]]]]
     ;;  [:div {:class "flex items-center justify-center rounded bg-gray-50 h-28 dark:bg-gray-800"}
     ;;   [:p {:class "text-2xl text-gray-400 dark:text-gray-500"}
     ;;    [:svg {:class "w-3.5 h-3.5", :aria-hidden "true", :xmlns "http://www.w3.org/2000/svg", :fill "none", :viewBox "0 0 18 18"}
     ;;     [:path {:stroke "currentColor", :stroke-linecap "round", :stroke-linejoin "round", :stroke-width "2", :d "M9 1v16M1 9h16"}]]]]
     ;;  [:div {:class "flex items-center justify-center rounded bg-gray-50 h-28 dark:bg-gray-800"}
     ;;   [:p {:class "text-2xl text-gray-400 dark:text-gray-500"}
     ;;    [:svg {:class "w-3.5 h-3.5", :aria-hidden "true", :xmlns "http://www.w3.org/2000/svg", :fill "none", :viewBox "0 0 18 18"}
     ;;     [:path {:stroke "currentColor", :stroke-linecap "round", :stroke-linejoin "round", :stroke-width "2", :d "M9 1v16M1 9h16"}]]]]
     ;;  [:div {:class "flex items-center justify-center rounded bg-gray-50 h-28 dark:bg-gray-800"}
     ;;   [:p {:class "text-2xl text-gray-400 dark:text-gray-500"}
     ;;    [:svg {:class "w-3.5 h-3.5", :aria-hidden "true", :xmlns "http://www.w3.org/2000/svg", :fill "none", :viewBox "0 0 18 18"}
     ;;     [:path {:stroke "currentColor", :stroke-linecap "round", :stroke-linejoin "round", :stroke-width "2", :d "M9 1v16M1 9h16"}]]]]]
     ;; [:div {:class "flex items-center justify-center h-48 mb-4 rounded bg-gray-50 dark:bg-gray-800"}
     ;;  [:p {:class "text-2xl text-gray-400 dark:text-gray-500"}
     ;;   [:svg {:class "w-3.5 h-3.5", :aria-hidden "true", :xmlns "http://www.w3.org/2000/svg", :fill "none", :viewBox "0 0 18 18"}
     ;;    [:path {:stroke "currentColor", :stroke-linecap "round", :stroke-linejoin "round", :stroke-width "2", :d "M9 1v16M1 9h16"}]]]]
     #_[:div {:class "grid grid-cols-2 gap-4"}
        [:div {:class "flex items-center justify-center rounded bg-gray-50 h-28 dark:bg-gray-800"}
         [:p {:class "text-2xl text-gray-400 dark:text-gray-500"}
          [:svg {:class "w-3.5 h-3.5", :aria-hidden "true", :xmlns "http://www.w3.org/2000/svg", :fill "none", :viewBox "0 0 18 18"}
           [:path {:stroke "currentColor", :stroke-linecap "round", :stroke-linejoin "round", :stroke-width "2", :d "M9 1v16M1 9h16"}]]]]
        [:div {:class "flex items-center justify-center rounded bg-gray-50 h-28 dark:bg-gray-800"}
         [:p {:class "text-2xl text-gray-400 dark:text-gray-500"}
          [:svg {:class "w-3.5 h-3.5", :aria-hidden "true", :xmlns "http://www.w3.org/2000/svg", :fill "none", :viewBox "0 0 18 18"}
           [:path {:stroke "currentColor", :stroke-linecap "round", :stroke-linejoin "round", :stroke-width "2", :d "M9 1v16M1 9h16"}]]]]
        [:div {:class "flex items-center justify-center rounded bg-gray-50 h-28 dark:bg-gray-800"}
         [:p {:class "text-2xl text-gray-400 dark:text-gray-500"}
          [:svg {:class "w-3.5 h-3.5", :aria-hidden "true", :xmlns "http://www.w3.org/2000/svg", :fill "none", :viewBox "0 0 18 18"}
           [:path {:stroke "currentColor", :stroke-linecap "round", :stroke-linejoin "round", :stroke-width "2", :d "M9 1v16M1 9h16"}]]]]
        [:div {:class "flex items-center justify-center rounded bg-gray-50 h-28 dark:bg-gray-800"}
         [:p {:class "text-2xl text-gray-400 dark:text-gray-500"}
          [:svg {:class "w-3.5 h-3.5", :aria-hidden "true", :xmlns "http://www.w3.org/2000/svg", :fill "none", :viewBox "0 0 18 18"}
           [:path {:stroke "currentColor", :stroke-linecap "round", :stroke-linejoin "round", :stroke-width "2", :d "M9 1v16M1 9h16"}]]]]]]]])