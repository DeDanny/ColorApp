(ns apl.color
    (:import (java.awt Color Dimension)
             (javax.swing JPanel JFrame Timer JOptionPane)
             (java.awt.event ActionListener MouseMotionListener MouseListener MouseAdapter MouseEvent))
    (:require (clojure.contrib.math)
              (clojure.contrib.generic.math-functions))
 )

(def width 1200)
(def height 300)
(def bar-size 5)

(defn main-bar []
  {:location (- (/ width 2) (/ bar-size 2))
   :red 15
   :green 2
   :blue 1})

(defn printer [& text]
  (println text)
  )

(defn x-update [main-bar x]
    (assoc main-bar :location (- x (/ bar-size 2)))
  )

(defn set-zero-bar [main-bar x]
  (dosync
    (alter main-bar x-update x)
       ))

(defn draw-bar [g x color]
  (.setColor g color)
  (.fillRect g x 0 bar-size height))

(defn draw-bars [g bars]
  (loop [barss bars extra 0]
    ;(printer barss ":" extra)
    (if-not (empty? barss)
      (let [[x red green blue] (first barss)]
        (draw-bar g x (Color. (int red) (int green) (int blue)))
          (recur (rest barss) (inc extra))) "EIND")))

(defn calculate-color [bar color]
  (let [color-number (- 255 (* bar color))]
    (if (< color-number 0)
      0
      color-number)))

(defn calculate-right [bars x red green blue]
      (if (zero? bars)
        '()
        (cons (list (+ x (* bars bar-size)) (calculate-color bars red) (calculate-color bars green) (calculate-color bars blue)) (calculate-right (dec bars) x red green blue))
        ))

(defn calculate-left [bars x red green blue]
      (if (zero? bars)
        '()
        (cons (list (- x (* bars bar-size)) (calculate-color bars red) (calculate-color bars green) (calculate-color bars blue)) (calculate-left (dec bars) x red green blue))
        ))

(defn bar-draw [g {x :location red :red green :green blue :blue}]
    (draw-bars g (calculate-right(Math/ceil (/ (- width (Math/floor (+ x bar-size))) bar-size )) x red green blue))
    (draw-bars g (calculate-left(Math/ceil (/ x bar-size)) x red green blue))
    (draw-bar g x (Color. 255 255 255))
  )

(defn color-panel [frame main-bar]
  (proxy [JPanel MouseListener MouseMotionListener ] []
    (paintComponent [g]
      (proxy-super paintComponent g)
      (bar-draw g @main-bar))
    (mouseMoved [e]
      (set-zero-bar main-bar (.getX e))
      (.repaint this))
    (mouseClicked [e])
    (mouseEntered [e])
    (mouseExited [e])
    (mousePressed [e])
    (mouseReleased [e])
    ))

(defn color-bar []
  (let [frame  (JFrame. "Color")
        main-bar (ref (main-bar))
        panel  (color-panel frame main-bar)]
    (doto panel
      (.setFocusable true)
      (.addMouseListener panel)
      (.addMouseMotionListener panel))
    (doto frame
      (.add panel)
      (.pack)
      (.setResizable false)
      (.setVisible true)
      (.setSize width height)
      (.setLocationRelativeTo nil)
    )[main-bar])
  )


    ;(printer (Math/ceil (/ (- width (Math/floor (+ x bar-size))) bar-size )))
    ;(calculate-right(Math/ceil (/ (- width (Math/floor (+ x bar-size))) bar-size )) x red green blue)
    ;(bar-loop (Math/ceil (/ width bar-size)) x #(+ bar-size %) red green blue)

;(defn bar-calculator
;  "Calculator works from the outside in. so f must me inverted"
;  [f m]
;  (fn
;    [bars x red green blue]
;      (if (zero? bars)
;        '()
;        (cons (f (* bars x) bar-size) (- 255 (* bars red)) (- 255 (* bars green)) (- 255 (* bars blue)) (m (dec bars) x red green blue))
;        )))
