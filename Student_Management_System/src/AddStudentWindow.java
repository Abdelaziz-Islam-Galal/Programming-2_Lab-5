import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentWindow {

    private JPanel AddPanel;
    private JTextField nameField;
    private JTextField ageField;
    private JComboBox GenderBox;
    private JComboBox DepartBox;
    private JTextField GPAField;
    private JButton addButton;
    private JTextField IDField;
    private JLabel IDLabel;

    public AddStudentWindow() {

        GenderBox.addItem("Male");
        GenderBox.addItem("Female");
        GenderBox.addItem("Other");

        DepartBox.addItem("Computer Engineering");
        DepartBox.addItem("Communication Engineering");
        DepartBox.addItem("Electrical Engineering");
        DepartBox.addItem("Mechanical Engineering");
        DepartBox.addItem("Civil Engineering");
        DepartBox.addItem("Biomedical Engineering");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = GenderBox.getSelectedItem().toString();
                String department = DepartBox.getSelectedItem().toString();
                float gpa = Float.parseFloat(GPAField.getText());
                int id = Integer.parseInt(IDField.getText());

                Validation validation = new Validation();

                if (!validation.isValidString(name)) {
                    JOptionPane.showMessageDialog(AddPanel,"Invalid Name", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
                if(!validation.isValidAge(age))
                {
                    JOptionPane.showMessageDialog(AddPanel,"Invalid age", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
                if(!validation.isValidInt(id))
                {
                    JOptionPane.showMessageDialog(AddPanel,"Invalid ID", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
                if (!validation.isValidGPA(gpa))
                {
                    JOptionPane.showMessageDialog(AddPanel,"Invalid GPA", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
                Student s1=new Student(id,name,age,gender,department,gpa);
                StudentDatabase sdb=new StudentDatabase();
                if(!sdb.addStudent(s1))
                    {
                    JOptionPane.showMessageDialog(AddPanel,"Student not added (Student id already exists)", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                JOptionPane.showMessageDialog(AddPanel,"Student added successfully", "Input Error", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Add Student");
        frame.setContentPane(new AddStudentWindow().AddPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}