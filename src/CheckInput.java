public class CheckInput {
    public static boolean validate(String username, String password) {
        return password.length() >= 12 && password.matches(".*[a-z].*") && password.matches(".*[A-Z].*") && password.matches(".*[!@#$%^&*].*");
    }
}
