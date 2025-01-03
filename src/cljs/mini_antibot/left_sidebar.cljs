(ns mini-antibot.left-sidebar
  (:require
   [reitit.frontend.easy :as reitii-easy]
   
   [reitit.frontend.easy :as reitit-easy]))

(defn left-sidebar []
  [:aside {:id "logo-sidebar", :class "fixed top-0 left-0 z-40 w-64 h-screen pt-20 transition-transform -translate-x-full bg-white border-r border-gray-200 sm:translate-x-0 dark:bg-gray-800 dark:border-gray-700", :aria-label "Sidebar"}
   [:div {:class "h-full px-3 pb-4 overflow-y-auto bg-white dark:bg-gray-800"}
    [:ul {:class "space-y-2 font-medium"}
     [:li
      [:a {:href (reitii-easy/href :home), :class "flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group"}
       [:svg {:class "w-5 h-5 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white", :aria-hidden "true", :xmlns "http://www.w3.org/2000/svg", :fill "currentColor", :viewBox "0 0 22 21"}
        [:path {:d "M16.975 11H10V4.025a1 1 0 0 0-1.066-.998 8.5 8.5 0 1 0 9.039 9.039.999.999 0 0 0-1-1.066h.002Z"}]
        [:path {:d "M12.5 0c-.157 0-.311.01-.565.027A1 1 0 0 0 11 1.02V10h8.975a1 1 0 0 0 1-.935c.013-.188.028-.374.028-.565A8.51 8.51 0 0 0 12.5 0Z"}]]
       [:span {:class "ms-3"} "Добавить домен"]]]
     [:li
      [:a {:href (reitit-easy/href :manual-block), :class "flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group"}
       [:svg {:class "w-5 h-5 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white", :aria-hidden "true", :xmlns "http://www.w3.org/2000/svg", :fill "currentColor", :viewBox "0 0 22 21"}
        [:path {:d "M16.975 11H10V4.025a1 1 0 0 0-1.066-.998 8.5 8.5 0 1 0 9.039 9.039.999.999 0 0 0-1-1.066h.002Z"}]
        [:path {:d "M12.5 0c-.157 0-.311.01-.565.027A1 1 0 0 0 11 1.02V10h8.975a1 1 0 0 0 1-.935c.013-.188.028-.374.028-.565A8.51 8.51 0 0 0 12.5 0Z"}]]
       [:span {:class "ms-3"} "Ручная блокировка"]]]
     [:li
      [:a {:href "/logout", :class "flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group"}
       [:svg {:class "w-6 h-6 text-gray-800 dark:text-white", :aria-hidden "true" :xmlns "http://www.w3.org/2000/svg" :width "24" :height "24" :fill "none" :viewBox "0 0 24 24"}
        [:path {:stroke "currentColor", :stroke-linecap "round", :stroke-linejoin "round", :stroke-width "2", :d "M20 12H8m12 0-4 4m4-4-4-4M9 4H7a3 3 0 0 0-3 3v10a3 3 0 0 0 3 3h2"}]]
       #_[:svg {:class "w-5 h-5 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white", :aria-hidden "true", :xmlns "http://www.w3.org/2000/svg", :fill "currentColor", :viewBox "0 0 22 21"}
          [:path {:d "M16.975 11H10V4.025a1 1 0 0 0-1.066-.998 8.5 8.5 0 1 0 9.039 9.039.999.999 0 0 0-1-1.066h.002Z"}]
          [:path {:d "M12.5 0c-.157 0-.311.01-.565.027A1 1 0 0 0 11 1.02V10h8.975a1 1 0 0 0 1-.935c.013-.188.028-.374.028-.565A8.51 8.51 0 0 0 12.5 0Z"}]]
       [:span {:class "ms-3"} "Выйти"]]]]]])
