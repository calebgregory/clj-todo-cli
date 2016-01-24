(ns clj-todo-cli.core-test
  (:require [clojure.test :refer :all]
            [clj-todo-cli.core :refer :all]))

(defn clean-todos
  [f]
  (swap! todos {})
  (f))

(use-fixtures :each clean-todos)

(deftest adds-a-todo
  (testing "Adding a todo to the list"
    (is (= 0 (count @todos)))
    (add-todo "Make todo")
    (is (= 1 (count @todos)))))

(deftest removes-a-todo
  (testing "Removing a todo"
    (add-todo "Make todo")
    (is (= 1 (count @todos)))
    (let [k (first (keys @todos))]
      (remove-todo k))
    (is (= 0 (count @todos)))))

(deftest clears-all-todos
  (testing "Clearing all the todos"
    (add-todo "Make a todo")
    (add-todo "Make another")
    (is (= 2 (count @todos)))
    (clear-all-todos)
    (is (= 0 (count @todos)))
    (is (= -1 @counter))))
