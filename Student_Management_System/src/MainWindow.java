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
    private JButton UpdateButton_SearchPanel;

    public MainWindow() {
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        StudentDatabase data = new StudentDatabase();
        TablesLogic tables = new TablesLogic( ViewTable,  DeleteTable, SearchTable, data);

        AddStudentLogic addStudentLogic = new AddStudentLogic(
                AddPanel,
                GenderBox_AddPanel,
                DepartBox_AddPanel,
                ViewTable,
                nameField_AddPanel,
                ageField_AddPanel,
                GPAField_AddPanel,
                IDField_AddPanel,
                tables
        );
        SearchAndUpdateLogic searchAndUpdateLogic = new SearchAndUpdateLogic(
                KeyBox_SearchPanel,
                KeyField_SearchPanel,
                SearchButton_SearchPanel,
                (DefaultTableModel) SearchTable.getModel(),
                data
        );

        DeleteLogic deleteLogic = new DeleteLogic(DeleteTable, data, this, tables);

        addButton_AddPanel.addActionListener(e -> addStudentLogic.addStudent(data));
        setContentPane(mainPanel);

        pack();
        setVisible(true);

        new DashboardLogic(logout, this);

    }

}