import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class HostelManagementSystem extends JFrame {

    private HostelManagementGUI hostelManagementGUI;

    public HostelManagementSystem() {
        setTitle("Hostel Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setUndecorated(true);

        // Load background image
        BufferedImage backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File("/home/shad/Downloads/PROJECTPICS/Hostel.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set a custom JPanel with a background image
        BufferedImage finalBackgroundImage = backgroundImage;
        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (finalBackgroundImage != null) {
                    g.drawImage(finalBackgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        });

        // Add your GUI components here

        // Example: Add a JLabel

        // Make the frame visible
        setVisible(true);


        // Show the admin login dialog when the main frame is created
        AdminLoginDialog adminLoginDialog = new AdminLoginDialog(this);
        adminLoginDialog.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(HostelManagementSystem::new);
    }

public static class HostelManagementGUI extends JFrame {

    // Define your student data (replace this with your actual data)
    private Object[][] studentData = {

            // Add more rows as needed
    };

    // Define column names
    private String[] columnNames = {"Names", "Reg No", "Phone Number", "Parent/Guardian Phone.No", "Room", "Block"};

    public HostelManagementGUI() {
        setTitle("Hostel Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load background image
        BufferedImage backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File("/home/shad/Downloads/PROJECTPICS/Hostel.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a JSplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        // Create a JPanel for the navigation panel
        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new BorderLayout());
        JLabel navigationLabel = new JLabel("Navigation Panel");
        navigationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        navigationPanel.add(navigationLabel, BorderLayout.NORTH);
        navigationPanel.setBackground(new Color(135, 147, 120));


        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Add buttons to the button panel with the same width

        addButton(buttonPanel, "Manage Students", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new manageStudents();
            }
        });
        addButton(buttonPanel, "Manage Room", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and show the RoomManager
                new RoomManager();
            }
        });

        addButton(buttonPanel, "Manage CareTakers",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and show the RoomManager
                new ManageCaretakers();
            }
        });
        addButton(buttonPanel, "Manage attendance",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and show the RoomManager
                new attendance();
            }
        });

        addButton(buttonPanel, "Manage complaints",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and show the RoomManager
                new complaints();
            }
        });

        addButton(buttonPanel, "Manage visitorlog",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and show the RoomManager
                new visitorlog();
            }
        });


        addButton(buttonPanel, "Manage leaverquests",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and show the RoomManager
                new leaverrequests();
            }
        });

        addButton(buttonPanel, "Manage messmenu",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and show the RoomManager
                new messmenu();
            }
        });
        addButton(buttonPanel, "Manage Facilities",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and show the RoomManager
                new Facilities();
            }
        });
        addButton(buttonPanel, "Manage Security",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and show the RoomManager
                new Security();
            }
        });



        // Add the button panel to the navigation panel
        navigationPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add the navigation panel to the left side of the split pane
        splitPane.setLeftComponent(navigationPanel);

        // Set a custom JPanel with a background image on the right side of the split pane
        BufferedImage finalBackgroundImage = backgroundImage;
        splitPane.setRightComponent(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (finalBackgroundImage != null) {
                    // Calculate the dimensions for the image to cover the right 2/3 of the split pane
                    int width = getWidth();
                    int height = getHeight();
                    int x = 0;
                    int y = 0;
                    g.drawImage(finalBackgroundImage, x, y, width, height, this);
                }
            }
        });

        // Add the split pane to the frame
        setContentPane(splitPane);

        // Make the frame visible
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addButton(Container container, String text) {
        addButton(container, text, null);
    }

    private void addButton(Container container, String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set a maximum size to ensure all buttons have the same width
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));

        // Add ActionListener to the button if provided
        if (actionListener != null) {
            button.addActionListener(actionListener);
        }

        container.add(button);
    }


}}
