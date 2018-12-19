import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
    private JLabel nameVerify;
    private JLabel perssonVerify;
    private JLabel sexVerify;
    private JLabel phoneVerify;
    private JLabel userVerify;
    private JLabel passVerify;
    private JLabel againVerify;
    private JLabel nameVerifyLabel;
    private JLabel personIDVerifyLabel;
    private JLabel sexVerifyLabel;
    private JLabel phoneVerifyLabel;
    private JLabel userNameVerifyLabel;
    private JLabel passWordVerifyLabel;
    private JLabel againVerifyLabel;
    private JLabel verifyCodeLabel;
    private JLabel verifyCode;
    String result="";
    String symbol="";
    String nameTextText="";
    String personIDTextText="";
    String phoneTextText="";
    String userNameTextText="";
    String passWordTextText="";
    String againTextText="";
    String verifyTextText="";
    int count=0;
    boolean inputIsFalse;
    boolean passWordIsFalse;
    boolean verifyCodeIsFalse;

    private boolean verifyInput(String string,int length,boolean unequal) {
        if (unequal) {
            if (string == null || string.equals("") || string.length() != length) {
                return false;
            } else return true;
        } else {
            if (string == null || string.equals("") || string.length() > length) {
                return false;
            } else return true;
        }
    }

    private boolean inputIsTrue(){
        count=0;
        if(verifyInput(nameTextText,6,false))count+=1;
        if(verifyInput(personIDTextText,18,true))count+=1;
        if(verifyInput(phoneTextText,11,true))count+=1;
        if(verifyInput(userNameTextText,10,true))count+=1;
        if(verifyInput(passWordTextText,10,true))count+=1;
        if(verifyInput(againTextText,10,true))count+=1;
        if(count==6){
            return true;
        }else{
            return false;
        }

    }

    private boolean passWordIsTrue(){
        if(passWordTextText.equals(againTextText)){
            return true;
        }else{
            return false;
        }

    }

    private boolean verifyCodeIsTrue(){
        if(verifyTextText.equals(result)){
            return true;
        }else{
            return false;
        }

    }

    private boolean isVerify(){
        if(!inputIsTrue()){
            inputIsFalse=true;
            Error.error("输入有误请检查！");
            return false;
        }else if(!passWordIsTrue()){
            passWordIsFalse=true;
            passWordText.setText("");
            againText.setText("");
            Error.error("两次密码输入不一致请重新输入！");
            return false;
        }else if(!verifyCodeIsTrue()){
            verifyCodeIsFalse=true;
            verifyText.setText("");
            Error.error("验证码输入错误请重新输入！");
            return false;
        }else return true;
    }

    public String verifyCode(){
        int number1 = (int)(Math.random()*9+1);
        int number2 = (int)(Math.random()*9+1);
        int symbolCode = (int)(Math.random()*4);
        switch( symbolCode )
        {
            case 0: symbol="➕"; result=""+(number1+number2); break;
            case 1: symbol="－"; result=""+(number1-number2); break;
            case 2: symbol="✖"; result=""+(number1*number2);break;
        }
        symbol=""+number1+symbol+ ""+number2;
        return symbol;
    }



    public SignupGUI() {

        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                verifyCode();
                verifyLabel.setText(symbol);
            }
        });
        signupButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nameTextText = nameText.getText();
                personIDTextText = personIDText.getText();
                phoneTextText = phoneText.getText();
                userNameTextText = userNameText.getText();
                passWordTextText = new String(passWordText.getPassword());
                againTextText = new String(againText.getPassword());
                verifyTextText = verifyText.getText();
                boolean sex = true;
                if (womanRadioButton.isSelected()) {
                    sex = false;
                }
                boolean aBoolean=false;
                if (isVerify()) {
                    aBoolean = new Insert(nameTextText, personIDTextText, sex, phoneTextText, userNameTextText, passWordTextText).insert();
                    if (!aBoolean) {
                        signupError("注册失败请联系管理员！");
                    } else {
                        Succeed.succeed();
                    }
                }
                verifyCode();
                verifyLabel.setText(symbol);
            }
        });

        nameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                nameVerifyLabel.setText("");
            }
        });
        nameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String nameTextText=nameText.getText();
                if(!verifyInput(nameTextText,5,false)){
                    nameVerifyLabel.setText("限制5位请重新输入！");
                }
            }
        });
        personIDText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                personIDVerifyLabel.setText("");
            }
        });
        personIDText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String personIDTextText = personIDText.getText();
                if (!verifyInput(personIDTextText,18,true)){
                    personIDVerifyLabel.setText("限制18位请重新输入！");
                }else if(personIDTextText.equals(new PersonQuery("personID",personIDTextText,true).personQuery())) {
                    personIDVerifyLabel.setText("您输入的身份证号已存在！");
                }
            }
        });
        userNameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                userNameVerifyLabel.setText("");
            }
        });
        userNameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String userNameTextText = userNameText.getText();
                if (!verifyInput(userNameTextText,10,true)){
                    userNameVerifyLabel.setText("限制10位请重新输入！");
                }else if(userNameTextText.equals(new PersonQuery("userName", userNameTextText,true).personQuery())) {
                    userNameVerifyLabel.setText("您输入的用户名已存在！");
                }
            }
        });
        passWordText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                passWordVerifyLabel.setText("");
            }
        });
        passWordText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String passWordTextText = new String(passWordText.getPassword());
                if (!verifyInput(passWordTextText,10,true)) {
                    passWordVerifyLabel.setText("限制10位请重新输入");
                }
            }
        });
        againText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                againVerifyLabel.setText("");
            }
        });
        againText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String againTextText = new String(againText.getPassword());
                if (!verifyInput(againTextText,10,true)) {
                    againVerifyLabel.setText("限制10位请重新输入");
                }
            }
        });
        verifyText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                verifyCodeLabel.setText("");
            }
        });
        verifyText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String verifyTextText = verifyText.getText();
                if(verifyTextText==null||verifyTextText.equals("")){
                    verifyCodeLabel.setText("请输入验证码！");
                }
            }
        });
        phoneText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String phoneTextText = phoneText.getText();
                if (!verifyInput(phoneTextText,11,true)){
                    phoneVerifyLabel.setText("限制11位请重新输入！");
                }
            }
        });
        phoneText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                phoneVerifyLabel.setText("");
            }
        });
        verifyLabel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                verifyCode();
                verifyLabel.setText(symbol);
            }
        });
    }
    private void signupError(String errorHint) {
        Error.error(errorHint);
        verifyCode();
        verifyLabel.setText(symbol);
    }
}
