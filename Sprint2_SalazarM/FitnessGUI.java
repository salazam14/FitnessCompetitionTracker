import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FitnessGUI extends JFrame {
    private JButton workoutButton, historyButton;
    private String username;

    public FitnessGUI(String username) {
        this.username = username;
        setTitle("Fitness Dashboard");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        workoutButton = new JButton("Log Workout");
        historyButton = new JButton("Workout History");

        JPanel centerPanel = new JPanel();
        centerPanel.add(workoutButton);
        centerPanel.add(historyButton);
        
        
        add(centerPanel, BorderLayout.CENTER);

        workoutButton.addActionListener(e -> logWorkout());
        historyButton.addActionListener(e -> showHistory());

        setVisible(true);
    }

    private void logWorkout() {
        JFrame workoutFrame = new JFrame("Log Workout");
        workoutFrame.setSize(300, 200);
        workoutFrame.setLayout(new BorderLayout());
        workoutFrame.setLocationRelativeTo(null);
        workoutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel for user inputs
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));

        inputPanel.add(new JLabel("Workout Type:"));
        String[] workoutTypes = {"Walking", "Running", "Swimming", "Biking", "Weight Lifting", "Rowing"};
        JComboBox<String> typeComboBox = new JComboBox<>(workoutTypes);
        inputPanel.add(typeComboBox);

        inputPanel.add(new JLabel("Hours Logged:"));
        JTextField hoursField = new JTextField();
        inputPanel.add(hoursField);

        workoutFrame.add(inputPanel, BorderLayout.CENTER);

        // Submit button
        JButton submitButton = new JButton("Submit");
        workoutFrame.add(submitButton, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> {
            try {
                String type = (String) typeComboBox.getSelectedItem();
                double hours = Double.parseDouble(hoursField.getText());  // This might throw NumberFormatException
                String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
                saveWorkoutData(type, hours, timestamp);
                workoutFrame.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(workoutFrame, "Invalid Input. Please enter a valid number for hours.", "Input Error", JOptionPane.ERROR_MESSAGE);
                hoursField.requestFocus();  // Request focus back to the hours input field for correction
            }
        });

        workoutFrame.setVisible(true);
    }

    private void saveWorkoutData(String type, double hours, String timestamp) {
        String record = username + "," + type + "," + hours + "," + timestamp + "\n";
        try (FileWriter fw = new FileWriter("workout_data.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.print(record);
        } catch (IOException e) {
            System.err.println("Error writing to workout file: " + e.getMessage());
        }
    }



    private void showHistory() {
        JFrame historyFrame = new JFrame("Workout History");
        historyFrame.setSize(500, 300);
        historyFrame.setLayout(new BorderLayout());
        historyFrame.setLocationRelativeTo(null);
        historyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea historyArea = new JTextArea();
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        historyFrame.add(scrollPane, BorderLayout.NORTH);

        double totalScore = 0;

        try (Scanner scanner = new Scanner(new File("workout_data.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data[0].equals(username)) {
                    double hours = Double.parseDouble(data[2]);
                    double score = Calculator.calculateScore(data[1], hours);
                    totalScore += score;
                    historyArea.append("Type: " + data[1] + ", Hours: " + data[2] + ", Timestamp: " + data[3] + ", Score: " + score + "\n");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Workout file not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number: " + e.getMessage());
        }

        JLabel scoreLabel = new JLabel("Total Score: " + totalScore);
        historyFrame.add(scoreLabel, BorderLayout.CENTER);

        historyFrame.setVisible(true);
    }


}
