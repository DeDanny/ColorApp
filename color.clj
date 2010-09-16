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

(defn draw-balk [g] )

(defn color-panel []
  (proxy [JPanel MouseListener MouseMotionListener ] []
    (paintComponent [g]
      (proxy-super paintComponent g)
      (paint g )
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