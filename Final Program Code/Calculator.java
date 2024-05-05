/* Purpose of the program: 
 *This class provides a utility for calculating a score based on the type of workout and the duration in hours.
 * It is designed to support a fitness application, helping quantify the intensity and effort of different
 * types of physical activities, thus allowing users to track their fitness progress quantitatively.
 * 
 * Group 7: Matthew Salazar & Dennis Nguyen
 * Class: CMSC 495 / 7380
 * Date: 4 May 2024
 * Revision: 2.0
 * 
 */


public class Calculator {
    public static double calculateScore(String type, double hours) {
        switch (type) {
            case "Walking":
                return hours * 1;
            case "Running":
            	 return hours * 1.5;
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
