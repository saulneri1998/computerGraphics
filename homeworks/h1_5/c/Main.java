import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.lang.Math;

/**
 * Instruction based vector drawing api
 * @author  Saul Neri A01652526
 * @since   Wednesday, 15 January 2020. 
 */
public class Main {
    static class Window extends JPanel { 
        
        // turtle object, draws vector based instructions
        private Turtle turtle = new Turtle();;

        /**
         * Main drawing method
         * @param g Grpahics object context
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            turtle.rotate(40);
            turtle.goForward(248);
            turtle.rotate(50);
            turtle.goForwardDrawing(g, 100);
            turtle.rotate(-90);
            turtle.goForwardDrawing(g, 160);
            turtle.rotate(-90);
            turtle.goForwardDrawing(g, 100);
            turtle.rotate(-45);
            turtle.goForwardDrawing(g, 113);
            turtle.rotate(-90);
            turtle.goForwardDrawing(g, 113);
            turtle.rotate(-45);
            turtle.rotate(-60);
            turtle.goForward(72);
            turtle.rotate(60);
            turtle.goForwardDrawing(g, 50);
            turtle.rotate(-90);
            turtle.goForwardDrawing(g, 36);
            turtle.rotate(-90);
            turtle.goForwardDrawing(g, 50);
            turtle.rotate(-90);
            turtle.goForwardDrawing(g, 36);

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