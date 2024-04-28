
import java.io.*;
import java.util.Scanner;

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
