(ns saga.core)

(defn- form-steps [& steps]
  (->> steps (split-at 3)))

(defmacro saga
  "Creates map with saga steps. Takes acknowledge function and pairs of forms
  for transaction and compensation"
  [acknowledge-fn & steps]
  `{:id (java.util.UUID/randomUUID)
    :acknowledge ~acknowledge-fn
    :steps (form-steps ~@steps)})

(defmacro execute
  "Executes previously created saga by it's steps and runs compensation if they
  are failed."
  [s])

(def s
  (saga
    #(println "Finished")
    :first #(println "start") nil
    :second  #(println "Second") #(println "Compensation")))
