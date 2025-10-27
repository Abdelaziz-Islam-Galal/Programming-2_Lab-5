import javax.swing.*;

public class AddStudentLogic {
    private JComboBox GenderBox_AddPanel;
    private JComboBox DepartBox_AddPanel;
    private JTable table;
    private JTextField nameField_AddPanel;
    private JTextField ageField_AddPanel;
    private JTextField GPAField_AddPanel;
    private JTextField IDField_AddPanel;
    private JPanel panel;
    private TablesLogic TablesLogic;

    public AddStudentLogic(JPanel panel, JComboBox genderBox_AddPanel, JComboBox departBox_AddPanel,
                           JTable table, JTextField nameField_AddPanel,
                           JTextField ageField_AddPanel, JTextField GPAField_AddPanel,
                           JTextField IDField_AddPanel, TablesLogic TablesLogic) {
        this.panel = panel;
        this.GenderBox_AddPanel = genderBox_AddPanel;
        this.DepartBox_AddPanel = departBox_AddPanel;
        this.table = table;
        this.nameField_AddPanel = nameField_AddPanel;
        this.ageField_AddPanel = ageField_AddPanel;
        this.GPAField_AddPanel = GPAField_AddPanel;
        this.IDField_AddPanel = IDField_AddPanel;
        this.TablesLogic = TablesLogic;

        initializeComboBoxes();
    }

    public void addStudent(StudentDatabase data) {
        try {
            String name = nameField_AddPanel.getText();
            int age = Integer.parseInt(ageField_AddPanel.getText());
            String gender = GenderBox_AddPanel.getSelectedItem().toString();
            String department = DepartBox_AddPanel.getSelectedItem().toString();
            float gpa = Float.parseFloat(GPAField_AddPanel.getText());
            int id = Integer.parseInt(IDField_AddPanel.getText());

            Student s1 = new Student(id, name, age, gender, department, gpa);

            if (!data.addStudent(s1)) {
                JOptionPane.showMessageDialog(panel, "Student not added (Student id already exists)",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(panel, "Student added successfully",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            clearAddStudentsFields();

            TablesLogic.refreshTable();
        }
        catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(panel, "Please enter valid numbers for Age, ID, and GPA",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch(IllegalArgumentException e2) {
            JOptionPane.showMessageDialog(panel, e2.getMessage(),
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
    }
}
