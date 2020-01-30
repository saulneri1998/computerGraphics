import java.awt.Color;

/**
 * Loader
 * @author  Saul Neri A01652526
 * @since   Wednesday, 29 January 2020
 */
public class Loader {

    public static void main(String[] args) {
        Point3D[] vertices = new Point3D[20];
        vertices[ 0] = new Point3D( -1, -1, 0 );
        vertices[ 1] = new Point3D( -1, -1, 1 );
        vertices[ 2] = new Point3D( -1, 0, 0 );
        vertices[ 3] = new Point3D( -1, 0, 1 );
        vertices[ 4] = new Point3D( -1, 1, 0 );
        vertices[ 5] = new Point3D( -1, 1, 1 );
        vertices[ 6] = new Point3D( 0, -1, 0 );
        vertices[ 7] = new Point3D( 0, -1, 1 );
        vertices[ 8] = new Point3D( 0, 0, 0 );
        vertices[ 9] = new Point3D( 0, 0, 1 );
        vertices[10] = new Point3D( 0, 1, 0 );
        vertices[11] = new Point3D( 0, 1, 1 );
        vertices[12] = new Point3D( 1, -1, 0 );
        vertices[13] = new Point3D( 1, -1, 1 );
        vertices[14] = new Point3D( 1, 0, 0 );
        vertices[15] = new Point3D( 1, 0, 1 );
        vertices[16] = new Point3D( 2, -1, 0 );
        vertices[17] = new Point3D( 2, -1, 1 );
        vertices[18] = new Point3D( 2, 0, 0 );
        vertices[19] = new Point3D( 2, 0, 1 );

        Face[] faces = new Face[18];
        faces[ 0] = new Face(0, 1, 3, 2, Color.RED);
        faces[ 1] = new Face(2, 3, 5, 4, Color.GREEN);
        faces[ 2] = new Face(0, 1, 7, 6, Color.BLUE);
        faces[ 3] = new Face(1, 3, 9, 7, Color.MAGENTA);
        faces[ 4] = new Face(3, 5, 11, 9, Color.YELLOW);
        faces[ 5] = new Face(4, 5, 11, 10, Color.CYAN);
        faces[ 6] = new Face(2, 4, 10, 8, Color.RED);
        faces[ 7] = new Face(0, 2, 8, 6, Color.GREEN);
        faces[ 8] = new Face(8, 9, 11, 10, Color.BLUE);
        faces[ 9] = new Face(6, 7, 13, 12, Color.MAGENTA);
        faces[10] = new Face(7, 9, 15, 13, Color.YELLOW);
        faces[11] = new Face(8, 9, 15, 14, Color.CYAN);
        faces[12] = new Face(6, 8, 14, 12, Color.RED);
        faces[13] = new Face(12, 13, 17, 16, Color.GREEN);
        faces[14] = new Face(13, 15, 19, 17, Color.BLUE);
        faces[15] = new Face(14, 15, 19, 18, Color.MAGENTA);
        faces[16] = new Face(12, 14, 18, 16, Color.YELLOW);
        faces[17] = new Face(16, 17, 19, 18, Color.CYAN);
        
        new Wireframe(vertices, faces);
    }
}