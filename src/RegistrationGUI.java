import javax.swing.*;
import java.awt.*;

public class RegistrationGUI extends JFrame {
    private JTextField nameField, usernameField;
    private JPasswordField passwordField;
    private JButton submitButton;

    public RegistrationGUI() {
        setTitle("Register");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        JPanel centerPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        nameField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        centerPanel.add(new JLabel("Name:"));
        centerPanel.add(nameField);
        centerPanel.add(new JLabel("Username:"));
        centerPanel.add(usernameField);
        centerPanel.add(new JLabel("Password:"));
        centerPanel.add(passwordField);

        JPanel southPanel = new JPanel();
        submitButton = new JButton("Submit");
        southPanel.add(submitButton);

        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

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
    }
}
