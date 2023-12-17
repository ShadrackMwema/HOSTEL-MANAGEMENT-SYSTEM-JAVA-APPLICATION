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

import java.awt.event.ActionListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class complaints {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hostel_management_system?zeroDateTimeBehavior=convertToNull";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = null;


    public complaints() {
        // Create a new JFrame to display the student table
        JFrame studentFrame = new JFrame("Complaints Details");
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
            String query = "SELECT Name, PhoneNumber, Date, Complaints, Status, RegNo FROM Complaints";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String[] rowData = new String[]{
                        resultSet.getString("Name"),
                        resultSet.getString("RegNo"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getString("Date"),
                        resultSet.getString("Complaints"),
                        resultSet.getString("Status")
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
        return new String[]{"Name", "PhoneNumber", "Date", "Complaints", "Status", "RegNo"};
    }


    private static JPanel createButtonPanel(JTable studentTable) {
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Add Record", e -> displayAddStudentForm(studentTable));
        addButton(buttonPanel, "Edit Record", e -> editSelectedStudent(studentTable));
        addButton(buttonPanel, "Delete Record", e -> deleteSelectedStudent(studentTable));
        addButton(buttonPanel, "Save Record", e -> saveDetailsToDatabase(studentTable));
        addButton(buttonPanel, "Print", e -> printStudentDetails(studentTable));
        addButton(buttonPanel, "Generate Report", e -> generateReport(studentTable));
        return buttonPanel;
    }

    private static void generateReport(JTable studentTable) {
        try {
            // Create a dataset from the database
            DefaultCategoryDataset dataset = createDataset();

            // Create the chart based on the dataset
            JFreeChart chart = ChartFactory.createBarChart(
                    "Complaints Report", // Chart title
                    "Name",           // X-axis label
                    "Complaints",              // Y-axis label
                    dataset,              // Dataset
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );

            // Change the chart type to column chart
            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            plot.setOrientation(PlotOrientation.VERTICAL);

            // Display the chart in a JFrame
            JFrame chartFrame = new JFrame("Complaints Report");
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

    private static DefaultCategoryDataset createDataset() throws SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            String query = "SELECT Complaints, Name FROM Complaints";
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    String name = resultSet.getString("Name");
                    int regNo = resultSet.getInt("Complaints");

                    // Add data to the dataset
                    dataset.addValue(regNo, "Complaints", name);
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

            printerJob.setJobName("Print records Details");

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
        JFrame editStudentFrame = new JFrame("Edit Complaints");
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
        formPanel.add(new JLabel("Date:"));
        formPanel.add(parentPhoneNumberField);
        formPanel.add(new JLabel("Complaints:"));
        formPanel.add(roomField);
        formPanel.add(new JLabel("Status:"));
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
        JFrame addStudentFrame = new JFrame("Add Complaints");
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
        formPanel.add(new JLabel("Date:"));
        formPanel.add(parentPhoneNumberField);
        formPanel.add(new JLabel("Complaints:"));
        formPanel.add(roomField);
        formPanel.add(new JLabel("Status:"));
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
            String deleteQuery = "DELETE FROM Complaints";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.executeUpdate();

            // Insert new records
            String insertQuery = "INSERT INTO Complaints (Name, PhoneNumber, Date, Complaints, Status, RegNo) VALUES (?, ?, ?, ?, ?, ?)";
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