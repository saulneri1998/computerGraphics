import java.awt.Color;

/**
 * Face
 */
public class Face {
    int p1;
    int p2;
    int p3;
    int p4;

    Color color;

    Face(int p1, int p2, int p3, int p4, Color color) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;

        this.color = color;
    }

    public double getDistance(Point3D[] v, double theta, double phi) {
        double x0 = (v[p1].x + v[p2].x + v[p3].x + v[p4].x) / 4;
        double y0 = (v[p1].y + v[p2].y + v[p3].y + v[p4].y) / 4;
        double z0 = (v[p1].z + v[p2].z + v[p3].z + v[p4].z) / 4;

        double cosT = (float)Math.cos( theta ), sinT = (float)Math.sin( theta );
        double cosP = (float)Math.cos( phi ), sinP = (float)Math.sin( phi );
        double cosTcosP = cosT*cosP, cosTsinP = cosT*sinP,
                sinTcosP = sinT*cosP, sinTsinP = sinT*sinP;

        // double distX = cosT*x0 + sinT*z0;
        // double distY = -sinTsinP*x0 + cosP*y0 + cosTsinP*z0;
        double distZ = cosTcosP*z0 - sinTcosP*x0 - sinP*y0;

        return(distZ);
    }

}