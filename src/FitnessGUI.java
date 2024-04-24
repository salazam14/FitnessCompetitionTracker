import javax.swing.*;
import java.awt.*;

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
        // Implement workout logging
    }

    private void showHistory() {
        // Implement history display
    }
}
