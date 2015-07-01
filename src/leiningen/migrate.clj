(ns leiningen.migrate
  (:require [leiningen.core.eval :refer [eval-in-project]]
            [leiningen.core.project :refer [merge-profiles]]))

(defn migrate
  "Migrate a namespace"
  [project & args]
  (let [project (merge-profiles project [{:dependencies '[[appcanary/crustacean "0.1.0-SNAPSHOT"]]}])]
    (eval-in-project project
                     `(doseq [entity# ~(mapv symbol args)]
                       (println "Migrating " (:name entity#))
                       (crustacean.migrations/write-migrations entity#)
                       (println "Writing" (:migration-file entity#))
                       (println "Done."))
                     '(require 'crustacean.migrations))))
