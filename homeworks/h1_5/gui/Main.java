import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.lang.Math;

/**
 * Draws gradients between 6 colors
 * @author  Saul Neri A01652526
 * @since   Thursday, 14 January 2020. 1579028533
 */
public class Main {
    static class Window extends JFrame {
        private Canvas canvas;

        private JPanel controlPanel;
        private JPanel controlPositionPanel;
        private JPanel controlForwardDrawingPanel;
        private JPanel controlForwardPanel;
        private JPanel controlRotatePanel;
        private JPanel controlInfoPanel;

        private JButton positionButton;
        private JButton forwardDrawingButton;
        private JButton forwardButton;
        private JButton rotateButton;

        private JTextField positionXField;
        private JTextField positionYField;
        private JTextField forwardDrawingField;
        private JTextField forwardField;
        private JTextField rotateField;

        private JLabel xPosLabel;
        private JLabel yPosLabel;
        private JLabel angLabel;
        private JLabel message;


        public Window() {
            setSize(1000, 500);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new GridLayout(1,2));
            initComponents();
            setVisible(true);
        }

        public void initComponents() {
            canvas = new Canvas();

            controlPanel = new JPanel();
            controlPanel.setLayout(new GridLayout(5,1));

            controlPositionPanel = new JPanel();
            controlPositionPanel.setLayout(new GridLayout(1,3));
            controlForwardDrawingPanel = new JPanel();
            controlForwardDrawingPanel.setLayout(new GridLayout(1,2));
            controlForwardPanel = new JPanel();
            controlForwardPanel.setLayout(new GridLayout(1,2));
            controlRotatePanel = new JPanel();
            controlRotatePanel.setLayout(new GridLayout(1,2));
            controlInfoPanel = new JPanel();
            controlInfoPanel.setLayout(new GridLayout(4, 1));

            positionButton = new JButton("Position (x, y)");
            positionXField = new JTextField();
            positionYField = new JTextField();
            controlPositionPanel.add(positionButton);
            controlPositionPanel.add(positionXField);
            controlPositionPanel.add(positionYField);
            positionButton.addActionListener(new positionButtonListener());

            forwardDrawingButton = new JButton("Draw forward (pixels)");
            forwardDrawingField = new JTextField();
            controlForwardDrawingPanel.add(forwardDrawingButton);
            controlForwardDrawingPanel.add(forwardDrawingField);
            forwardDrawingButton.addActionListener(new forwardDrawingButtonListener());

            forwardButton = new JButton("Move forward (pixels)");
            forwardField = new JTextField();
            controlForwardPanel.add(forwardButton);
            controlForwardPanel.add(forwardField);
            forwardButton.addActionListener(new forwardButtonListener());

            rotateButton = new JButton("Rotate (degrees)");
            rotateField = new JTextField();
            controlRotatePanel.add(rotateButton);
            controlRotatePanel.add(rotateField);
            rotateButton.addActionListener(new rotateButtonListener());


            xPosLabel = new JLabel("x: 0");
            yPosLabel = new JLabel("y: 0");
            angLabel = new JLabel("ang: 0");
            message = new JLabel("Use instructions to draw");
            controlInfoPanel.add(xPosLabel);
            controlInfoPanel.add(yPosLabel);
            controlInfoPanel.add(angLabel);
            controlInfoPanel.add(message);

            controlPanel.add(controlPositionPanel);
            controlPanel.add(controlForwardDrawingPanel);
            controlPanel.add(controlForwardPanel);
            controlPanel.add(controlRotatePanel);
            controlPanel.add(controlInfoPanel);

            add(controlPanel);
            add(canvas);
        }

        class positionButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                try {
                    double newX = Double.parseDouble(positionXField.getText());
                    double newY = Double.parseDouble(positionYField.getText());
                    canvas.position(newX, newY);
                    xPosLabel.setText("x: " + canvas.getX());
                    yPosLabel.setText("y: " + canvas.getY());
                    angLabel.setText("ang: " + canvas.getA());
                    message.setText("Changed position to (" + newX + ", " + newY + ")");
                } catch (NumberFormatException nfe) {
                    message.setText("Error: Empty field");
                }

                positionXField.setText("");
                positionYField.setText("");
                forwardDrawingField.setText("");
                forwardField.setText("");
                rotateField.setText("");
            }
        }

        class forwardDrawingButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                try {
                    double pixels = Double.parseDouble(forwardDrawingField.getText());
                    canvas.goForwardDrawing(pixels);
                    xPosLabel.setText("x: " + canvas.getX());
                    yPosLabel.setText("y: " + canvas.getY());
                    angLabel.setText("ang: " + canvas.getA());
                    message.setText("Moved forward drawing " + pixels + " pixels");
                } catch (NumberFormatException nfe) {
                    message.setText("Error: Empty field");
                }

                positionXField.setText("");
                positionYField.setText("");
                forwardDrawingField.setText("");
                forwardField.setText("");
                rotateField.setText("");
            }
        }

        class forwardButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                try {
                    double pixels = Double.parseDouble(forwardField.getText());
                    canvas.goForward(pixels);
                    xPosLabel.setText("x: " + canvas.getX());
                    yPosLabel.setText("y: " + canvas.getY());
                    angLabel.setText("ang: " + canvas.getA());
                    message.setText("Moved forward " + pixels + " pixels");
                } catch (NumberFormatException nfe) {
                    message.setText("Error: Empty field");
                }

                positionXField.setText("");
                positionYField.setText("");
                forwardDrawingField.setText("");
                forwardField.setText("");
                rotateField.setText("");
            }
        }

        class rotateButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                try {
                    double degrees = Double.parseDouble(rotateField.getText());
                    canvas.rotate(degrees);
                    xPosLabel.setText("x: " + canvas.getX());
                    yPosLabel.setText("y: " + canvas.getY());
                    angLabel.setText("ang: " + canvas.getA());
                    message.setText("Rotated " + degrees + "°");
                } catch (NumberFormatException nfe) {
                    message.setText("Error: Empty field");
                }

                positionXField.setText("");
                positionYField.setText("");
                forwardDrawingField.setText("");
                forwardField.setText("");
                rotateField.setText("");
            }
        }
    }

    static class Canvas extends JPanel { 
        
        // turtle object, draws vector based instructions
        private Turtle turtle = new Turtle();

        public void position(double x, double y) {
            turtle.position(x, y);
        }

        public void goForwardDrawing(double pixels) {
            turtle.goForwardDrawing(this.getGraphics(), pixels);
        }

        public void goForward(double pixels) {
            turtle.goForward(pixels);
        }

        public void rotate(double ang) {
            turtle.rotate(ang);
        }

        public int getX() {
            return turtle.getX();
        }
        public int getY() {
            return turtle.getY();
        }
        public int getA() {
            return turtle.getA();
        }

        /**
         * Main drawing method
         * @param g Grpahics object context
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        new Window();
    }
}