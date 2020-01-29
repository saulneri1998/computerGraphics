import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyListener;
import java.awt.event.FocusListener;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.lang.Math; 

/**
 * Wireframe
 * @author  Saul Neri A01652526
 * @since   Wednesday, 29 January 2020
 */
public class Wireframe extends JFrame implements KeyListener, FocusListener{
    Canvas canvas;
    int width, height;
    int azimuth = 35, elevation = 30;
    Point3D[] vertices;
    Edge[] edges;
    boolean focussed = false;

    public Wireframe(Point3D[] vertices, Edge[] edges) {
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,1));

        this.vertices = vertices;
        this.edges = edges;
        canvas = new Canvas();
        add(canvas);        
        
        
        addKeyListener(this);
        addFocusListener(this);
        // setFocusable(true);
        // setFocusTraversalKeysEnabled(false);
        setVisible(true);
    }

    class Canvas extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);  
   
            if (focussed) 
                g.setColor(Color.cyan);
            else
                g.setColor(Color.lightGray);
   
            int width = getSize().width;  // Width of the applet.
            int height = getSize().height; // Height of the applet.
            g.drawRect(0,0,width-1,height-1);
            g.drawRect(1,1,width-3,height-3);
            g.drawRect(2,2,width-5,height-5);
   
            if (!focussed) {
                g.setColor(Color.magenta);
                g.drawString("Click to activate",100,120);
                g.drawString("Use arrow keys to change azimuth and elevation",100,150);
            }
            else {
                double theta = Math.PI * azimuth / 180.0;
                double phi = Math.PI * elevation / 180.0;
                double cosT = (float)Math.cos( theta ), sinT = (float)Math.sin( theta );
                double cosP = (float)Math.cos( phi ), sinP = (float)Math.sin( phi );
                double cosTcosP = cosT*cosP, cosTsinP = cosT*sinP,
                        sinTcosP = sinT*cosP, sinTsinP = sinT*sinP;
    
                // project vertices onto the 2D viewport
                Point[] points;
                points = new Point[ vertices.length ];
                int j;
                int scaleFactor = width/8;
                double near = 3;  // distance from eye to near plane
                double nearToObj = 1.5f;  // distance from near plane to center of object
                for ( j = 0; j < vertices.length; ++j ) {
                    double x0 = vertices[j].x;
                    double y0 = vertices[j].y;
                    double z0 = vertices[j].z;

                    // compute an orthographic projection
                    double x1 = cosT*x0 + sinT*z0;
                    double y1 = -sinTsinP*x0 + cosP*y0 + cosTsinP*z0;
                    double z1 = cosTcosP*z0 - sinTcosP*x0 - sinP*y0;

                    // now adjust things to get a perspective projection
                    x1 = x1*near/(z1+near+nearToObj);
                    y1 = y1*near/(z1+near+nearToObj);

                    // the 0.5 is to round off when converting to int
                    points[j] = new Point(
                        (int)(width/2 + scaleFactor*x1 + 0.5),
                        (int)(height/2 - scaleFactor*y1 + 0.5)
                    );
                }
   
               // draw the wireframe
               g.setColor( Color.black );
               g.fillRect( 0, 0, width, height );
               g.setColor( Color.white );
               for ( j = 0; j < edges.length; ++j ) {
                    g.drawLine(
                        points[ edges[j].a ].x, points[ edges[j].a ].y,
                        points[ edges[j].b ].x, points[ edges[j].b ].y
                    );
               }
            } 
        }  // end paintComponent()    
    } // end nested class Canvas

    // ------------------- Event handling methods ----------------------
   
    public void focusGained(FocusEvent evt) {
        focussed = true;
        canvas.repaint();
    }
    
    public void focusLost(FocusEvent evt) {
        focussed = false;
        canvas.repaint(); 
    }
        
    public void keyTyped(KeyEvent evt) {  
    }  // end keyTyped()
        
    public void keyPressed(KeyEvent evt) { 
        
        int key = evt.getKeyCode();  // keyboard code for the key that was pressed
        
        if (key == KeyEvent.VK_LEFT) {
        azimuth += 5;
        canvas.repaint();         
        }
        else if (key == KeyEvent.VK_RIGHT) {
        azimuth -= 5;
        canvas.repaint();         
        }
        else if (key == KeyEvent.VK_UP) {
        elevation -= 5;
        canvas.repaint();         
        }
        else if (key == KeyEvent.VK_DOWN) {
        elevation += 5;         
        canvas.repaint();
        }

    }  // end keyPressed()

    public void keyReleased(KeyEvent evt) { 
        // empty method, required by the KeyListener Interface
    }
}