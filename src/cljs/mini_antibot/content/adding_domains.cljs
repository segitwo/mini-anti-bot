(ns mini-antibot.content.adding-domains
  (:require
   [reagent.core :as r]
   [mini-antibot.api-client :refer [api-client-error add-domain]]))

(def data (r/atom {:ip "" :domain ""}))

(defn ip-input [value]
  [:div
   [:div {:class "relative"}
    [:input {:class "block px-2.5 pb-2.5 pt-4 w-full text-sm text-gray-900 bg-white rounded-lg border-2 border-gray-100 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
             :id "ip-address"
             :type "text"
             :placeholder ""
             :value (:ip @value)
             :on-change #(swap! value assoc :ip (-> % .-target .-value))
             :on-click #(reset! api-client-error {})}]
    [:label {:for "ip-address" :class "absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white dark:bg-gray-900 px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 rtl:peer-focus:translate-x-1/4 rtl:peer-focus:left-auto start-1"} "127.0.0.1"]]
   [:p {:id "outlined_error_help" :class "mx-2 mt-1 text-xs text-red-600 dark:text-red-400"} (when (not-empty @api-client-error) (:ip-error @api-client-error))]])

(defn domain-input [value]
  [:div
   [:div {:class "relative"}
    [:input {:class "block px-2.5 pb-2.5 pt-4 w-full text-sm text-gray-900 bg-white rounded-lg border-2 border-gray-100 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
             :id "domain"
             :type "text"
             :placeholder ""
             :value (:domain @value)
             :on-change #(swap! value assoc :domain (-> % .-target .-value))
             :on-click #(reset! api-client-error {})}]
    [:label {:for "domain" :class "absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white dark:bg-gray-900 px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 rtl:peer-focus:translate-x-1/4 rtl:peer-focus:left-auto start-1"} "acme.com"]]
   [:p {:id "outlined_error_help" :class "mx-2 mt-1 text-xs text-red-600 dark:text-red-400"} (when (not-empty @api-client-error) (:domain-error @api-client-error))]])

(defn adding-domains []
  [:<>
   [:h3 {:class "mb-1 font-medium"} "Домейн - IP"]
   [:div {:class "flex h-24 mb-4 rounded bg-gray-50 dark:bg-gray-800"}
    [:div
     [:div {:class "flex h-20 mb-4 rounded bg-gray-50 dark:bg-gray-800 items-top mx-5 gap-5 flex flex-row mt-4"}
      [:div {:class "grid gap-6 mb-6 md:grid-cols-3 sm:grid-cols-3 xs:grid-cols-12"}
       [domain-input data]
       [ip-input data]
       [:div
        [:button {:type "button" :class "mt-1 w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800"
                  :on-click #(add-domain data)} "Добавить"]]]]]]])
