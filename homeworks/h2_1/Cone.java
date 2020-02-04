import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Cone
 * @author  Saul Neri A01652526
 * @since   Wednesday, 29 January 2020
 */
public class Cone {

    public static void main(String[] args) {
        final int r  = Integer.parseInt(args[0]);
        final int h  = Integer.parseInt(args[1]);
        final int bd  = Integer.parseInt(args[2]);
        final int hd = Integer.parseInt(args[3]);

        int nVertices = bd * hd + hd + 1;
        int nEdges = 3 * bd * hd;

        Point3D[] vertices = new Point3D[ nVertices ];
        Edge[] edges = new Edge[ nEdges ];

        double[] ys = new double[hd+1];
        for (int i = 0; i < hd+1; i++) {
            ys[i] = -h / 2 + ((double)i * h / hd);
        }

        int index = 0;
        for (int i = 0; i < hd; i++) {
            vertices[index] = new Point3D(0, ys[i], 0);
            index++;

            double tempR = (double)r * ((double)h - ((double)i*(double)h/(double)hd)) / (double)h;

            double tempX = 0;
            double tempZ = 0;
            for (int j = 0; j < bd; j++) {
                tempX = tempR * Math.cos((double)j * 2*Math.PI / (double)bd);
                tempZ = tempR * Math.sin((double)j * 2*Math.PI / (double)bd);
                vertices[index] = new Point3D(tempX, ys[i], tempZ);
                index++;
            }
        }
        vertices[index] = new Point3D(0, ys[ys.length-1], 0);

        index = 0;
        for (int i = 0; i < nVertices; i++) {
            if (i % (bd+1) != 0) {
                edges[index] = new Edge(i, i - (i % (bd+1)));
                index++;

                edges[index] = new Edge(i, Math.min(i + (bd+1), nVertices-1));
                index++;
            }
            if ((i+1) % (bd+1) == 0) {
                edges[index] = new Edge(i, i - bd + 1);
                index++;
            } else if (i % (bd+1) != 0) {
                edges[index] = new Edge(i, i + 1);
                index++;
            }
        }

        new Wireframe(vertices, edges);
    }
}
