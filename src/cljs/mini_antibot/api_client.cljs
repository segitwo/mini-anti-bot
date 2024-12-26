(ns mini-antibot.api-client
  (:require-macros [cljs.core.async :refer [go]])
  (:require
   [cljs-http.client :as http]
   [cljs.core.async :refer [<!]]
   [cljs.spec.alpha :as s]
   [reagent.core :as r]
   [reagent.cookies :as cookies]))

(def ipv4-regex #"^((25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])$")

(def domain-regex #"^(?=.{1,253}$)((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$")

(s/def ::ip (s/and string? #(re-matches ipv4-regex %)))
(s/def ::domain #(re-matches domain-regex %))

(defn valid-ip? [ip]
  (s/valid? ::ip ip))

(defn valid-domain? [domain]
  (s/valid? ::domain domain))

(comment
  (valid-ip? "172.0.0.1"))

(def api-client-error (r/atom {}))

(defn add-ip [ip]
  (if (valid-ip? @ip)
    (go (let [token (cookies/get "token")
              response (<! (http/post "/api/add-ip"
                                      {:headers {"Authorization" (str "Token " token)}
                                       :json-params {:ip @ip}}))]

          (print token)
          (prn (:status response))))
    (reset! api-client-error {:ip-error "Некорректный IP адрес"})))

(def errors-text {:ip "Некорректный IP адрес"
                  :domain "Некорректное доменое имя"})

(defn validate-domain-ip! [data]
  (let [result (s/conform (s/keys :req-un [::ip ::domain]) data)]
    (if (s/invalid? result)
      (let [errors (s/explain-data (s/keys :req-un [::ip ::domain]) data)
            problems (:cljs.spec.alpha/problems errors)]
        (doseq [problem problems]
          (let [key (get (:path problem) 0)]
            (swap! api-client-error conj {key (get errors-text key)})))
        false)
      true)))


(comment
  (prn @api-client-error)
  (reset! api-client-error {})
  (validate-domain-ip! {:ip "127.0.0.1" :domain ""}))

(defn add-domain [data])

