import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardLogic extends JPanel{
    private JButton logout;
    private JFrame frame;
    public DashboardLogic(JButton logout, JFrame frame) {
        this.logout= logout;
        this.frame = frame;
        setupLogoutButton();
    }

    public void setupLogoutButton() {
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Login login = new Login();
            }
        });
    }
}
