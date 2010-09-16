(ns apl.color
    (:import (java.awt Color Dimension)
             (javax.swing JPanel JFrame Timer JOptionPane)
             (java.awt.event ActionListener MouseMotionListener MouseListener MouseAdapter MouseEvent)
     ;        (javax.swing.event MouseInputAdapter)
      )
      )

(def width 200)
(def height 400)
(def bar-size 10)

(defn printer [text]
  (println text)
  )

(defn color-panel []
  (proxy [JPanel MouseListener MouseMotionListener ] []
    (mouseMoved [e]
      (printer (.getX e))
      )
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