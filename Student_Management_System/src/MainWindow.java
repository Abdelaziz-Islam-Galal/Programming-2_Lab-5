import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private JPanel mainPanel;
    private JButton addStudentBtn;
    private JButton viewStudentsBtn;
    private JButton deleteStudentBtn;
    private JButton updateStudentBtn;
    private JButton searchStudentBtn;
    private JButton logoutBtn;
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

    public MainWindow() {

        ImageIcon image = new ImageIcon("admin-icon.jpg");
        setIconImage(image.getImage());
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(mainPanel);
        pack();
        setVisible(true);


        addStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStudentWindow();
                dispose();
            }
        });


        viewStudentsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewStudentsWindow();
                dispose();
            }
        });


        deleteStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteStudentWindow();
                dispose();
            }
        });


        updateStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateStudentWindow();
                dispose();
            }
        });


        searchStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchStudentWindow();
                dispose();
            }
        });


        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                dispose();
            }
        });
    }
}
