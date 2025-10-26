import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
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

    public MainWindow() {

        ImageIcon image = new ImageIcon("admin-icon.jpg");
        setIconImage(image.getImage());
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(mainPanel);
        pack();
        setVisible(true);

        StudentDatabase data = initTable();


    }

    private StudentDatabase initTable() {
        String[] cols = {"ID", "Name", "Age", "Gender", "Department", "GPA"};
        DefaultTableModel studentModel = new DefaultTableModel(cols, 0);
        students.setModel(studentModel);

        StudentDatabase data = new StudentDatabase();

        List<Student> students = data.getAllStudents();
        for (Student s : students) {
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
