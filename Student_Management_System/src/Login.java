import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Login extends JFrame {
    private JPasswordField passwordField;
    private JLabel PasswordLabel;
    private JLabel UsernameLabel;
    private JTextField usernameField;
    private JLabel Title;
    private JButton submit;
    private JPanel LoginContainer;

    public Login() {
        setVisible(true);
        setSize(400,400);
        setContentPane(LoginContainer);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processInput();
            }
        });
    }

    private void processInput() {
        String username = usernameField.getText();
        String passwrod = new String(passwordField.getPassword()); //Arrays.toString(passwordField.getPassword());

        if(username.trim().isEmpty() || passwrod.trim().isEmpty()) {
            JOptionPane.showMessageDialog(LoginContainer, "Please fill all the fields");
        }
//        if(checkCredentials(username,passwrod)) {
//            opeenMainWindow();
//            // close this panel
//
//        }

    }

    private boolean checkCredentials(String username, String passwrod) {
        if(username.equals("admin") && passwrod.equals("1234")) {
            return true;
        }
        return false;
    }
}
