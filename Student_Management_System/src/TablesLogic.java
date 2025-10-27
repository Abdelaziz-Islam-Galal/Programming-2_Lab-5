import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TablesLogic {
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private StudentDatabase data;

    public TablesLogic(JTable table1, JTable table2, JTable table3, StudentDatabase data) {
        this.table1 = table1;
        this.table2 = table2;
        this.table3 = table3;
        this.data = data;
        initTable(table1, data);
        initTable(table2, data);
        initTable(table3, data);
    }

    private void initTable(JTable table, StudentDatabase data) {
        String[] cols = {"ID", "Name", "Age", "Gender", "Department", "GPA"};
        DefaultTableModel model;
        model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };

        table.setModel(model);

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

    public void refreshTable() {
        DefaultTableModel model1 = (DefaultTableModel) table1.getModel();
        DefaultTableModel model2 = (DefaultTableModel) table2.getModel();

        model1.setRowCount(0);
        model2.setRowCount(0);

        List<Student> studentList = data.getAllStudents();
        for (Student s : studentList) {
            model1.addRow(new Object[]{
                    s.getStudentId(),
                    s.getFname(),
                    s.getAge(),
                    s.getGender(),
                    s.getDepartment(),
                    s.getGPA()
            });
            model2.addRow(new Object[]{
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
