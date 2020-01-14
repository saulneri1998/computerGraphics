/**
 * Draws the outline of an n sided diamond on console. Dimensions are passed through args.
 * @author  Saul Neri A01652526
 * @since   Wednesday, 8 January 2020. 1578505890
 */
public class Diamond {
    public static void main(String[] args) {
        final int side = Integer.parseInt(args[0]);
        // Define midpoint and separation from leftmost/rightmost point to midpoint
        int mid = side - 1;
        int delta = 0;

        for (int i = 0; i < 2 * side - 1; i++) {
            for (int j = 0; j <= mid + delta; j++) {
                // Print '+' in leftmost/rightmost points
                if (j == mid - delta || j == mid + delta) {
                    System.out.print("+");
                } else {
                    System.out.print(" ");
                }
            }

            // Increment separation for upper half, decrement for lower half
            if (i < mid) {
                delta++;
            } else {
                delta--;
            }

            System.out.print("\n");
        }
    }    
}