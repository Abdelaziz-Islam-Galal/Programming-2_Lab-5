import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainWindow extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel dashboard;
    private JPanel view;
    private JPanel add;
    private JPanel search;
    private JTable students;
    private JLabel id;
    private JLabel age;
    private JLabel gender;
    private JLabel department;
    private JLabel gpa;
    private JLabel name;
    private JScrollPane table_scroll;
    private JPanel mainPanel;
    private JPanel AddPanel;
    private JTextField nameField;
    private JTextField ageField;
    private JComboBox GenderBox;
    private JComboBox DepartBox;
    private JTextField GPAField;
    private JButton addButton;
    private JTextField IDField;
    private JLabel IDLabel;
    private JButton logout;

    public MainWindow() {
        ImageIcon image = new ImageIcon("admin-icon.jpg");
        setIconImage(image.getImage());
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Initialize the combo boxes
        initializeComboBoxes();
        // Add button action listener
        addButton.addActionListener(e -> addStudent());
        setContentPane(mainPanel);
        // Initialize the table
        initTable();
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
        GenderBox.addItem("Male");
        GenderBox.addItem("Female");
        GenderBox.addItem("Other");
        DepartBox.addItem("Computer Engineering");
        DepartBox.addItem("Communication Engineering");
        DepartBox.addItem("Electrical Engineering");
        DepartBox.addItem("Mechanical Engineering");
        DepartBox.addItem("Civil Engineering");
        DepartBox.addItem("Biomedical Engineering");
    }

    private void addStudent() {
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = GenderBox.getSelectedItem().toString();
            String department = DepartBox.getSelectedItem().toString();
            float gpa = Float.parseFloat(GPAField.getText());
            int id = Integer.parseInt(IDField.getText());
            Validation validation = new Validation();
            if (!validation.isValidString(name)) {
                JOptionPane.showMessageDialog(this, "Invalid Name", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!validation.isValidAge(age)) {
                JOptionPane.showMessageDialog(this, "Invalid age", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!validation.isValidInt(id)) {
                JOptionPane.showMessageDialog(this, "Invalid ID", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!validation.isValidGPA(gpa)) {
                JOptionPane.showMessageDialog(this, "Invalid GPA", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Student s1 = new Student(id, name, age, gender, department, gpa);
            StudentDatabase sdb = new StudentDatabase();

            if (!sdb.addStudent(s1)) {
                JOptionPane.showMessageDialog(this, "Student not added (Student id already exists)",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, "Student added successfully",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            clearFields();

            refreshTable();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for Age, ID, and GPA",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        IDField.setText("");
        GPAField.setText("");
        GenderBox.setSelectedIndex(0);
        DepartBox.setSelectedIndex(0);
    }

    private void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) students.getModel();
        model.setRowCount(0);
        StudentDatabase data = new StudentDatabase();
        List<Student> studentList = data.getAllStudents();
        for (Student s : studentList) {
            model.addRow(new Object[]{
                    s.getStudentId(),
                    s.getFname(),
                    s.getAge(),
                    s.getGender(),
                    s.getDepartment(),
                    s.getGPA()
            });
        }
    }

    private StudentDatabase initTable() {
        String[] cols = {"ID", "Name", "Age", "Gender", "Department", "GPA"};
        DefaultTableModel studentModel = new DefaultTableModel(cols, 0);
        students.setModel(studentModel);

        StudentDatabase data = new StudentDatabase();

        List<Student> studentList = data.getAllStudents();
        for (Student s : studentList) {
            studentModel.addRow(new Object[]{
                    s.getStudentId(),
                    s.getFname(),
                    s.getAge(),
                    s.getGender(),
                    s.getDepartment(),
                    s.getGPA()
            });
        }
        return data;
    }
}