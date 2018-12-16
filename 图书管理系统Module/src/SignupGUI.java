import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupGUI {
    public JPanel signupPanel;
    private JPanel westPanel;
    private JPanel eastPanel;
    private JPanel southPanel;
    private JTextField nameText;
    private JTextField personIDText;
    private JTextField phoneText;
    private JTextField userNameText;
    private JPasswordField passWordText;
    private JPasswordField againText;
    private JTextField verifyText;
    private JButton signupButton;
    private JLabel nameLabel;
    private JLabel personIDLabel;
    private JLabel phoneLabel;
    private JLabel userNameLabel;
    private JLabel passWordLabel;
    private JLabel againLabel;
    private JLabel verifyLabel;
    private JButton refreshButton;
    int result=0;
    String op="";

    public SignupGUI() {
        signupButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        verifyLabel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                verify();
                verifyLabel.setText(op);
            }
        });



        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                verify();
                verifyLabel.setText(op);
            }
        });
        signupButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(Integer.parseInt(verifyText.getText())==result) {
                    System.out.println("验证成功，暂且这么着吧");
                    JFrame frame = new JFrame("SignupSuccessGUI");
                    frame.setContentPane(new SignupSuccessGUI().signupSuccessPanel);
                    frame.setDefaultCloseOperation(2);
                    frame.pack();
                    frame.setVisible(true);
                }
                else {
                    System.out.println("验证失败！");
                    verify();
                    verifyLabel.setText(op);
                }
            }
        });
    }
    public String verify(){
        int a = (int)(Math.random()*9+1);
        int b = (int)(Math.random()*9+1);
        int c = (int)(Math.random()*4);
        switch( c )
        {
            case 0: op="➕"; result=a+b; break;
            case 1: op="－"; result=a-b; break;
            case 2: op="✖"; result=a*b;break;
            case 3: op="➗"; result=a/b;break;
        }
        op=String.valueOf(a)+op+ String.valueOf(b);
        return op;
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("SignupGUI");
        frame.setContentPane(new SignupGUI().signupPanel);
        frame.setDefaultCloseOperation(2);
        frame.pack();
        frame.setVisible(true);
    }
}
