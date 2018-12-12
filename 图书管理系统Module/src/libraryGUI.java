import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class libraryGUI {
    private JPanel bottomPanel;
    private JLabel userNameLabel;
    private JLabel passWordLabel;
    private JPanel westPanel;
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel eastPanel;
    private JButton signinButton;
    private JButton signupButton;
    private JTextField userNameText;
    private JRadioButton managerRadioButton;
    private JRadioButton normalRadioButton;
    private JButton visitorButton;
    private JPasswordField passwordText;

    public libraryGUI() {
        visitorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = new JFrame("visitorGUI");
                frame.setContentPane(new visitorGUI().bottomPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        userNameText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userNameText.setText("");
            }
        });
        passwordText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                passwordText.setText("");
            }
        });
        signinButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username=userNameText.getText();
                String password = new String(passwordText.getPassword());
                Signin signin=new Signin();
                signin.linkAndCheck(username,password);

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("libraryGUI");
        frame.setContentPane(new libraryGUI().bottomPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
