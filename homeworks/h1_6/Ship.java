import java.util.ArrayList;
import java.lang.Math;

/**
 * Ship
 */
public class Ship {

    public double x;
    public double y;
    private double a;
    private double s;
    private double[] xPoints = {-147, -95, -90, -85, -82, -76, -57, -57, -43, -41, -38, -34, -31, -29, -27, -24, -22, -20, -18, -17, -9, -9, -2, 1, 4, 6, 9, 12, 13, 15, 17, 21, 26, 25, 37, 40, 48, 52, 56, 64, 62, 66, 140, 157, 157, 134, 130, 62, 53, 41, 40, 29, 28, 26, 24, 22, 20, 18, 17, 15, 12, 10, 7, 4, 1, -2, -6, -9, -12, -16, -20, -24, -28, -32, -35, -38, -40, -42, -53, -80, -79, -79, -89, -91, -97, -145, -154, -156, -156, -154, -148, -151};
    private double[] yPoints = {-162, -153, -106, -57, -20, -22, -27, -30, -34, -38, -42, -43, -47, -48, -49, -50, -51, -52, -52, -60, -60, -54, -54, -53, -53, -53, -51, -51, -49, -48, -46, -46, -41, -38, -34, -33, -32, -55, -93, -151, -159, -166, -157, -1, 6, 143, 143, 102, 2, 5, 7, 10, 12, 14, 16, 18, 20, 22, 24, 26, 27, 29, 30, 30, 31, 32, 32, 32, 32, 31, 31, 30, 28, 26, 23, 20, 18, 16, 13, 8, 13, 22, 147, 167, 167, 119, -17, -19, -27, -26, -140, -156};

    Ship() {
        this.x = 500;
        this.y = 300;
        this.a = Math.PI / 2;
        this.s = 0;
    }

    Ship(double x, double y, double a, double s) {
        this.x = x;
        this.y = y;
        this.a = a;
        this.s = s;
    }

    public int[] getXPoints() {
        int[] resXPoints = new int[this.xPoints.length];
        for (int i = 0; i < this.xPoints.length; i++) {
            resXPoints[i] = (int)(this.x + this.xPoints[i]);
        }

        return resXPoints;
    }

    public int[] getYPoints() {
        int[] resYPoints = new int[this.yPoints.length];
        for (int i = 0; i < this.yPoints.length; i++) {
            resYPoints[i] = (int)(this.y + this.yPoints[i]);
        }

        return resYPoints;
    }

    public void rotation(double da) {
        this.a += da;
        for (int i = 0; i < xPoints.length; i++) {
            double newX = xPoints[i] * Math.cos(da) - yPoints[i] * Math.sin(da);
            double newY = xPoints[i] * Math.sin(da) + yPoints[i] * Math.cos(da);

            xPoints[i] = newX;
            yPoints[i] = newY;
        }
    }

    public void scaling(double ds) {
        this.s *= ds;
        for (int i = 0; i < xPoints.length; i++) {
            double newX = xPoints[i] * ds;
            double newY = yPoints[i] * ds;

            xPoints[i] = newX;
            yPoints[i] = newY;
        }
    }

    public void translation(double dx, double dy) {
        this.x = this.x + Math.sin(this.a) * dx + Math.cos(this.a) * dy;
        this.y = this.y - Math.cos(this.a) * dx + Math.sin(this.a) * dy;
    }
}