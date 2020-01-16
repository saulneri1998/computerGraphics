import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.lang.Math;

/**
 * Draws gradients between 6 colors
 * @author  Saul Neri A01652526
 * @since   Thursday, 14 January 2020. 1579028533
 */
public class Main {
    static class Window extends JPanel { 
        
        private Turtle turtle = new Turtle(50, 50, 0);;

        /**
         * Main drawing method
         * @param g Grpahics object context
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            turtle.position(200, 300);
            System.out.println(turtle.toString());
            turtle.goForwardDrawing(g,100);
            System.out.println(turtle.toString());
            turtle.rotate(90);
            System.out.println(turtle.toString());
            turtle.goForwardDrawing(g,100);
            System.out.println(turtle.toString());
            turtle.rotate(90);
            System.out.println(turtle.toString());
            turtle.goForwardDrawing(g,100);
            System.out.println(turtle.toString());
            turtle.rotate(90);
            System.out.println(turtle.toString());
            turtle.goForwardDrawing(g,100);
            System.out.println(turtle.toString());
            turtle.rotate(90);
            System.out.println(turtle.toString());

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