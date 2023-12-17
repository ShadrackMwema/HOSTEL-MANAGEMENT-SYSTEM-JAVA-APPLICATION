 import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 import java.awt.image.BufferedImage;
 import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 import java.awt.event.*;
public class AdminLoginDialog extends JDialog {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JProgressBar progressBar;
    private boolean shouldDispose = true;


    public AdminLoginDialog(JFrame parent) {




        super(parent, "", true);
          // Set a fixed location for the JFrame
        setLocationRelativeTo(null);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                // Reset the location if the component is moved
                setLocationRelativeTo(null);
            }
        });



        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);



        setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));

        JPanel mainPanel = new JPanel(null); // Use null layout for manual component placement

        // Create a panel for the photo
        JPanel photoPanel = new JPanel();
        photoPanel.setBounds(5, 15, 150, 130); // Set bounds for the photoPanel
        ImageIcon photoIcon = new ImageIcon("/home/shad/Downloads/PROJECTPICS/log.png"); // Replace with the actual path to your image
        JLabel photoLabel = new JLabel(photoIcon);
        photoLabel.setBounds(5, 15, 150, 130);
        photoPanel.add(photoLabel);

        // Create a panel for the login components
        JPanel loginPanel = new JPanel(null);
        loginPanel.setBounds(150, 15, 250, 130); // Set bounds for the loginPanel

        JLabel label = new JLabel("Admin Login:");
        label.setBounds(0, 5, 260, 20); // Set bounds for the label
        label.setHorizontalAlignment(SwingConstants.CENTER);
        loginPanel.add(label);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(10, 45, 80, 20); // Set bounds for the usernameLabel
        loginPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(90, 45, 150, 20); // Set bounds for the usernameField
        loginPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 70, 80, 20); // Set bounds for the passwordLabel
        loginPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(90, 75, 150, 20); // Set bounds for the passwordField
        loginPanel.add(passwordField);

        progressBar = new JProgressBar();
        progressBar.setBounds(40, 160, 340, 20);
        progressBar.setStringPainted(false); // Display percentage
        mainPanel.add(progressBar);
        progressBar.setBorder(BorderFactory.createEmptyBorder());
        progressBar.setForeground(Color.GREEN);




        setUndecorated(true);  // Remove the default window decorations

        // Create a custom close button
        ImageIcon closeButtonIcon = new ImageIcon("/home/shad/Pictures/Close all jframe.png"); // Replace with the actual path to your image
        JButton closeButton = new JButton(closeButtonIcon);
        closeButton.setBounds(415, 2, 30, 30); // Set bounds for the close button
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(
                        AdminLoginDialog.this,
                        "Are you sure you want to close the login window?",
                        "Confirm Close",
                        JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    dispose();
                    parent.dispose();
                } else if (option == JOptionPane.NO_OPTION) {
                    shouldDispose = false;
                }
            }
        });

        mainPanel.add(closeButton); // Add the close button to the mainPanel

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(
                        AdminLoginDialog.this,
                        "Are you sure you want to close the login window?",
                        "Confirm Close",
                        JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    dispose();
                    parent.dispose();
                } else if (option == JOptionPane.NO_OPTION) {
                    shouldDispose = false;
                }
            }
        });


        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(80, 105, 80, 25); // Set bounds for the signupButton
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameField.getText();
                char[] enteredPasswordChars = passwordField.getPassword();
                String enteredPassword = new String(enteredPasswordChars);

                // Add your database sign-up logic here
                if (isValidSignUp(enteredUsername, enteredPassword)) {
                    JOptionPane.showMessageDialog(null, "Login successful", "Login", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(AdminLoginDialog.this, "Failed to create account", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loginPanel.add(signupButton);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(170, 105, 80, 25); // Set bounds for the loginButton
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameField.getText();
                char[] enteredPasswordChars = passwordField.getPassword();
                String enteredPassword = new String(enteredPasswordChars);

                // Use SwingWorker to perform login logic in the background
                SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
                    @Override
                    protected Boolean doInBackground() {
                        // Simulate login progress with a loop
                        for (int i = 0; i <= 100; i += 2) { // Increment by 2 to fill within 1.2 seconds
                            try {
                                Thread.sleep(12); // Adjust sleep time for the desired duration
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            progressBar.setValue(i);
                        }

                        // Add your actual login logic here
                        return isValidLogin(enteredUsername, enteredPassword);
                    }

                    @Override

                    protected void done() {
                        progressBar.setValue(100); // Set progress to 100%
                        try {
                            if (get()) {
                                getParent().setVisible(false);
                                // If login is successful, show the success message and dispose
                                JOptionPane.showMessageDialog(AdminLoginDialog.this, "Login successful", "Login", JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                            } else {
                                // If login fails, reset progress bar and show an error message
                                progressBar.setValue(0);
                                JOptionPane.showMessageDialog(AdminLoginDialog.this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                };

                worker.execute(); // Start the SwingWorker
            }
        });
        loginPanel.add(loginButton);

        // Add the photoPanel and loginPanel to the mainPanel
        mainPanel.add(photoPanel);
        mainPanel.add(loginPanel);

        getContentPane().add(mainPanel);
        setSize(new Dimension(450, 200));
        setLocationRelativeTo(parent);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Handle the close operation here (e.g., show a confirmation dialog)
                int option = JOptionPane.showConfirmDialog(
                        AdminLoginDialog.this,
                        "Are you sure you want to close the login window?",
                        "Confirm Close",
                        JOptionPane.YES_NO_OPTION);


                if (option == JOptionPane.YES_OPTION) {
                    // Close both the AdminLoginDialog and the main HostelManagementSystem frame
                    dispose();
                    parent.dispose();
                }
                else if (option == JOptionPane.NO_OPTION){
                    shouldDispose = false;

                }
            }
        });

    }

    // Database sign-up logic
    private boolean isValidSignUp(String username, String password) {
        // Replace these with your actual database connection details
        String url = "jdbc:mysql://localhost:3306/hostel_management_system?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String dbPassword = null;

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(AdminLoginDialog.this, "Username and password cannot be empty", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
            String query = "INSERT INTO accounts (username, password) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                shouldDispose = false;
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0; // Returns true if at least one row is affected (account is inserted)

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Database login logic
    private boolean isValidLogin(String username, String password) {
        // Replace these with your actual database connection details
        String url = "jdbc:mysql://localhost:3306/hostel_management_system?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String dbPassword = null;

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
            String query = "SELECT * FROM accounts WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        dispose();
                        // Open the HostelManagementSystem JFrame after a successful login
                        HostelManagementSystem.HostelManagementGUI hostelManagementGUI = new HostelManagementSystem.HostelManagementGUI();
                        return true;
                    } else {
                        return false; // No matching record found
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        } catch (SQLException ex) { // Added catch block for the outer try
            ex.printStackTrace();
            return false;
        }
    }
    public void dispose() {
        if (shouldDispose) {
            super.dispose();
        }
    }

}
//  I added a catch block for the outer try block in both isValidSignUp and isValidLogin methods. This should resolve the compilation error. Remember to handle exceptions appropriately in your actual application, such as showing an error message or logging the exception details.