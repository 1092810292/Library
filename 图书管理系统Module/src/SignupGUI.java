import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
    private JLabel sexLabel;
    private JRadioButton manRadioButton;
    private JRadioButton womanRadioButton;
    int result=0;
    boolean aBoolean=false;
    String op="";

    public SignupGUI() {
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
                    String nameTextText=nameText.getText();
                    String personIDTextText=personIDText.getText();
                    String phoneTextText=phoneText.getText();
                    String userNameTextText=userNameText.getText();
                    String passWordTextText = new String(passWordText.getPassword());
                    String againTextText = new String(againText.getPassword());
                    boolean sex=true;
                    if(womanRadioButton.isSelected()){
                        sex=false;
                    }
                    if(passWordTextText.equals(againTextText)) {
                        aBoolean=new Insert(nameTextText,personIDTextText,sex,phoneTextText,userNameTextText,passWordTextText).insert();
                        if(aBoolean) {
                            Succeed.succeed();
                        }else {
                            signupError();
                        }
                    }
                    else{
                        signupError();//有问题
                    }
                }
                else {
                    System.out.println("验证失败！");
                    verify();
                    verifyLabel.setText(op);
                }
            }
        });
        verifyText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String str = userNameText.getText();
                if(null != str && userNameText.getText().equals("请输入用户名")){
                    userNameText.setText("");
                }
            }
        });
        verifyText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String str = userNameText.getText();
                if(null == str || userNameText.getText().equals("")){
                    userNameText.setText("请输入用户名");
                }
            }
        });
    }

    private void signupError() {
        Error.error();
        passWordText.setText("");
        againText.setText("");
        verify();
        verifyLabel.setText(op);
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
}
