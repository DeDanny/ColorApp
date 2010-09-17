(ns apl.color
    (:import (java.awt Color Dimension)
             (javax.swing JPanel JFrame Timer JOptionPane)
             (java.awt.event ActionListener MouseMotionListener MouseListener MouseAdapter MouseEvent)))

(def width 600)
(def height 200)
(def bar-size 200)

(defn main-bar []
  {:location (- (/ width 2) (/ bar-size 2))
   :red 210
   :green 50
   :blue 90})

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

(defn draw-balk [g x color]
  (.setColor g color)
  (.fillRect g x 0 bar-size height))

(defn bar-calculator [g {x :location red :red green :green blue :blue}]
    (draw-balk g x (Color. red green blue))
  )

(defn color-panel [frame main-bar]
  (proxy [JPanel MouseListener MouseMotionListener ] []
    (paintComponent [g]
      (proxy-super paintComponent g)
      (bar-calculator g @main-bar)
      )
    (mouseMoved [e]
      (set-zero-bar main-bar (.getX e))
      (.repaint this))
    (mouseClicked [e])
    (mouseEntered [e])
    (mouseExited [e])
    (mousePressed [e])
    (mouseReleased [e])
    )
  )

(defn color-bar []
  (let [frame  (JFrame. "Color")
        main-bar (ref (main-bar))
        panel  (color-panel frame main-bar)
        ]
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