(ns treasure)

(def i 0)
(def j 0)

(def vect [])
(def indv [])
(def con 0)
(def r 0)

(defn printmatrix
      "Prints the final map when treasure is either found or not"
      [mapvector a width height]
      (doseq [x (range height)]
             (doseq [y (range width) ]

                    (print (char (aget a x y)))
                    )
             (println )
             )
      )


(defn findtreasure
      "Actual function that implements the main game play logic"
      [mapvector a length height]

      (if (or (or (or ( and (not= i (- (count mapvector) 1)) (= \@ (aget a (inc i) j) ) ) ( and (not= i 0) (= \@ (aget a (dec i) j) ) )) ( and (not= j (- length 1))  (= \@ (aget a i (inc j)) ) )) ( and (not= j 0) (= \@ (aget a i (dec j)) ))
              )
        (do
          (aset-char a i j \+)
          (println )
          (println "Woo hoo, I found the treasure :-)")
          (println )
          (println )
          (printmatrix mapvector a length height)
          (def con (inc con))
          )
        )

      (if ( and ( and (= i 0 ) (= j 0)) ( = \@ (aget a i j) ))

        (do
          (println "\nWoo hoo, I found the treasure :-)")
          (println )
          (println )
          (printmatrix mapvector a length height)
          (def con (inc con))
          )
        )

      (if ( and ( and (= i 0 ) (= j 0)) ( = \# (aget a i j) ))

        (do
          (println "\nUh Oh, I could not find the treasure :-(")
          (println )
          (println )
          (printmatrix mapvector a length height)
          (def con (inc con))
          )
        )

      (if (and (and ( and (not= i (- (count mapvector) 1)) (= \- (aget a (inc i) j) )) (not= con 1) ))

        (do
          (aset-char a i j \+)
          (def i (inc i))
          (findtreasure mapvector a length height)
          (aset-char a i j \!)
          (def i (dec i))
          )
        )

      (if (and (and (not= i 0) (= \- (aget a (dec i) j) )) (not= con 1) )

        (do
          (aset-char a i j \+)
          (def i (dec i))
          (findtreasure mapvector a length height)
          (aset-char a i j \!)
          (def i (inc i))
          )
        )

      (if (and (and (not= j (- length 1))  (= \- (aget a i (inc j)) )) (not= con 1) )

        (do
          (aset-char a i j \+)
          (def j (inc j))
          (findtreasure mapvector a length height)
          (aset-char a i j \!)
          (def j (dec j))
          )
        )

      (if (and (and (not= j 0) (= \- (aget a i (dec j)) )) (not= con 1))

        (do
          (aset-char a i j \+)
          (def j (dec j))
          (findtreasure mapvector a length height)
          (aset-char a i j \!)
          (def j (inc j))
          )
        )

      (print " ")
      )

(defn importmap
      "Imports the map"
      []

      (def mapstr (slurp "map.txt"))
      (if (clojure.string/blank? mapstr)

        (do
          (println "Hoo Hoo! It's an empty Map....Try with a different map")
          (def r (inc r))
          )
        (do
          )
        )

      (def mapvector (clojure.string/split-lines mapstr ))
      (def varcountbef (count (get mapvector 0)))
      (def varcountaft 0)
      (def c 0)
      (def x 0)
      (doseq [n mapvector]

             (def varcountaft (count (get mapvector c)))
             (def c (inc c))
             (if (= varcountaft varcountbef)
               (do
                 (def varcountbef varcountaft)
                 )
               (do
                 (if (= x 0)
                   (do
                     (println "Hoo Hoo! It's a Invalid Map....Try with a different map")
                     )
                   )

                 (def x (inc x))
                 (def r (inc r))
                 )
               )
             )

      (if (= r 0)

        (do
          (def width (count(get mapvector 0)))
          (def height (count mapvector))
          (def a (make-array Character/TYPE height width))
          (println )
          (println )
          (println "This is my Challenge: ")
          (println )
          (doseq [x (range height)]
                 (doseq [y (range width) ]
                        (aset-char a x y (nth (get mapvector x) y))
                        (print (aget a x y))
                        )
                 (println )
                 )
          (println )
          (println )
          (findtreasure mapvector a width height)
          (println )
          (if (= con 0)

            (do
              (println "Uh Oh, I could not find the treasure :-(")
              (println )
              (aset-char a 0 0 \!)
              (println )
              (printmatrix mapvector a width height)
              )
            )
          )
        )
      )

(defn newgame
      "Initial point of executioon"
      []

      (importmap)
      )

(newgame)

