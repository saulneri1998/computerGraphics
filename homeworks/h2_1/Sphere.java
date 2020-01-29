import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Sphere
 */
public class Sphere {

    public static void main(String[] args) {
        final int r  = Integer.parseInt(args[0]);
        final int yrc  = Integer.parseInt(args[1]);
        final int xzd  = Integer.parseInt(args[2]);

        int nVertices = xzd * yrc + 2;
        int nEdges = xzd * (2*yrc + 1);
        // int nEdges = 2*xzd;


        Point3D[] vertices = new Point3D[ nVertices ];
        Edge[] edges = new Edge[ nEdges ];

        double[] ys = new double[yrc+2];
        for (int i = 0; i < yrc+2; i++) {
            ys[i] = -r + ((double)i * 2*(double)r / (double)(yrc+1));
            System.out.println("YYYY: " +  ys[i]);
        }

        int index = 0;
        for (int i = 1; i < ys.length-1; i++) {
            double tempRH = Math.abs(ys[i]);
            double tempR = Math.sqrt(r*r - tempRH*tempRH);

            System.out.println("----> " + tempRH + ", " + tempR + ", " + ys[i]);
            
            double tempX = 0;
            double tempZ = 0;
            for (int j = 0; j < xzd; j++) {
                tempX = tempR * Math.cos((double)j * 2*Math.PI / (double)xzd);
                tempZ = tempR * Math.sin((double)j * 2*Math.PI / (double)xzd);
                vertices[index] = new Point3D(tempX, ys[i], tempZ);
                System.out.println(tempX + ", " + ys[i] + ", " + tempZ);
                index++;
            }
        }
        vertices[index] = new Point3D(0, r, 0);
        System.out.println(0 + ", " + r + ", " + 0);
        index++;
        vertices[index] = new Point3D(0, -r, 0);
        System.out.println(0 + ", " + -r + ", " + 0);

        index = 0;
        for (int i = 0; i < nVertices; i++) {
            if (i < xzd) {
                edges[index] = new Edge(i, nVertices-1);
                System.out.println("Edge x: " + i + ", " + (nVertices-1));
                index++;
            }
            if (i > nVertices-xzd-3 && i < nVertices-2) {
                edges[index] = new Edge(i, nVertices-2);
                System.out.println("Edge x: " + i + ", " + (nVertices-2));
                index++;
            }
            if (i < nVertices-xzd-2) {
                edges[index] = new Edge(i, i + xzd);
                System.out.println("Edge x: " + i + ", " + (i + xzd));
                index++;
            }

            if (i < nVertices - 2) {
                if ((i+1) % xzd != 0) {
                    edges[index] = new Edge(i, i + 1);
                    System.out.println("Edge x: " + i + ", " + (i + 1));
                    index++;
                } else {
                    edges[index] = new Edge(i, i - xzd + 1);
                    System.out.println("Edge x: " + i + ", " + (i - xzd + 1));
                    index++;
                }
            }
        }

        new Wireframe(vertices, edges);
    }
}
