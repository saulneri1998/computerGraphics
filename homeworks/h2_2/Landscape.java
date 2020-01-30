/**
 * Landscape
 * @author  Saul Neri A01652526
 * @since   Wednesday, 29 January 2020
 */
public class Landscape {

    public static void main(String[] args) {
        double[][] m1 = new double[2][2];
        double sign = Math.random() > 0.5 ? 1 : -1;
        m1[0][0] = sign * Math.random() * 1.2;
        sign = Math.random() > 0.5 ? 1 : -1;
        m1[0][1] = sign * Math.random() * 1.2;
        sign = Math.random() > 0.5 ? 1 : -1;
        m1[1][0] = sign * Math.random() * 1.2;
        sign = Math.random() > 0.5 ? 1 : -1;
        m1[1][1] = sign * Math.random() * 1.2;

        double[][] m = m1;
        for (int i = 0; i < 6; i++) {
            double delta = 0.08;
            m = expandMatrix(m, delta);
            delta *= Math.random();
        }

        Point3D[] vertices = returnVertices(m, 4);
        Edge[] edges = returnEdges(m.length);

        Object[] geometry = addTrees(vertices, edges);

        new Wireframe((Point3D[])geometry[0], (Edge[])geometry[1]);
    }

    public static Object[] addTrees(Point3D[] vertices, Edge[] edges) {
        int verticesCount = vertices.length;
        Point3D[] newVertices = vertices;
        Edge[] newEdges = edges;

        for (Point3D v : vertices) {
            verticesCount = newVertices.length;
            if (v.y > 0.1 && Math.random() < 0.01) {
                Point3D[] nv = new Point3D[73];
                Edge[] ne = new Edge[72];
                nv[0] = v;

                int index = 0;
                int root = 0;
                double branchSize = 1;
                for (int j = 1; j < 9; j++) {
                    nv[index+1] = new Point3D(nv[root].x, nv[root].y+0.03, nv[root].z);
                    ne[index] = new Edge(verticesCount+root, verticesCount+root+1, j);
                    index++;
                    root = (j-1) * 9 + 1; 
                    double ang = 0;
                    for (int i = 0; i < 8; i++) {
                        double newX = nv[root].x + 0.09 * Math.cos(ang) * branchSize;
                        double newZ = nv[root].z + 0.09 * Math.sin(ang) * branchSize;
                        double sign = Math.random() > 0.5 ? 1 : -1;
                        double newY = nv[root].y + (sign * Math.random() * 0.03);
                        nv[index+1] = new Point3D(newX, newY, newZ);
                        ne[index] = new Edge(verticesCount+root, verticesCount+root+i, j);
                        index++;
                        ang = ang + (Math.PI/4);
                    }
                    branchSize -= 0.1;
                }

                newVertices = mergeVertices(newVertices, nv);
                newEdges = mergeEdges(newEdges, ne);
            }
        }
        
        return new Object[] {newVertices, newEdges};
    }


    public static Point3D[] mergeVertices(Point3D[] v1, Point3D[] v2) {
        Point3D[] newVertices = new Point3D[v1.length + v2.length];

        for (int i = 0; i < v1.length + v2.length; i++) {
            if (i < v1.length) {
                newVertices[i] = v1[i];
            } else {
                newVertices[i] = v2[i - v1.length];
            }
        }

        return newVertices;
    }

    public static Edge[] mergeEdges(Edge[] e1, Edge[] e2) {
        Edge[] newEdges = new Edge[e1.length + e2.length];

        for (int i = 0; i < e1.length + e2.length; i++) {
            if (i < e1.length) {
                newEdges[i] = e1[i];
            } else {
                newEdges[i] = e2[i - e1.length];
            }
        }

        return newEdges;
    }

    public static double[][] expandMatrix(double[][] m1, double delta) {
        int size = 2 * m1.length - 1;
        double[][] m = new double[size][size];

        // fill previous points
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                m[2*i][2*j] = m1[i][j];
            }
        }

        // fill diagonals
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (i % 2 != 0 && j % 2 != 0) {
                    double sum = m[i-1][j-1] + m[i-1][j+1] + m[i+1][j-1] + m[i+1][j+1];
                    double sign = Math.random() > 0.5 ? 1 : -1;
                    double rand = sign * Math.random() * delta;
                    m[i][j] = sum / 4.0d + rand;
                }
            }
        }

        // fill remainders
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if ((i % 2 == 0 && j % 2 != 0) || (i % 2 != 0 && j % 2 == 0)) {
                    double sum = 0;
                    int total = 0;

                    if (i - 1 >= 0) {
                        sum += m[i-1][j];
                        total++;
                    } 
                    if (i + 1 < size) {
                        sum += m[i+1][j];
                        total++;
                    }
                    if (j - 1 >= 0) {
                        sum += m[i][j-1];
                        total++;
                    }
                    if (j + 1 < size) {
                        sum += m[i][j+1];
                        total++;
                    } 
                    double sign = Math.random() > 0.5 ? 1 : -1;
                    double rand = sign * Math.random() * delta;
                    m[i][j] = sum / (double)total + rand;
                }
            }
        }

        return m;
    }

    public static Point3D[] returnVertices(double[][] m, double gridSize) {
        Point3D[] vertices = new Point3D[m.length * m[0].length];

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                double x = -gridSize/2 + (gridSize * (double)i / (double)(m.length-1));
                double z = -gridSize/2 + (gridSize * (double)j / (double)(m[0].length-1));
                vertices[i*m.length + j] = new Point3D(x, m[i][j], z);
            }
        }

        return vertices;
    }

    public static Edge[] returnEdges(int size) {
        Edge[] edges = new Edge[2 * size * (size-1)];

        int index = 0;
        for (int i = 0; i < size*size; i++) {
            if ((i+1) % size != 0) {
                edges[index] = new Edge(i, i+1, 0);
                index++;
            }
            if (i < size*(size-1)) {
                edges[index] = new Edge(i, i+size, 0);
                index++;
            }
        }

        return edges;
    }
}