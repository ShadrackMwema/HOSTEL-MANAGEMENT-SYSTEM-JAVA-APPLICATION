

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
        import javax.swing.table.DefaultTableModel;
        import java.awt.*;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;
        import java.sql.SQLException;
        import javax.print.attribute.HashPrintRequestAttributeSet;
        import javax.print.attribute.PrintRequestAttributeSet;
        import javax.print.attribute.standard.Copies;
        import java.awt.print.*;

        import java.sql.*;
        import java.sql.ResultSet;

        import java.util.ArrayList;
        import java.util.List;

        import java.awt.event.ActionListener;

public class Security {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hostel_management_system?zeroDateTimeBehavior=convertToNull";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = null;


    public Security() {
        // Create a new JFrame to display the student table
        JFrame studentFrame = new JFrame("Students Details");
        studentFrame.setSize(800, 400);

        // Create a JTable with the student data
        JTable studentTable = new JTable(new DefaultTableModel(getInitialData(), getColumnNames()));

        // Add the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(studentTable);

        // Create a panel for the buttons at the bottom
        JPanel buttonPanel = createButtonPanel(studentTable);

        // Add the button panel and the JScrollPane to the JFrame
        studentFrame.add(scrollPane, BorderLayout.CENTER);
        studentFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Make the JFrame visible
        studentFrame.setLocationRelativeTo(null);
        studentFrame.setVisible(true);



    }
    private static String[][] getInitialData() {
        List<String[]> data = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "SELECT Name, PhoneNumber, Salary, No_Of_Rooms, Block, ID_No FROM Security";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String[] rowData = new String[]{
                        resultSet.getString("Name"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getString("Salary"),
                        resultSet.getString("No_Of_Rooms"),
                        resultSet.getString("Block"),
                        resultSet.getString("ID_No")
                };
                data.add(rowData);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data.toArray(new String[0][0]);
    }

    private static String[] getColumnNames() {
        return new String[]{"Name", "PhoneNumber", "Salary", "No_Of_Rooms", "Block", "ID_No"};
    }


    private static JPanel createButtonPanel(JTable studentTable) {
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Add Student", e -> displayAddStudentForm(studentTable));
        addButton(buttonPanel, "Edit Student", e -> editSelectedStudent(studentTable));
        addButton(buttonPanel, "Delete Student", e -> deleteSelectedStudent(studentTable));
        addButton(buttonPanel, "Save Details", e -> saveDetailsToDatabase(studentTable));
        addButton(buttonPanel, "Print", e -> printStudentDetails(studentTable));
        addButton(buttonPanel, "Generate Report", e -> generateReport(studentTable));
        return buttonPanel;
    }
    private static void generateReport(JTable studentTable) {
        try {
            // Create a dataset from the database
            DefaultPieDataset dataset = createDataset();

            // Create the chart based on the dataset
            JFreeChart chart = ChartFactory.createPieChart(
                    "Security Report", // Chart title
                    dataset,              // Dataset
                    true,
                    true,
                    false
            );

            // Display the chart in a JFrame
            JFrame chartFrame = new JFrame("Security Report");
            chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            chartFrame.setSize(800, 600);

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(750, 500));
            chartFrame.setContentPane(chartPanel);

            // Set the JFrame to be visible
            chartFrame.setLocationRelativeTo(null);
            chartFrame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error generating report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static DefaultPieDataset createDataset() throws SQLException {
        DefaultPieDataset dataset = new DefaultPieDataset();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            String query = "SELECT Name, Salary FROM Security";
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    String name = resultSet.getString("Name");
                    int regNo = resultSet.getInt("Salary");

                    dataset.setValue(name, regNo);
                }
            }
        }

        return dataset;
    }
    private static void printStudentDetails(JTable studentTable) {
        try {
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            PageFormat pageFormat = printerJob.defaultPage();
            pageFormat.setOrientation(PageFormat.LANDSCAPE);

            printerJob.setJobName("Print Student Details");

            printerJob.setPrintable(new Printable() {
                @Override
                public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                    if (pageIndex > 0) {
                        return Printable.NO_SUCH_PAGE;
                    }

                    Graphics2D g2d = (Graphics2D) graphics;
                    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                    // Print the entire JTable using its existing model
                    studentTable.setSize((int) pageFormat.getImageableWidth(), Integer.MAX_VALUE);
                    studentTable.printAll(g2d);

                    return Printable.PAGE_EXISTS;
                }
            }, pageFormat);

            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            printRequestAttributeSet.add(new Copies(1)); // Set the number of copies

            if (printerJob.printDialog(printRequestAttributeSet)) {
                printerJob.print(printRequestAttributeSet);
            }

        } catch (PrinterException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while printing: " + ex.getMessage(), "Print Error", JOptionPane.ERROR_MESSAGE);
        }
    }




    private static void editSelectedStudent(JTable studentTable) {
        int selectedRow = studentTable.getSelectedRow();

        if (selectedRow != -1) {
            String[] rowData = getSelectedRowData(studentTable, selectedRow);
            displayEditStudentForm(studentTable, selectedRow, rowData);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a student to edit.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static String[] getSelectedRowData(JTable studentTable, int selectedRow) {
        String[] rowData = new String[getColumnNames().length];
        for (int i = 0; i < getColumnNames().length; i++) {
            rowData[i] = studentTable.getValueAt(selectedRow, i).toString();
        }
        return rowData;
    }


    private static void deleteSelectedStudent(JTable studentTable) {
        int selectedRow = studentTable.getSelectedRow();

        if (selectedRow != -1) {
            int option = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete the selected student?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
                model.removeRow(selectedRow);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a student to delete.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static void addButton(JPanel panel, String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        panel.add(button);
    }

    private static void displayEditStudentForm(JTable studentTable, int selectedRow, String[] rowData) {
        JFrame editStudentFrame = new JFrame("Edit Student");
        editStudentFrame.setSize(400, 300);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2));

        JTextField nameField = new JTextField(rowData[0]);
        JTextField regNoField = new JTextField(rowData[1]);
        JTextField phoneNumberField = new JTextField(rowData[2]);
        JTextField parentPhoneNumberField = new JTextField(rowData[3]);
        JTextField roomField = new JTextField(rowData[4]);
        JTextField blockField = new JTextField(rowData[5]);

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Reg No:"));
        formPanel.add(regNoField);
        formPanel.add(new JLabel("Phone Number:"));
        formPanel.add(phoneNumberField);
        formPanel.add(new JLabel("Parent/Guardian Phone.No:"));
        formPanel.add(parentPhoneNumberField);
        formPanel.add(new JLabel("Room:"));
        formPanel.add(roomField);
        formPanel.add(new JLabel("Block:"));
        formPanel.add(blockField);

        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> {
            String[] updatedData = {
                    nameField.getText(),
                    regNoField.getText(),
                    phoneNumberField.getText(),
                    parentPhoneNumberField.getText(),
                    roomField.getText(),
                    blockField.getText()
            };

            DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
            model.removeRow(selectedRow);
            model.insertRow(selectedRow, updatedData);

            editStudentFrame.dispose();
        });

        cancelButton.addActionListener(e -> editStudentFrame.dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        editStudentFrame.add(formPanel, BorderLayout.CENTER);
        editStudentFrame.add(buttonPanel, BorderLayout.SOUTH);

        editStudentFrame.setLocationRelativeTo(null);
        editStudentFrame.setVisible(true);
    }

    private static void displayAddStudentForm(JTable studentTable) {
        JFrame addStudentFrame = new JFrame("Add Student");
        addStudentFrame.setSize(400, 300);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2));

        JTextField nameField = new JTextField();
        JTextField regNoField = new JTextField();
        JTextField phoneNumberField = new JTextField();
        JTextField parentPhoneNumberField = new JTextField();
        JTextField roomField = new JTextField();
        JTextField blockField = new JTextField();

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Reg No:"));
        formPanel.add(regNoField);
        formPanel.add(new JLabel("Phone Number:"));
        formPanel.add(phoneNumberField);
        formPanel.add(new JLabel("Parent/Guardian Phone.No:"));
        formPanel.add(parentPhoneNumberField);
        formPanel.add(new JLabel("Room:"));
        formPanel.add(roomField);
        formPanel.add(new JLabel("Block:"));
        formPanel.add(blockField);

        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> {
            String[] rowData = {
                    nameField.getText(),
                    regNoField.getText(),
                    phoneNumberField.getText(),
                    parentPhoneNumberField.getText(),
                    roomField.getText(),
                    blockField.getText()
            };

            DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
            model.addRow(rowData);

            addStudentFrame.dispose();
        });

        cancelButton.addActionListener(e -> addStudentFrame.dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        addStudentFrame.add(formPanel, BorderLayout.CENTER);
        addStudentFrame.add(buttonPanel, BorderLayout.SOUTH);

        addStudentFrame.setLocationRelativeTo(null);
        addStudentFrame.setVisible(true);
    }
    private static void saveDetailsToDatabase(JTable studentTable) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Delete all existing records
            String deleteQuery = "DELETE FROM Security";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.executeUpdate();

            // Insert new records
            String insertQuery = "INSERT INTO Security (Name, PhoneNumber, Salary, No_Of_Rooms, Block, ID_No) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

            DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
            int rowCount = model.getRowCount();

            try {
                for (int i = 0; i < rowCount; i++) {
                    insertStatement.setString(1, model.getValueAt(i, 0).toString());
                    insertStatement.setString(2, model.getValueAt(i, 2).toString());
                    insertStatement.setString(3, model.getValueAt(i, 3).toString());
                    insertStatement.setString(4, model.getValueAt(i, 4).toString());
                    insertStatement.setString(5, model.getValueAt(i, 5).toString());
                    insertStatement.setString(6, model.getValueAt(i, 1).toString());

                    insertStatement.executeUpdate();
                }

                JOptionPane.showMessageDialog(null, "Details saved to database.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } finally {
                if (deleteStatement != null) {
                    deleteStatement.close();
                }
                if (insertStatement != null) {
                    insertStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving details to database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }




}


