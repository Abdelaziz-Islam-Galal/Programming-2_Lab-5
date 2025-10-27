import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchAndUpdateLogic {
    private JComboBox KeyBox_SearchPanel;
    private JTextField KeyField_SearchPanel;
    private JButton SearchButton_SearchPanel;
    public SearchAndUpdateLogic(JComboBox keyBox_SearchPanel, JTextField keyField_SearchPanel, JButton searchButton_SearchPanel, DefaultTableModel searchModel, StudentDatabase data) {
        this.KeyBox_SearchPanel = keyBox_SearchPanel;
        this.KeyField_SearchPanel = keyField_SearchPanel;
        this.SearchButton_SearchPanel = searchButton_SearchPanel;
        initializeComboBoxes();
        searchLogic(searchModel, data);
    }

    private void searchLogic(DefaultTableModel SearchModel, StudentDatabase data){
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
    }

    private void initializeComboBoxes() {
        KeyBox_SearchPanel.addItem("ID Key");
        KeyBox_SearchPanel.addItem("Name Key");
    }
}
