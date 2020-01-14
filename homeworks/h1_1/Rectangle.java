/**
 * Draws the outline of an m x n rectangle on console. Dimensions are passed through args.
 * @author  Saul Neri A01652526
 * @since   Wednesday, 8 January 2020. 1578504518
 */
public class Rectangle {
    public static void main(String[] args) {
        final int rows = Integer.parseInt(args[0]);
        final int cols = Integer.parseInt(args[1]);

        // Loop through number of rows, then through number of columns
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // For rows different than first and last
                if (i > 0 && i < rows - 1) {
                    // For the first and last columns 
                    if (j == 0 || j == cols - 1) {
                        System.out.print("+");
                    } else {
                        System.out.print(" ");
                    }
                } else {
                    System.out.print("+");
                }
            }
            System.out.print("\n");
        }
    }
}