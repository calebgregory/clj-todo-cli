(ns clj-todo-cli.core)

(defn -main
  [& args]
  (println "received args: " args))

(def counter (atom -1))
(def todos (atom {}))

(defrecord Todo [title completed])

(defn make-todo
  [title]
  (->Todo title false))

(defn add-todo
  [title]
  (let [todo (make-todo title)]
    (dosync
      (swap! counter inc)
      (swap! todos assoc @counter todo))))

(defn remove-todo
  [id]
  (swap! todos dissoc id))

(defn toggle-todo
  [id]
  (swap! todos update-in [id :completed] not))

(defn clear-all-todos
  []
  (dosync
   (reset! counter -1)
   (reset! todos {})))
