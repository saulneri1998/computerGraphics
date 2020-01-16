import java.awt.Graphics;
import java.awt.Color;

/**
 * Model of drawing turtle
 * @author  Saul Neri A01652526
 * @since   Wednesday, 15 January 2020. 
 */
public class Turtle {
    private int x;
    private int y;
    private int a;

    /**
     * Empty constructor
     */
    public Turtle() {
        this.x = 0;
        this.y = 0;
        this.a = 0;
    }

    /**
     * Constructor with attributes
     * @param x Horizontal position component
     * @param y Vertical position component
     * @param a Rotation angle
     */
    public Turtle(int x, int y, int a) {
        this.x = x;
        this.y = y;
        this.a = a;
    }

    /**
     * Sets turtle's position
     * @param x Horizontal coordinate
     * @param y Vertical coordinate
     */
    public void position(double x, double y) {
        this.x = (int)(x);
        this.y = (int)(y);
    }
    
    /**
     * Moves turtle in direction to it's current angle and drwas a line
     * @param pixels Number of pixels to move turle
     * @param g Graphics object context
     */
    public void goForwardDrawing(Graphics g, double pixels) {
        double deltaX = pixels * Math.cos(Math.toRadians(this.a));
        double deltaY = pixels * Math.sin(Math.toRadians(this.a));

        g.drawLine(this.x, this.y, (int)(this.x + deltaX), (int)(this.y + deltaY));

        this.x += deltaX;
        this.y += deltaY;
    }
    /**
     * Moves turtle in direction to it's current angle
     * @param pixels Number of pixels to move turle
     */
    public void goForward(double pixels) {
        double deltaX = pixels * Math.cos(Math.toRadians(this.a));
        double deltaY = pixels * Math.sin(Math.toRadians(this.a));

        this.x += deltaX;
        this.y += deltaY;
    }
    /**
     * Rotates turtle's orientation clockwise
     * @param angle Angle to rotate turle, in degrees
     */
    public void rotate(double ang) {
        this.a = (int)((this.a + ang) % 360);
    }

    /**
     * Getter for horizontal position component
     * @return x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter for vertical position component
     * @return y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Getter for rotation angle
     * @return a
     */
    public int getA() {
        return this.a;
    }

    /**
     * Compose string with object's information
     * @return Composed attributes in string form
     */
    public String toString() {
        return "Turtle: (" + this.x + ", " + this.y + ") - " + this.a + "°"; 
    }
}