import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchStudentWindow {
    private JComboBox KeyBox;
    private JTextField KeyField;
    private JButton SearchButton;
    private JPanel SearchPanel;
    private JTable students;

    public SearchStudentWindow() {
        KeyBox.addItem("ID Key");
        KeyBox.addItem("Name Key");

        String[] columns = {"ID", "Name", "Age", "Gender", "Department", "GPA"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        students.setModel(model);

        String key = KeyField.getText();

        StudentDatabase s1=new StudentDatabase();
        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0); // clear previous results
                String selectedKey = KeyBox.getSelectedItem().toString();
                String keyText = KeyField.getText().trim();

                if (selectedKey.equals("ID Key")) {
                    try {
                        int id = Integer.parseInt(keyText);
                        Student s = s1.searchStudentById(id);
                        if (s != null) {
                            Object[] row = {s.getStudentId(),
                                    s.getFname(),
                                    s.getAge(),
                                    s.getGender(),
                                    s.getDepartment(),
                                    s.getGPA()};
                            model.addRow(row);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "No student found with that ID.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid numeric ID.");
                    }
                }
                else if (selectedKey.equals("Name Key")) {
                    List<Student> results = s1.searchStudentByName(keyText);
                    if (results != null && !results.isEmpty()) {
                        for (Student s : results) {
                            Object[] row = {s.getStudentId(),
                                    s.getFname(),
                                    s.getAge(),
                                    s.getGender(),
                                    s.getDepartment(),
                                    s.getGPA()};
                            model.addRow(row);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "No student found with that name.");
                    }
                }
            }
        });
    }
    public JPanel getSearchPanel() {
        return SearchPanel;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Search Student");
        frame.setContentPane(new SearchStudentWindow().getSearchPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
