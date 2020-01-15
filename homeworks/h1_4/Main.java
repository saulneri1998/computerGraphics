import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.lang.Math;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/**
 * Plots pie and bar chart with data taken from file
 * @author  Saul Neri A01652526
 * @since   Thursday, 15 January 2020. 1579120231
 */
public class Main {
    static class Window extends JPanel {
        private int n;
        private ArrayList<Double> values;
        private ArrayList<String> labels;
        private ArrayList<Color> colors;

        public Window(int n, ArrayList<Double> values, ArrayList<String> labels, ArrayList<Color> colors) {
            super();
            this.n = n;
            this.values = values;
            this.labels = labels;
            this.colors = colors;
        }

        /**
         * Draws a pie chart
         * @param g Graphics context
         */
        private void pieChart(Graphics g) {
            // Sum of values
            double sum = 0;
            for (double v : this.values) {
                sum += v;
            }

            // Plot proportional arc
            double ang = 0;
            for (int i = 0; i < this.n; i++) {
                double arcAng = this.values.get(i) * 360 / sum;

                // Compute offset to make segments look separated
                int x = 50 + (int)(5 * Math.cos(Math.toRadians(ang + arcAng / 2)));
                int y = 80 - (int)(5 * Math.sin(Math.toRadians(ang + arcAng / 2)));

                g.setColor(this.colors.get(i));
                g.fillArc(x, y, 295, 295, (int)ang, (int)arcAng);
                ang += arcAng;
            }
        }

        /**
         * Draws a bar chart
         * @param g Graphics2D context
         */
        private void barChart(Graphics2D g) {
            g.setStroke(new BasicStroke(4));
            g.setFont(new Font("default", Font.BOLD, 16));

            // Max value
            double max = -1;
            for (double v : this.values) {
                if (v > max) {
                    max = v;
                }
            }
            g.setColor(Color.BLACK);
            g.drawLine(390, 100, 400, 100);
            g.drawString("" + max, 350, 100);
            
            // Plot bars
            int barWidth = 280 / this.n;
            for (int i = 0; i < n; i++) {
                int barHeight = (int)(this.values.get(i) * 280 / max);
                g.setColor(this.colors.get(i));
                g.fillRect(400 + i * barWidth, 380 - barHeight, barWidth, barHeight);
            }
            
            // Cartesian axes
            g.setColor(Color.BLACK);
            g.drawLine(400, 80, 400, 380);
            g.drawLine(400, 380, 700, 380);
            g.drawLine(680, 380, 680, 390);
        }

        private void drawLabels(Graphics2D g) {
            g.setStroke(new BasicStroke(2));
            
            int labelHeight = 350 / n;
            
            // Outline
            g.drawRect(750, 50, 200, 380);
            g.setFont(new Font("default", Font.BOLD, 20));
            g.drawString("Labels", 810, 75);

            g.setFont(new Font("default", Font.PLAIN, 16));
            for (int i = 0; i < n; i++) {
                int labelY = 80 + (int)(labelHeight * (i + 0.5) - 8);
                g.setColor(this.colors.get(i));
                g.fillRect(757, labelY, 16, 16);
                g.setColor(Color.BLACK);
                g.drawString(this.labels.get(i), 780, labelY + 16);
            }
        }

        /**
         * Main drawing method
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            // Background color
            g.setColor(new Color(242, 242, 242));
            g.fillRect(0, 0, 1000, 500);

            // Charts
            this.pieChart(g);
            this.barChart(g2);
            this.drawLabels(g2);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        ArrayList<String> labels = new ArrayList<>();
        ArrayList<Double> values = new ArrayList<>();
        ArrayList<Color> colors = new ArrayList<>();
        int n = Integer.parseInt(scanner.nextLine());

		for (int i = 0; i < n; i++) {
            values.add(Double.parseDouble(scanner.nextLine()));
            colors.add(new Color(rand.nextInt(220),rand.nextInt(220),rand.nextInt(220)));
        }
        for (int i = 0; i < n; i++) {
			labels.add(scanner.nextLine());
		}
        scanner.close();        

        Window panel = new Window(n, values, labels, colors);
		JFrame application = new JFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel);
		application.setSize(1000, 500);
        application.setVisible(true);
    }
}