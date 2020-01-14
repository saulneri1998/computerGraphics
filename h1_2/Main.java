import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.lang.Math;;

/**
 * Draws gradients between 6 colors
 * @author  Saul Neri A01652526
 * @since   Thursday, 9 January 2020.
 */
public class Main {
    static class Window extends JPanel {
        /**
         * Computes RGB value based on x, y coordinates to fulfill gradient requirement
         * @param x Horizontal coordinate
         * @param y Vertical Coordinate
         * @return Color object with computed RGB values
         */
        private Color colorForPoint(int x, int y) {
            int r, g, b;
            if (x >= 333) {
                r = 255;
            } else {
                r = x * 255 / 333;
            }

            if (x <= 666) {
                b = 255;
            } else {
                b = 255 - ((x - 666) * 255 / 333);
            }

            double distCyan = Math.sqrt(x*x + y*y);
            double distWhite = Math.sqrt((x-500)*(x-500) + (y-500)*(y-500));
            double distYellow = Math.sqrt((x-1000)*(x-1000) + y*y);
            if (distCyan <= distWhite && distCyan <= distYellow) {
                g = 255 - (int)(distCyan * 255 / 500);
            } else if (distWhite <= distCyan && distWhite <= distYellow) {
                g = 255 - (int)(distWhite * 255 / 500);
            } else {
                g = 255 - (int)(distYellow * 255 / 500);
            }

            return (new Color(r, g, b));
        }

        /**
         * Main drawing method
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 500; j++) {
                    Color c = this.colorForPoint(i, j);
                    g.setColor(c);
                    g.fillRect(i, j, 1, 1);	
                }
            }

        }
    }

    public static void main(String[] args) {
        Window panel = new Window();
		JFrame application = new JFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel);
		application.setSize(1000, 500);
		application.setVisible(true);
    }
}