import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public MainGUI() {
        setTitle("Fitness Tracker");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        centerPanel.add(new JLabel("Username:"));
        centerPanel.add(usernameField);
        centerPanel.add(new JLabel("Password:"));
        centerPanel.add(passwordField);

        JPanel southPanel = new JPanel();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        southPanel.add(loginButton);
        southPanel.add(registerButton);

        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        registerButton.addActionListener(e -> new RegistrationGUI());
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (UserDataManager.checkCredentials(username, password)) {
                new FitnessGUI(username);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}
