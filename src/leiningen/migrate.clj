(ns leiningen.migrate
  (:require [leiningen.core.eval :refer [eval-in-project]]
            [leiningen.core.project :refer [merge-profiles]]))

(defn migrate
  "Migrate a namespace"
  [project & args]
  (let [project (merge-profiles project [{:dependencies '[[appcanary/crustacean "0.1.7-SNAPSHOT"]]}])]
    (eval-in-project project
                     `(doseq [entity# ~(mapv symbol args)]
                       (println "Migrating " (:name entity#))
                       (crustacean.migrations/new-migrations entity#)
                       (println "Done."))
                     '(require 'crustacean.migrations))))
