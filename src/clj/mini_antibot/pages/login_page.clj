(ns mini-antibot.pages.login-page)

(defn login-page []
     [:div {:class "flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0"}
        [:div {:class "w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700"}
         [:div {:class "p-6 space-y-4 md:space-y-6 sm:p-8"}
          [:h1 {:class "text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white"}
            "Войдите в свой акаут"]
          [:form {:class "space-y-4 md:space-y-6" :method "post" :action "/login" :enctype "multipart/form-data"}
            [:div
             [:label {:for "email" :class "block mb-2 text-sm font-medium text-gray-900 dark:text-white"} "Ваш email"]
             [:input {:type "email" :name "email" :id "email" :class "bg-gray-50 border border-gray-300 text-gray-900 rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              :placeholder "name@company.com" :required ""}]]
            [:div
             [:label {:for "password" :class "block mb-2 text-sm font-medium text-gray-900 dark:text-white"} "Пароль"]
             [:input {:type "password" :name "password" :id "password" :placeholder "••••••••" :class
            "bg-gray-50 border border-gray-300 text-gray-900 rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" :required ""}]]
            #_[:div {:class "flex items-center justify-between"}
             [:div {:class "flex items-start"}
              [:div {:class "flex items-center h-5"}
               #_[:input {:id "remember" :aria-describedby "remember" :type "checkbox" :class "w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-primary-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-primary-600 dark:ring-offset-gray-800"
                :required ""}]]
            #_[:div {:class "ml-3 text-sm"}
             #_[:label {:for "remember" :class "text-gray-500 dark:text-gray-300"} "Remember me"]]]
             #_[:a {:href "#" :class "text-sm font-medium text-primary-600 hover:underline dark:text-primary-500"} "Forgot password?"]]
            [:button {:type "submit" :class "w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800"} "Войти"]
            #_[:p {:class "text-sm font-light text-gray-500 dark:text-gray-400"} "Еще нет акаунат зарегистрируйтесь?"
             #_[:a {:href "#" :class "ml-1 font-medium text-primary-600 hover:underline dark:text-primary-500"} "Зарегистрироваться"]]]]]])
