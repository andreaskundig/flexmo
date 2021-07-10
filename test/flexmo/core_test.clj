(ns flexmo.core-test
  (:require [clojure.test :refer :all]
            [flexmo.core :refer :all]))

(deftest test-swappable
  (testing "swappable"
    (is (swappable-parts? ["teau" "ga"] #{"gateau"})))
  (testing "not swappable"
    (is (not (swappable-parts? ["teau" "gax"] #{"gateau"}))))
  )
