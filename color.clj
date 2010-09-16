(ns apl.color
    (:import (java.awt Color Dimension)
             (javax.swing JPanel JFrame Timer JOptionPane)
             (java.awt.event ActionListener MouseMotionListener MouseListener MouseAdapter MouseEvent)))

(def width 600)
(def height 200)
(def bar-size 10)

(defn printer [text]
  (println text)
  )

(defn bar-calculator [x]
  (printer x))

(defn draw-balk [g x color]
  (.setColor g color)
  (.fillRect g x 0 bar-size height))

(defn color-panel []
  (proxy [JPanel MouseListener MouseMotionListener ] []
    (paintComponent [g]
      (proxy-super paintComponent g)
      (.setColor g (Color. 0 0 0))
      (.fillRect g 0 0 width height)
      (draw-balk g 00 (Color. 250 150 150))
      (draw-balk g 10 (Color. 240 140 140))
      (draw-balk g 20 (Color. 230 130 130))
      (draw-balk g 30 (Color. 220 120 120))
      (draw-balk g 40 (Color. 210 110 110))
      (draw-balk g 50 (Color. 200 100 100))
      (draw-balk g 60 (Color. 190 90 90))
      (draw-balk g 70 (Color. 180 80 80))
      (draw-balk g 80 (Color. 170 70 70))
      (draw-balk g 90 (Color. 160 60 60))
      (draw-balk g 100 (Color. 150 50 50))
      (draw-balk g 110 (Color. 140 40 40))
      (draw-balk g 120 (Color. 130 30 30))
      (draw-balk g 130 (Color. 120 20 20))
      (draw-balk g 140 (Color. 110 10 10))
      (draw-balk g 150 (Color. 100 00 00))
      (draw-balk g 160 (Color. 90 00 00))
      (draw-balk g 170 (Color. 80 00 00))
      (draw-balk g 180 (Color. 70 00 00))
      (draw-balk g 190 (Color. 60 00 00))
      (draw-balk g 200 (Color. 50 00 00))
      (draw-balk g 210 (Color. 40 00 00))
      (draw-balk g 220 (Color. 30 00 00))
      (draw-balk g 230 (Color. 20 00 00))
      (draw-balk g 240 (Color. 10 00 00))
      (draw-balk g 250 (Color. 00 00 00))
        )
    (mouseMoved [e]
      (bar-calculator (.getX e)))
    (mouseClicked [e])
    (mouseEntered [e])
    (mouseExited [e])
    (mousePressed [e])
    (mouseReleased [e])
    )
  )

(defn color-bar []
  (let [frame  (JFrame. "Color")
        panel  (color-panel)]
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
    ))
  )