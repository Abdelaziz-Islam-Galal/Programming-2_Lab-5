import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DeleteLogic {
    private JFrame frame;
    private TablesLogic tables;
    public DeleteLogic(JTable DeleteTable, StudentDatabase data, JFrame frame, TablesLogic tables) {
        this.frame = frame;
        this.tables = tables;
        deleteLogic(DeleteTable, data, (DefaultTableModel) DeleteTable.getModel());
    }

    private void deleteLogic(JTable DeleteTable, StudentDatabase data, DefaultTableModel DeleteModel) {
        DeleteTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = DeleteTable.rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        showDeleteConfirmation(DeleteTable, DeleteModel, row, data);
                    }
                }
            }
        });
    }
    private void showDeleteConfirmation(JTable DeleteTable, DefaultTableModel DeleteModel, int row, StudentDatabase data) {
        int id = (Integer) DeleteModel.getValueAt(row, 0);
        String name = (String) DeleteModel.getValueAt(row, 1);
        String department = (String) DeleteModel.getValueAt(row, 3);

        String message = "Are you sure you want to delete this record?\n\n" + "ID: " + id + "\nName: " + name + "\nDepartment: " + department;

        int result = JOptionPane.showConfirmDialog(
                frame,
                message,
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (result == JOptionPane.YES_OPTION) {
            data.deleteStudent(id);
            tables.refreshTable();
            JOptionPane.showMessageDialog(frame,
                    "Record deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
