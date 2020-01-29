import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Box
 */
public class Box {

    public static void main(String[] args) {
        final int x  = Integer.parseInt(args[0]);
        final int y  = Integer.parseInt(args[1]);
        final int z  = Integer.parseInt(args[2]);
        final int xc = Integer.parseInt(args[3]);
        final int yc = Integer.parseInt(args[4]);
        final int zc = Integer.parseInt(args[5]);

        int nVertices = (xc+1) * (yc+1) * (zc+1);
        int nEdges = (xc*(yc+1)*(zc+1)) + (yc*(xc+1)*(zc+1)) + (zc*(yc+1)*(xc+1));

        Point3D[] vertices = new Point3D[ nVertices ];
        Edge[] edges = new Edge[ nEdges ];

        System.out.println((xc+1) * (yc+1) * (zc+1));

        double[] xs = new double[xc+1];
        double[] ys = new double[yc+1];
        double[] zs = new double[zc+1];

        for (int i = 0; i < xc+1; i++) {
            xs[i] = -x / 2 + ((double)i * x / xc);
        }

        for (int i = 0; i < yc+1; i++) {
            ys[i] = -y / 2 + ((double)i * y / yc);
        }

        for (int i = 0; i < zc+1; i++) {
            zs[i] = -z / 2 + ((double)i * z / zc);
        }

        int index = 0;
        for (double xi : xs) {
            for (double yi : ys) {
                for (double zi : zs) {
                    vertices[index] = new Point3D(xi, yi, zi);
                    System.out.println(xi + ", " + yi + ", " + zi);
                    index++;
                }
            }
        }

        index = 0;
        for (int i = 0; i < nVertices; i++) {
            if ((i+1) % (zc+1) != 0) {
                edges[index] = new Edge(i, i + 1);
                System.out.println("Edge x: " + i + ", " + (i + 1));
                index++;
            }

            if ((i/(zc+1)+1) % (yc+1) != 0) {
                edges[index] = new Edge(i, i + zc + 1);
                System.out.println("Edge y: " + i + ", " + (i + zc + 1));
                index++;
            }

            if ((i/((zc+1)*(yc+1))+1) % (xc+1) != 0) {
                edges[index] = new Edge(i, i + ((zc+1)*(yc+1)));
                System.out.println("Edge z: " + i + ", " + (i + ((zc+1)*(yc+1))));
                index++;
            }
        }

        new Wireframe(vertices, edges);
    }
}