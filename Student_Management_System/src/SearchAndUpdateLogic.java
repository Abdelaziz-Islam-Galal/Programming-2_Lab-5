import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SearchAndUpdateLogic {
    private JComboBox KeyBox_SearchPanel;
    private JTextField KeyField_SearchPanel;
    private JButton SearchButton_SearchPanel;
    private JTable SearchTable;
    private JButton UpdateButton_SearchPanel;
    private JTextField updateName;
    private JLabel ageLabel;
    private JTextField updateAge;
    private JComboBox updateGenderComboBox;
    private JComboBox updateDepartComboBox;
    private JTextField updateGpa;
    private JTextField updateId;
    private TablesLogic tables;

    public SearchAndUpdateLogic(
            TablesLogic tables,
            JTable SearchTable,
            JComboBox keyBox_SearchPanel,
            JTextField keyField_SearchPanel,
            JButton searchButton_SearchPanel,
            JButton updateButton_SearchPanel,
            JTextField updateName,
            JTextField updateAge,
            JComboBox updateGenderComboBox,
            JComboBox updateDepartComboBox,
            JTextField updateGpa,
            JTextField updateId,
            DefaultTableModel searchModel,
            StudentDatabase data) {

        this.tables = tables;
        this.SearchTable = SearchTable;
        this.KeyBox_SearchPanel = keyBox_SearchPanel;
        this.KeyField_SearchPanel = keyField_SearchPanel;
        this.SearchButton_SearchPanel = searchButton_SearchPanel;
        this.UpdateButton_SearchPanel = updateButton_SearchPanel;
        this.updateName = updateName;
        this.updateAge = updateAge;
        this.updateGenderComboBox = updateGenderComboBox;
        this.updateDepartComboBox = updateDepartComboBox;
        this.updateGpa = updateGpa;
        this.updateId = updateId;

        initializeComboBoxes();
        searchLogic(searchModel, data);
    }

    private void initializeComboBoxes() {
        KeyBox_SearchPanel.addItem("ID Key");
        KeyBox_SearchPanel.addItem("Name Key");
        updateGenderComboBox.addItem("Male");
        updateGenderComboBox.addItem("Female");
        updateGenderComboBox.addItem("Other");
        updateDepartComboBox.addItem("Computer Engineering");
        updateDepartComboBox.addItem("Communication Engineering");
        updateDepartComboBox.addItem("Electrical Engineering");
        updateDepartComboBox.addItem("Mechanical Engineering");
        updateDepartComboBox.addItem("Civil Engineering");
        updateDepartComboBox.addItem("Biomedical Engineering");
    }

    private void searchLogic(DefaultTableModel SearchModel, StudentDatabase data){
        String key = KeyField_SearchPanel.getText();
        SearchButton_SearchPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchModel.setRowCount(0);
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






        SearchTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = SearchTable.getSelectedRow();
                if (row >= 0) {

                    updateId.setText(SearchTable.getValueAt(row, 0).toString());
                    updateName.setText(SearchTable.getValueAt(row, 1).toString());
                    updateAge.setText(SearchTable.getValueAt(row, 2).toString());
                    updateGenderComboBox.setSelectedItem(SearchTable.getValueAt(row, 3).toString());
                    updateDepartComboBox.setSelectedItem(SearchTable.getValueAt(row, 4).toString());
                    updateGpa.setText(SearchTable.getValueAt(row, 5).toString());


                    UpdateButton_SearchPanel.setEnabled(true);
                }
            }
        });


        UpdateButton_SearchPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if (updateId.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please select a student from the search table first.");
                        return;
                    }


                    int id = Integer.parseInt(updateId.getText());
                    String name = updateName.getText().trim();
                    int age = Integer.parseInt(updateAge.getText().trim());
                    String gender = updateGenderComboBox.getSelectedItem().toString();
                    String department = updateDepartComboBox.getSelectedItem().toString();
                    float gpa = Float.parseFloat(updateGpa.getText().trim());


                    Student updatedStudent = new Student(id, name, age, gender, department, gpa);


                    data.UpdateStudent(updatedStudent);

                    JOptionPane.showMessageDialog(null, "Student updated successfully!");
                    tables.refreshTable();


                    updateId.setText("");
                    updateName.setText("");
                    updateAge.setText("");
                    updateGpa.setText("");
                    updateGenderComboBox.setSelectedIndex(0);
                    updateDepartComboBox.setSelectedIndex(0);
                    UpdateButton_SearchPanel.setEnabled(false);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers for ID, Age, and GPA.");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Update Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



    }


}
