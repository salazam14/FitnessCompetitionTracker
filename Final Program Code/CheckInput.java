/* Purpose of the program: 
 * This class provides validation functionality for user inputs, specifically checking the strength and 
 * criteria compliance of passwords. It ensures that passwords meet specific security requirements, such as minimum 
 * length and the inclusion of various character types.
 *
 * Group 7: Matthew Salazar & Dennis Nguyen
 * Class: CMSC 495 / 7380
 * Date: 4 May 2024
 * Revision: 2.0
 * 
 */

public class CheckInput {
    public static boolean validate(String username, String password) {
        return password.length() >= 12 && password.matches(".*[a-z].*") && 
        		password.matches(".*[A-Z].*") && password.matches(".*[!@#$%^&*].*");
    }
}
