
public class Calculator {
    public static double calculateScore(String type, double hours) {
        switch (type) {
            case "Walking":
                return hours * 1;
            case "Running":
            case "Biking":
                return hours * 1.5;
            case "Weight Lifting":
                return hours * 2;
            case "Rowing":
                return hours * 3;
            case "Swimming":
                return hours * 4;
            default:
                return 0;
        }
    }
}
