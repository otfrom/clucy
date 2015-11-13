(ns clucy.test.analyzers-ru
  (:use clojure.test
        clucy.core
        clucy.analyzers
        clucy.positions-searcher))

(deftest analyzers

  (testing "make-analyzer fn"
    (is
     (= #{"алма" "ола" "ата" "йошкар"}
        (binding [*analyzer* (make-analyzer :class :standard)]
          (stemming-text "Алма-Ата Йошкар-Ола"))))
    (is
     (= #{"ол" "алм" "ат" "йошкар"}
        (binding [*analyzer* (make-analyzer :class :ru)]
          (stemming-text "Алма-Ата Йошкар-Ола"))))
    (is
     (= #{"ол" "алм" "ат" "йошкар"}
        (binding [*analyzer* (make-analyzer :tokenizer :standard
                                            :stemmer :ru)]
          (stemming-text "Алма-Ата Йошкар-Ола"))))
    (is
     (= #{"алма-ат" "йошкар-ол"}
        (binding [*analyzer* (make-analyzer :tokenizer :whitespace
                                            :stemmer :ru)]
          (stemming-text "Алма-Ата Йошкар-Ола"))))))
