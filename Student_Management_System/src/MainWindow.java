import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainWindow extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel dashboard;
    private JPanel view;
    private JPanel add;
    private JPanel search;
    private JTable ViewTable;
    private JScrollPane table_scroll;
    private JPanel mainPanel;
    private JPanel AddPanel;
    private JTextField nameField_AddPanel;
    private JTextField ageField_AddPanel;
    private JComboBox GenderBox_AddPanel;
    private JComboBox DepartBox_AddPanel;
    private JTextField GPAField_AddPanel;
    private JButton addButton_AddPanel;
    private JTextField IDField_AddPanel;
    private JLabel IDLabel_AddPanel;
    private JButton logout;
    private JLabel DashboardImage;
    private JPanel Del;
    private JPanel SearchPanel;
    private JComboBox KeyBox_SearchPanel;
    private JTextField KeyField_SearchPanel;
    private JButton SearchButton_SearchPanel;
    private JTable SearchTable;
    private JTable DeleteTable;

    public MainWindow() {
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        StudentDatabase data = new StudentDatabase();

        initializeComboBoxes();

        addButton_AddPanel.addActionListener(e -> addStudentPanel(data));
        setContentPane(mainPanel);

        initTables(data);
        pack();
        setVisible(true);

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login login = new Login();
            }
        });

    }

    private void initializeComboBoxes() {
        GenderBox_AddPanel.addItem("Male");
        GenderBox_AddPanel.addItem("Female");
        GenderBox_AddPanel.addItem("Other");
        DepartBox_AddPanel.addItem("Computer Engineering");
        DepartBox_AddPanel.addItem("Communication Engineering");
        DepartBox_AddPanel.addItem("Electrical Engineering");
        DepartBox_AddPanel.addItem("Mechanical Engineering");
        DepartBox_AddPanel.addItem("Civil Engineering");
        DepartBox_AddPanel.addItem("Biomedical Engineering");

        KeyBox_SearchPanel.addItem("ID Key");
        KeyBox_SearchPanel.addItem("Name Key");
    }

    private void initTables(StudentDatabase data) {
        String[] cols = {"ID", "Name", "Age", "Gender", "Department", "GPA"};
        DefaultTableModel ViewModel = new DefaultTableModel(cols, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        DefaultTableModel SearchModel = new DefaultTableModel(cols, 0);
        DefaultTableModel DeleteModel = new DefaultTableModel(cols, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        // I made it view and delete tables are edit proof
        // Note -> search table is editable because it will have the update functionality

        ViewTable.setModel(ViewModel);
        SearchTable.setModel(SearchModel);
        DeleteTable.setModel(DeleteModel);

        List<Student> studentList = data.getAllStudents();
        for (Student s : studentList) {
            ViewModel.addRow(new Object[]{
                    s.getStudentId(),
                    s.getFname(),
                    s.getAge(),
                    s.getGender(),
                    s.getDepartment(),
                    s.getGPA()
            });
            SearchModel.addRow(new Object[]{
                    s.getStudentId(),
                    s.getFname(),
                    s.getAge(),
                    s.getGender(),
                    s.getDepartment(),
                    s.getGPA()
            });
            DeleteModel.addRow(new Object[]{
                    s.getStudentId(),
                    s.getFname(),
                    s.getAge(),
                    s.getGender(),
                    s.getDepartment(),
                    s.getGPA()
            });
        }

        // search logic
        String key = KeyField_SearchPanel.getText();
        SearchButton_SearchPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchModel.setRowCount(0); // clear previous results
                String selectedKey = KeyBox_SearchPanel.getSelectedItem().toString();
                String keyText = KeyField_SearchPanel.getText().trim();

                if (selectedKey.equals("ID Key")) {
                    try {
                        int id = Integer.parseInt(keyText);
                        Student s = data.searchStudentById(id);
                        if (s != null) {
                            Object[] row = {s.getStudentId(),
                                    s.getFname(),
                                    s.getAge(),
                                    s.getGender(),
                                    s.getDepartment(),
                                    s.getGPA()};
                            SearchModel.addRow(row);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "No student found with that ID.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid numeric ID.");
                    }
                }
                else if (selectedKey.equals("Name Key")) {
                    List<Student> results = data.searchStudentByName(keyText);
                    if (results != null && !results.isEmpty()) {
                        for (Student s : results) {
                            Object[] row = {s.getStudentId(),
                                    s.getFname(),
                                    s.getAge(),
                                    s.getGender(),
                                    s.getDepartment(),
                                    s.getGPA()};
                            SearchModel.addRow(row);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "No student found with that name.");
                    }
                }
            }
        });

        deleteLogic(data, DeleteModel);

    }
    private void deleteLogic(StudentDatabase data, DefaultTableModel DeleteModel) {
        DeleteTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = DeleteTable.rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        showDeleteConfirmation(DeleteModel, row, data);
                    }
                }
            }
        });
    }
    private void showDeleteConfirmation(DefaultTableModel DeleteModel, int row, StudentDatabase data) {
        int id = (Integer) DeleteModel.getValueAt(row, 0);
        String name = (String) DeleteModel.getValueAt(row, 1);
        String department = (String) DeleteModel.getValueAt(row, 3);

        String message = "Are you sure you want to delete this record?\n\n" + "ID: " + id + "\nName: " + name + "\nDepartment: " + department;

        int result = JOptionPane.showConfirmDialog(
                this,
                message,
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (result == JOptionPane.YES_OPTION) {
            data.deleteStudent(id);
            refreshTables(data);
            JOptionPane.showMessageDialog(this,
                    "Record deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void addStudentPanel(StudentDatabase data) {
        try {
            String name = nameField_AddPanel.getText();
            int age = Integer.parseInt(ageField_AddPanel.getText());
            String gender = GenderBox_AddPanel.getSelectedItem().toString();
            String department = DepartBox_AddPanel.getSelectedItem().toString();
            float gpa = Float.parseFloat(GPAField_AddPanel.getText());
            int id = Integer.parseInt(IDField_AddPanel.getText());

            Student s1 = new Student(id, name, age, gender, department, gpa);

            if (!data.addStudent(s1)) {
                JOptionPane.showMessageDialog(this, "Student not added (Student id already exists)",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, "Student added successfully",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            clearAddStudentsFields();

            refreshTables(data);
        }
        catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for Age, ID, and GPA",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch(IllegalArgumentException e2) {
            JOptionPane.showMessageDialog(this, e2.getMessage(),
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearAddStudentsFields() {
        nameField_AddPanel.setText("");
        ageField_AddPanel.setText("");
        IDField_AddPanel.setText("");
        GPAField_AddPanel.setText("");
        GenderBox_AddPanel.setSelectedIndex(0);
        DepartBox_AddPanel.setSelectedIndex(0);
    }

    private void refreshTables(StudentDatabase data) {
        DefaultTableModel view = (DefaultTableModel) ViewTable.getModel();
        // DefaultTableModel search = (DefaultTableModel) ViewTable.getModel(); -> updates intseld everytime the button is used
        DefaultTableModel delete = (DefaultTableModel) DeleteTable.getModel();

        view.setRowCount(0);
        delete.setRowCount(0);

        List<Student> studentList = data.getAllStudents();
        for (Student s : studentList) {
            view.addRow(new Object[]{
                    s.getStudentId(),
                    s.getFname(),
                    s.getAge(),
                    s.getGender(),
                    s.getDepartment(),
                    s.getGPA()
            });
            delete.addRow(new Object[]{
                    s.getStudentId(),
                    s.getFname(),
                    s.getAge(),
                    s.getGender(),
                    s.getDepartment(),
                    s.getGPA()
            });
        }
    }

}