
import java.io.*;
import java.util.Scanner;
/* Purpose of the program: 
 * This program is designed to manage user data for applications requiring user authentication and registration.
 * It handles the basic functionality of registering new users with their name, username, and password,
 * and allows for checking credentials of existing users. The data is stored in a plain text file, enabling
 * persistence of user information across sessions.
 * 
 * Group 7: Matthew Salazar & Dennis Nguyen
 * Class: CMSC 495 / 7380
 * Date: 4 May 2024
 * Revision: 2.0
 * 
 */
public class UserDataManager {
    private static final String USER_FILE = "user.txt";

    public static void registerUser(String name, String username, String password) {
        try (FileWriter fw = new FileWriter(USER_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(username + "," + password + "," + name);  // Save username, password and name
        } catch (IOException e) {
            System.err.println("Error writing to user file: " + e.getMessage());
        }
    }

    public static boolean checkCredentials(String username, String password) {
        try (Scanner scanner = new Scanner(new File(USER_FILE))) {
            while (scanner.hasNextLine()) {
                String[] credentials = scanner.nextLine().split(",");
                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    return true;  // User found with matching password
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("User file not found: " + e.getMessage());
        }
        return false;  // User not found or password does not match
    }
}
