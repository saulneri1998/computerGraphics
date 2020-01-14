/**
 * Draws the outline of an n sided hexagon on console. Dimensions are passed through args.
 * @author  Saul Neri A01652526
 * @since   Wednesday, 8 January 2020. 1578510432
 */
public class Hexagon {
    public static void main(String[] args) {
        final int side = Integer.parseInt(args[0]);
        // Define leading space and separation between leftmost and rightmost points.
        int leading = side - 1;
        int separation = side - 2;

        for (int i = 0; i < 2 * side - 1; i++) {
            // First and last rows
            if (i == 0 || i == 2 * side - 2) {
                for (int k = 0; k < side - 1; k++) {
                    System.out.print(" ");
                }
                for (int k = 0; k < side; k++) {
                    System.out.print("+");
                }
            } else {
                for (int l = 0; l < leading; l++) {
                    System.out.print(" ");
                }
                System.out.print("+");
                for (int s = 0; s < separation; s++) {
                    System.out.print(" ");
                }
                System.out.print("+");
            }

            // Modify leading and separation.
            if (i < side-1) {
                separation += 2;
                leading--;
            } else {
                separation -= 2;
                leading++;
            }

            System.out.print("\n");
        }
    }
}