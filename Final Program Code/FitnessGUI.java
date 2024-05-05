import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/* Purpose of the program: 
 * This program provides a user interface for a fitness application that allows users to log workouts,
 * view their workout history, and visualize their workout data with background images that enhance user experience.
 * It supports user interaction through a graphical interface and manages user-specific data, enhancing functionality
 * for fitness tracking and historical data review..
 * 
 * Group 7: Matthew Salazar & Dennis Nguyen
 * Class: CMSC 495 / 7380
 * Date: 4 May 2024
 * Revision: 2.0
 * 
 */

public class FitnessGUI extends JFrame {
    private JButton workoutButton, historyButton;
    private Image backgroundImage;
    private String username;
    
    public FitnessGUI(String username) {
    	this.username = username;
        setTitle("Fitness Dashboard");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:/Users/msall/Downloads/fitnessimage2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading background image: " + e.getMessage(), "Image Error", JOptionPane.ERROR_MESSAGE);
        }

        // Create buttons
        workoutButton = new JButton("Log Workout");
        historyButton = new JButton("Workout History");

        // Create a panel that will paint the background image
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        // Configure center panel for buttons
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false); // Make the panel transparent

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; // End row after this component
        gbc.weightx = 3; // Distribute space
        gbc.fill = GridBagConstraints.BASELINE; // Make the button expand to fill the space

        // Align the panel itself centrally both vertically and horizontally
        backgroundPanel.setLayout(new GridBagLayout());

        // Add buttons to the panel
        centerPanel.add(workoutButton, gbc); // Add the first button
        centerPanel.add(historyButton, gbc); // Add the second button

        // Add the centered panel to the background panel
        backgroundPanel.add(centerPanel, gbc); // This places the centerPanel in the center of the backgroundPanel

        // Add action listeners
        workoutButton.addActionListener(e -> logWorkout());
        historyButton.addActionListener(e -> showHistory());

        // Set the custom panel as the content pane
        setContentPane(backgroundPanel);
        setVisible(true);
    }


    private void logWorkout() {
        JFrame workoutFrame = new JFrame("Log Workout");
        workoutFrame.setSize(300, 200);
        workoutFrame.setLocationRelativeTo(null);
        workoutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Load and set background image
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image img = ImageIO.read(new File("C:/Users/msall/Downloads/fitnessimage3.jpg")); // Adjust path as needed
                    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

     // Panel for user inputs
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.setOpaque(false); // Make input panel transparent

        // Create and modify labels
        JLabel workoutTypeLabel = new JLabel("Workout Type:");
        workoutTypeLabel.setForeground(Color.WHITE); // Set the color of the workout type label
        inputPanel.add(workoutTypeLabel);

        String[] workoutTypes = {"Walking", "Running", "Swimming", "Biking", "Weight Lifting", "Rowing"};
        JComboBox<String> typeComboBox = new JComboBox<>(workoutTypes);
        inputPanel.add(typeComboBox);

        JLabel hoursLoggedLabel = new JLabel("Hours Logged:");
        hoursLoggedLabel.setForeground(Color.WHITE); // Set the color of the hours logged label
        inputPanel.add(hoursLoggedLabel);

        JTextField hoursField = new JTextField();
        inputPanel.add(hoursField);

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setForeground(Color.WHITE);
        submitButton.setOpaque(false);
        submitButton.setContentAreaFilled(false);
        submitButton.setBorderPainted(false);

        backgroundPanel.add(inputPanel, BorderLayout.CENTER);
        backgroundPanel.add(submitButton, BorderLayout.SOUTH);

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

        workoutFrame.setContentPane(backgroundPanel);
        workoutFrame.setVisible(true);
    }

    private void saveWorkoutData(String type, double hours, String timestamp) {
        String record = username + "," + type + "," + hours + "," + timestamp + "\n";
        try (FileWriter fw = new FileWriter("workout_data.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.print(record);
            System.out.println("Data saved: " + record); // Logging to console
        } catch (IOException e) {
            System.err.println("Error writing to workout file: " + e.getMessage());
        }
    }




    private void showHistory() {
        JFrame historyFrame = new JFrame("Workout History");
        historyFrame.setSize(700, 200);
        historyFrame.setLocationRelativeTo(null);
        historyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Load and set background image
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image img = ImageIO.read(new File("C:/Users/msall/Downloads/fitnessimage4.jpg")); // Adjust path as needed
                    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        JTextArea historyArea = new JTextArea();
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.getViewport().setOpaque(false);

        double totalScore = 0;
        try (Scanner scanner = new Scanner(new File("workout_data.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data[0].equals(username)) { // Make sure username is not null or incorrect
                    double hours = Double.parseDouble(data[2]);
                    double score = Calculator.calculateScore(data[1], hours);
                    totalScore += score;
                    historyArea.append("Exercise Type: " + data[1] + ", Hours: " + data[2] + ", Timestamp: " + data[3] + ", Score: " + score + "\n");
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Workout file not found: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading workout file: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error parsing number from file: " + e.getMessage());
        }

        JLabel scoreLabel = new JLabel("Total Score: " + totalScore);
        scoreLabel.setFont(new Font("Total Score", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setOpaque(false);

        backgroundPanel.add(scrollPane, BorderLayout.NORTH);
        backgroundPanel.add(scoreLabel, BorderLayout.CENTER);

        historyFrame.setContentPane(backgroundPanel);
        historyFrame.setVisible(true);
    }


}
