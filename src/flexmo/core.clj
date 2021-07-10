(ns flexmo.core
  (:require [clojure.data.json :as json])
  )

(defn read-words []
  (let [file-name (.getFile (clojure.java.io/resource "mots.json"))
        words (json/read-str(slurp file-name))]
    words))

(defn split-word [word]
  (let [min-length 2]
    (map (fn [index]
           (map #( apply str % ) (split-at index word)))
         (range min-length (- (count word) min-length 1)))))

(defn swappable-parts? [[first-part second-part] word-set]
  (boolean (word-set (str second-part first-part))))

(defn find-swappable-parts [words]
  (let [word-set (into #{} words)
        word-parts (mapcat split-word words)
        swappable (filter #(swappable-parts? % word-set) word-parts)
        ]
    (distinct(map sort swappable))
    ))

(defn -main
  ([] (-main 10))
  ([number] (let [words (read-words)
            swappable (find-swappable-parts words)
            ]
        (prn(count swappable))
        (prn(take number swappable))
        )))
