import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/* Purpose of the program: 
 * This program serves as the main graphical user interface for a Fitness Tracker application.
 * It facilitates user interaction for logging into and registering for the fitness application,
 * enhancing the user experience with aesthetic and functional components such as background images,
 * customized text fields, and responsive buttons. The GUI is designed to be intuitive and visually appealing,
 * encouraging user engagement and regular usage.
 * 
 * Group 7: Matthew Salazar & Dennis Nguyen
 * Class: CMSC 495 / 7380
 * Date: 4 May 2024
 * Revision: 2.0
 * 
 */

public class MainGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public MainGUI() {
        // Set the title and size of the JFrame
        setTitle("Fitness Tracker");
        setSize(500, 400); // Adjust the size as needed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the north panel for welcome message
        JPanel northPanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome to the Fitness Tracker!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
       
       
        northPanel.add(welcomeLabel);
        northPanel.setBackground(Color.DARK_GRAY);
        welcomeLabel.setForeground(Color.WHITE);

        // Load the image to be used as background and resize it
        try {
            Image backgroundImage = ImageIO.read(new File("C:/Users/msall/Downloads/fitnessimage1.jpg")); // Change "path_to_your_image.jpg" to the actual path of your image
            ImageIcon resizedImageIcon = new ImageIcon(backgroundImage.getScaledInstance(500, 400, Image.SCALE_SMOOTH));
            setContentPane(new JLabel(resizedImageIcon));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create the center panel with GridLayout for input fields
        JPanel centerPanel = new JPanel(new GridLayout(4, 1, 2, 2)); // 4 rows, 1 column, with 2px spacing
        centerPanel.setOpaque(false); // Make the panel transparent for background image

        // Add components to the center panel
        usernameField = new JTextField(20);
        usernameField.setForeground(Color.WHITE);
        usernameField.setFont(new Font("Arial", Font.BOLD, 20));
        passwordField = new JPasswordField(20);
        passwordField.setForeground(Color.WHITE);
        passwordField.setFont(new Font("Arial", Font.BOLD, 20));
        usernameField.setOpaque(false); // Make the text field transparent
        passwordField.setOpaque(false); // Make the text field transparent
        passwordField.setEchoChar('\u25CF'); // Set password field echo character to a bullet

        // Create separate JLabels for "Username:" and "Password:" with different colors
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));

        centerPanel.add(usernameLabel);
        centerPanel.add(usernameField);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);
        centerPanel.add(new JLabel()); // Empty label for spacing
      

        // Add center panel to the content pane
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(northPanel, BorderLayout.NORTH); // Add north panel with welcome message
        getContentPane().add(centerPanel, BorderLayout.CENTER);

        // Create the south panel for buttons
        JPanel southPanel = new JPanel();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        southPanel.add(loginButton);
        southPanel.add(registerButton);
        southPanel.setOpaque(false);

        // Add south panel to the content pane
        getContentPane().add(southPanel, BorderLayout.SOUTH);

        // Add action listeners to the buttons
        registerButton.addActionListener(e -> new RegistrationGUI());
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim(); // Trim the input
                String password = new String(passwordField.getPassword()).trim(); // Trim the input

                // Check if username and password are not empty and validate credentials
                if (!username.isEmpty() && !password.isEmpty()) {
                    if (UserDataManager.checkCredentials(username, password)) {
                        // If credentials are valid, create an instance of FitnessGUI and dispose MainGUI
                        new FitnessGUI(username);
                        dispose();
                    } else {
                        // Show error message if credentials are invalid
                        JOptionPane.showMessageDialog(MainGUI.this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // Show error message if username or password is empty
                    JOptionPane.showMessageDialog(MainGUI.this, "Please enter username and password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of MainGUI
        SwingUtilities.invokeLater(() -> new MainGUI());
    }
}
