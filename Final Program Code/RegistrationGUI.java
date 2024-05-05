import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RegistrationGUI extends JFrame {
    private JTextField nameField, usernameField;
    private JPasswordField passwordField;
    private JButton submitButton;

    public RegistrationGUI() {
        setTitle("Registration");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            // Load the image
            ImageIcon backgroundImage = new ImageIcon("C:/Users/msall/Downloads/fitnessimage1.jpg"); // Change the path to your image
            // Create a JLabel with the image
            JLabel backgroundLabel = new JLabel(backgroundImage);
            // Set layout to null to position components freely
            setLayout(null);
            // Set bounds for the background label to cover the entire frame
            backgroundLabel.setBounds(0, 0, 300, 250);
            // Add the background label to the content pane
            getContentPane().add(backgroundLabel);

            // Create the center panel with GridLayout for input fields
            JPanel centerPanel = new JPanel(new GridLayout(3, 2, 5, 5));
            nameField = new JTextField();
            usernameField = new JTextField();
            passwordField = new JPasswordField();

            // Create JLabels for "Name:", "Username:", and "Password:" with different colors
            JLabel nameLabel = new JLabel("Name:");
            nameLabel.setForeground(Color.WHITE); // Set color to white
            centerPanel.add(nameLabel);
            centerPanel.add(nameField);

            JLabel usernameLabel = new JLabel("Username:");
            usernameLabel.setForeground(Color.WHITE); // Set color to white
            centerPanel.add(usernameLabel);
            centerPanel.add(usernameField);

            JLabel passwordLabel = new JLabel("Password:");
            passwordLabel.setForeground(Color.WHITE); // Set color to white
            centerPanel.add(passwordLabel);
            centerPanel.add(passwordField);

            centerPanel.setOpaque(false); // Make the center panel transparent

            JPanel southPanel = new JPanel();
            submitButton = new JButton("Submit");
            southPanel.add(submitButton);
            southPanel.setOpaque(false); // Make the south panel transparent

            // Set bounds for the center panel and south panel to position them within the frame
            centerPanel.setBounds(50, 50, 200, 100);
            southPanel.setBounds(50, 160, 200, 40);

            // Add center panel and south panel to the background label
            backgroundLabel.add(centerPanel);
            backgroundLabel.add(southPanel);

            submitButton.addActionListener(e -> {
                String name = nameField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (CheckInput.validate(username, password)) {
                    UserDataManager.registerUser(name, username, password);
                    JOptionPane.showMessageDialog(this, "Registration successful!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password!");
                }
            });

            setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationGUI());
    }
}
