(ns ceshire-cat.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [clojure.browser.repl :as repl]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [enfocus.core :as ef]
            [enfocus.events :as ev]))

(enable-console-print!)

(defn ^:export init []
  (repl/connect "http://localhost:9000/repl")
  (go
   (let [response (<! (http/get "/ceshire-cat")) body (:body response)]
     (ef/at "#cat-name" (ef/content (:name body))
            "#status" (ef/do->
                       (ef/content (:status body))
                       (ef/set-style :font-size "500%")))
     (ef/at "#button1" (ev/listen :click #(js/alert "Goodbye!"))))))

