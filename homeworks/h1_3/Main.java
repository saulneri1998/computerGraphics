import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.lang.Math;

/**
 * Draws gradients between 6 colors
 * @author  Saul Neri A01652526
 * @since   Thursday, 14 January 2020. 1579061554
 */
public class Main {
    static class Window extends JPanel {
        /**
         * Computes a step in color gradient
         * @param source starting Color object
         * @param target destination Color object
         * @param steps number of steps to complete the transformation
         * @param current step to compute color for
         * @return Color object representing the desired step in color gradient
         */
        private Color stepColor(Color source, Color target, int steps, int current) {
            double deltaR = target.getRed() - source.getRed();
            double deltaG = target.getGreen() - source.getGreen();
            double deltaB = target.getBlue() - source.getBlue();

            int r = source.getRed() + (int)(deltaR / steps * current);
            int g = source.getGreen() + (int)(deltaG / steps * current);
            int b = source.getBlue() +  (int)(deltaB / steps * current);

            return(new Color(r, g, b));
        }

        /**
         * Draws sky with gradient
         * @param g Graphics context
         */
        private void paintSky(Graphics g) {
            Color orange = new Color(252, 156, 84);
            Color purple = new Color(75, 61, 96);
            for (int j = 250; j >= 0; j--) {
                g.setColor(this.stepColor(orange, purple, 250, 250-j));
                g.drawLine(0, j, 1000, j);
            }
        }

        /**
         * Draws sun with gradient
         * @param g
         */
        private void paintSun(Graphics g) {
            Color yellow = new Color(255, 240, 0);
            Color orange = new Color(252, 156, 84);
            for (int i = 190; i >= 1; i--) {
                g.setColor(this.stepColor(yellow, orange, 190, i));
                g.fillOval(500-(i/2), 230-(i/2), i, i);
            }
        }

        /**
         * Draws road and car
         * @param g
         */
        private void paintCar(Graphics g) {
            // Green background
            for (int i = 250; i <= 600; i++) {
                g.setColor(this.stepColor(new Color(28, 77, 41), new Color(13, 40, 20),  350, i));
                g.drawLine(0, i, 1000, i);
            }

            // Gray road
            int[] roadX = {420, 580, 700, 300};
            int[] roadY = {250, 250, 600, 600};
            g.setColor(new Color(80, 80, 80));
            g.fillPolygon(roadX, roadY, roadX.length);

            // yellow lines
            g.setColor(new Color(200, 170, 0));
            g.fillRect(495, 570, 10, 30);
            g.fillRect(495, 520, 10, 30);
            g.fillRect(496, 470, 8, 30);
            g.fillRect(496, 420, 8, 30);
            g.fillRect(497, 370, 6, 30);
            g.fillRect(497, 320, 6, 30);
            g.fillRect(498, 270, 4, 30);
            g.fillRect(498, 250, 4, 3);

            // Car tires
            g.setColor(new Color(0, 0, 0));
            g.fillRect(410, 490, 15, 35);
            g.fillRect(490, 490, 15, 35);

            // Car body
            int[] carX = {394, 393, 392, 391, 390, 390, 389, 389, 388, 388, 388, 388, 388, 388, 388, 388, 388, 389, 390, 390, 391, 392, 393, 394, 396, 397, 399, 400, 402, 401, 401, 402, 403, 404, 404, 405, 405, 407, 408, 408, 409, 410, 412, 414, 416, 419, 422, 425, 429, 433, 438, 441, 445, 449, 452, 456, 459, 462, 465, 468, 471, 474, 476, 479, 482, 484, 487, 489, 491, 493, 495, 497, 498, 500, 501, 502, 503, 504, 504, 505, 506, 507, 508, 508, 509, 510, 511, 510, 509, 511, 513, 514, 515, 517, 518, 518, 519, 520, 521, 521, 522, 522, 523, 523, 523, 523, 523, 523, 522, 522, 521, 521, 520, 519, 518, 516, 514, 511, 508, 505, 502, 499, 496, 493, 489, 486, 481, 477, 474, 470, 466, 463, 459, 455, 450, 447, 441, 437, 432, 429, 424, 420, 416, 412, 407, 403, 400, 397};
            int[] carY = {510, 509, 507, 506, 505, 503, 502, 500, 499, 497, 496, 494, 493, 491, 490, 488, 487, 485, 484, 482, 481, 479, 477, 476, 475, 473, 472, 471, 471, 470, 468, 467, 468, 466, 464, 461, 458, 453, 449, 445, 443, 439, 437, 434, 432, 429, 426, 424, 422, 421, 419, 419, 418, 417, 417, 417, 417, 417, 418, 418, 419, 419, 420, 421, 422, 424, 425, 426, 428, 430, 432, 433, 435, 438, 440, 442, 445, 448, 451, 454, 457, 459, 462, 464, 468, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 481, 482, 484, 485, 487, 489, 490, 492, 494, 496, 498, 500, 501, 503, 504, 506, 508, 509, 510, 511, 511, 511, 512, 512, 513, 513, 513, 514, 514, 514, 514, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 514, 514, 514, 513, 513, 513, 512, 511, 511, 510};
            g.setColor(new Color(40, 40, 40));
            g.fillPolygon(carX, carY, carX.length);

            // Car window
            int[] windowX = {414, 414, 415, 415, 417, 418, 420, 421, 423, 425, 427, 431, 434, 438, 441, 445, 448, 451, 454, 459, 463, 466, 469, 472, 475, 478, 480, 482, 485, 487, 489, 491, 493, 495, 496, 497, 498, 498, 499, 499, 498, 496, 494, 491, 488, 485, 482, 478, 474, 470, 466, 463, 460, 456, 452, 449, 445, 443, 440, 436, 433, 430, 427, 423, 421, 418, 416, 415};
            int[] windowY = {455, 453, 451, 448, 445, 443, 440, 437, 434, 432, 431, 428, 427, 426, 425, 425, 424, 424, 424, 424, 424, 424, 425, 425, 426, 427, 427, 429, 431, 432, 434, 437, 439, 442, 444, 447, 449, 451, 453, 456, 457, 459, 460, 460, 459, 459, 458, 458, 457, 456, 456, 456, 456, 456, 456, 456, 456, 457, 457, 457, 458, 459, 459, 460, 460, 460, 459, 458};
            g.setColor(new Color(180, 180, 180));
            g.fillPolygon(windowX, windowY, windowX.length);

            // License plate
            g.setColor(new Color(170, 170, 0));
            g.fillRect(432, 483, 50, 15);

            // Car lights
            g.setColor(new Color(170, 0, 0));
            g.fillOval(405, 483, 15, 15);
            g.fillOval(494, 483, 15, 15);

        }

        /**
         * Draws house with gradients
         * @param g
         */
        private void paintHouse(Graphics g) {
            // Main structure
            g.setColor(new Color(190, 177, 170));
            g.fillRect(800, 200, 150, 100);
            g.setColor(new Color(170, 157, 150));
            g.fillRect(730, 230, 70, 60);
            g.setColor(new Color(150, 137, 130));
            g.fillPolygon(new int[] {800, 950, 970, 820}, new int[] {200, 200, 180, 180}, 4);
            g.setColor(new Color(130, 117, 110));
            g.fillPolygon(new int[] {950, 950, 970, 970}, new int[] {200, 300, 280, 180}, 4);
            g.fillPolygon(new int[] {730, 800, 800, 750}, new int[] {230, 230, 215, 215}, 4);

            // Window
            Color windowBlue = new Color(183, 207, 214);
            Color orange = new Color(252, 156, 84);
            for (int i = 1; i <= 80; i++) {
                g.setColor(this.stepColor(windowBlue, orange, 80, i));
                g.drawLine(860+i, 210, 860+i, 250);
            }
            g.setColor(windowBlue);
            g.fillRect(810, 210, 51, 40);
            g.setColor(new Color(170, 157, 150));
            g.drawRect(810, 210, 130, 40);

            // Doors
            g.setColor(new Color(134, 114, 91));
            g.fillRect(850, 270, 50, 30);
            g.fillRect(737, 250, 56, 40);

            // Pool
            g.setColor(new Color(170, 157, 150));
            g.fillPolygon(new int[] {810, 936, 950, 824}, new int[] {377, 377, 323, 323}, 4);
            g.setColor(windowBlue);
            g.fillPolygon(new int[] {820, 930, 940, 830}, new int[] {370, 370, 330, 330}, 4);
        }

        /**
         * Recursive function to draw a fractal tree
         * @param g Grpahics context
         * @param x1 Starting horizontal coordinate for line
         * @param y1 Starting vertical coordinate for line
         * @param angle Starting rotation angle in radians
         * @param depth Current level of recursion
         * @param colorGreen Green hue of the branch
         * @param size Scalar size factor 
         */
        private void paintTree(Graphics2D g, int x1, int y1, double angle, int depth, int colorGreen, double size) {
            if (depth > 0) {
                g.setStroke(new BasicStroke(depth));
                g.setColor(new Color(95, colorGreen, 61));
                int x2 = x1 + (int) (Math.cos(angle) * size * depth);
                int y2 = y1 + (int) (Math.sin(angle) * size * depth);
                g.drawLine(x1, y1, x2, y2);
                paintTree(g, x2, y2, angle - 0.4, depth - 1, colorGreen+13, size);
                paintTree(g, x2, y2, angle + 0.4, depth - 1, colorGreen+13, size);
            }            
        }

        private void paintDog(Graphics g) {
            int[] dogX = {496, 495, 493, 491, 487, 488, 490, 491, 491, 491, 485, 482, 478, 475, 474, 478, 479, 477, 482, 482, 487, 493, 501, 503, 515, 515, 517, 521, 524, 526, 524, 527, 525, 524, 527, 527, 527, 524, 522, 523, 525, 522, 520, 517, 515, 516, 519, 519, 517, 514, 513, 508, 501, 498};
            int[] dogY = {237, 241, 250, 254, 253, 251, 250, 246, 242, 237, 229, 223, 224, 223, 220, 217, 213, 208, 211, 208, 212, 219, 221, 221, 222, 218, 216, 214, 216, 221, 226, 232, 239, 245, 248, 253, 256, 257, 256, 254, 253, 248, 253, 253, 252, 250, 250, 247, 243, 239, 234, 234, 237, 237};
            g.setColor(new Color(40, 40, 40));
            g.fillPolygon(dogX, dogY, dogX.length);
        }

        // /**
        //  * Main drawing method
        //  */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            this.paintSky(g);
            this.paintSun(g);
            this.paintCar(g);
            this.paintHouse(g);
            this.paintTree(g2, 200, 360, -Math.PI/2, 12, 30, 3.9);
            this.paintDog(g);
        }
    }

    public static void main(String[] args) {
        Window panel = new Window();
		JFrame application = new JFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel);
		application.setSize(1000, 600);
		application.setVisible(true);
    }
}