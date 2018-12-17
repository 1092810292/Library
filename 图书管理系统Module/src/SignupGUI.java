import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

public class SignupGUI{
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
    String query="";

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
                   String  nameTextText=nameText.getText();
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
                String str = verifyText.getText();
                if(null != str && verifyText.getText().equals("请输入验证码")){
                    verifyText.setText(null);
                }
            }
        });
        verifyText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String str = verifyText.getText();
                if(null == str || verifyText.getText().equals("")){
                    verifyText.setText("请输入验证码");
                }
            }
        });
        nameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String nameTextText = nameText.getText();
                if (null != nameTextText && nameText.getText().equals("限制5位请重新输入")) {
                    nameText.setText(null);
                }
            }
        });
        nameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String nameTextText=nameText.getText();
                if(nameTextText.length()>5){
                    nameText.setText("限制5位请重新输入");
                }
            }
        });
        personIDText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String personIDTextText = personIDText.getText();
                if (personIDTextText.length() != 18) {
                    personIDText.setText("限制18位请重新输入");
                }
            }
        });
        personIDText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String personIDTextText = personIDText.getText();
                if (null != personIDTextText && personIDText.getText().equals("限制18位请重新输入")) {
                    personIDText.setText(null);
                }
            }
        });
        userNameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String userNameTextText = userNameText.getText();
                if (null != userNameTextText && userNameText.getText().equals("限制10位请重新输入")) {
                    userNameText.setText(null);
                }
            }
        });
        userNameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String userNameTextText = userNameText.getText();
                if (userNameTextText.length() != 10) {
                    userNameText.setText("限制10位请重新输入");
                }
            }
        });
        passWordText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String passWordTextText = new String(passWordText.getPassword());
                if (passWordTextText.length() != 10) {
                    passWordText.setEchoChar((char)0);
                    passWordText.setText("限制10位请重新输入");
                }
            }
        });
        againText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String againTextText = new String(againText.getPassword());
                if (againTextText.length() != 10) {
                    againText.setEchoChar((char)0);
                    againText.setText("限制10位请重新输入");
                }
            }
        });
        passWordText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String passWordTextText = new String(passWordText.getPassword());
                if (null != passWordTextText && passWordTextText.equals("限制10位请重新输入")) {
                    passWordText.setEchoChar((char)42);
                    passWordText.setText(null);
                }
            }
        });
        againText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String againTextText = new String(againText.getPassword());
                if (null != againTextText && againTextText.equals("限制10位请重新输入")) {
                    againText.setEchoChar((char)42);
                    againText.setText(null);
                }
            }
        });
    }

    private void signupError() {
        Error.error();
        passWordText.setText(null);
        againText.setText(null);
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
