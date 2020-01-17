import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.lang.Math;

/**
 * Logo similar app
 * @author  Saul Neri A01652526
 * @since   Friday, 17 January 2020.
 */
public class Main {
    static class Window extends JFrame implements KeyListener {
        private Canvas canvas;

        public Window() {
            setSize(1000, 500);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new GridLayout(1,1));
            initComponents();
            addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            setVisible(true);
        }

        public void initComponents() {
            canvas = new Canvas();
            add(canvas);
        }

        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                this.canvas.translateShip(10, 0);
                canvas.repaint();
            }
            else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                this.canvas.translateShip(-10, 0);
                canvas.repaint();
            }
            else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                this.canvas.translateShip(0, 10);
                canvas.repaint();
            }
            else if(e.getKeyCode() == KeyEvent.VK_UP) {
                this.canvas.translateShip(0, -10);
                canvas.repaint();
            }
            else if(e.getKeyCode() == 'E' || e.getKeyCode() == 'e') {
                this.canvas.rotateShip(-0.06);
                canvas.repaint();
            }
            else if(e.getKeyCode() == 'D' || e.getKeyCode() == 'd') {
                this.canvas.rotateShip(0.06);
                canvas.repaint();
            }
            else if(e.getKeyCode() == 'R' || e.getKeyCode() == 'r') {
                this.canvas.scaleShip(1.06);
                canvas.repaint();
            }
            else if(e.getKeyCode() == 'F' || e.getKeyCode() == 'f') {
                this.canvas.scaleShip(0.94);
                canvas.repaint();
            }
        }
        public void keyReleased(KeyEvent e) {
            // System.out.println("keyReleased");
        }
        public void keyTyped(KeyEvent e) {
            // System.out.println("keyTyped");
        }
    }

    static class Canvas extends JPanel { 
        private Ship ship = new Ship();

        public void rotateShip(double ang) {
            this.ship.rotation(ang);
        }

        public void scaleShip(double factor) {
            this.ship.scaling(factor);
        }

        public void translateShip(double dx, double dy) {
            this.ship.translation(dx, dy);
        }

        /**
         * Main drawing method
         * @param g Grpahics object context
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillPolygon(this.ship.getXPoints(), this.ship.getYPoints(), this.ship.getXPoints().length);
            g.setColor(Color.RED);
            g.fillOval((int)this.ship.x, (int)this.ship.y, 10, 10);

            System.out.println(this.ship.getXPoints()[0] + " " + this.ship.x);
        }
    }

    public static void main(String[] args) {
        new Window();
    }
}