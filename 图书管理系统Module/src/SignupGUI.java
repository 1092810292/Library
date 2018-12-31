import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * 这个是登录页面的类
 * @author 王陆斌
 * @since JDK1.8
 */
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

    /**
     * 这是验证输入的类，要求输入的内容不能为空或空字符，长度达到要求
     * @param string 要验证的字符串
     * @param length 长度要求
     * @param unequal 验证的是不等于或是其他
     * @return 符合要求则返回true，不符合则返回false
     */
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

    /**
     * 验证输入框输入，调用了verifyInput方法
     * @return 如果输入都符合要求则返回true，反之返回false
     */
    private boolean inputIsTrue(){
        count=0;
        if(verifyInput(nameTextText,6,false))count+=1;
        if(verifyInput(phoneTextText,11,true))count+=1;
        if(verifyInput(userNameTextText,10,true))count+=1;
        if(verifyInput(passWordTextText,10,true))count+=1;
        if(verifyInput(againTextText,10,true))count+=1;
        if(count==5){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 验证两次密码输入正确的方法
     * @return 输入正确返回true
     */
    private boolean passWordIsTrue(){
        if(passWordTextText.equals(againTextText)){
            return true;
        }else{
            return false;
        }

    }

    /**
     * 验证随机验证码输入的方法
     * @return 输入正确返回true
     */
    private boolean verifyCodeIsTrue(){
        if(verifyTextText.equals(result)){
            return true;
        }else{
            return false;
        }

    }

    /**
     * 验证所有输入，其中有：所输入的内容符合要求，两次输入的密码一致，随机验证码输入正确
     * @return 三个要求都满足返回true
     */
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

    /**
     * 生成随机验证码的方法，随机选择两个0到9的数字做加减乘三种运算
     * @return 随机生成的验证码，格式为String
     */
    public String verifyCode(){
        int number1 = (int)(Math.random()*9+1);//从0-9随机选一个数字
        int number2 = (int)(Math.random()*9+1);
        int symbolCode = (int)(Math.random()*3);//从0-2随机选一个数字，并根据这个数字选择加减乘三种运算
        switch( symbolCode )
        {
            case 0: symbol="➕"; result=""+(number1+number2); break;
            case 1: symbol="－"; result=""+(number1-number2); break;
            case 2: symbol="✖"; result=""+(number1*number2);break;
        }
        symbol=""+number1+symbol+ ""+number2;
        return symbol;
    }

    /**
     * 随机生成ID号的方法，生成一个97-122之间的int类型整数--为了生成小写字母，生成一个65-90之间的int类型整数--为了生成大写字母，生成一个30-39之间的int类型整数--为了生成数字
     * @return 返回生成的ID号，格式为String
     */
    public static String randomStr() {
        int i = 18;//控制字符长度
        StringBuilder stringBuilder = new StringBuilder() ;
        for (int j = 0; j < i; j++) {//循环 i 次，每次在字符序列中加入一个字符
            int intValL = (int)(Math.random()*26+97);//生成一个97-122之间的int类型整数--为了生成小写字母
            int intValU = (int)(Math.random()*26+65);//生成一个65-90之间的int类型整数--为了生成大写字母
            int intValN = (int)(Math.random()*10+48);//生成一个30-39之间的int类型整数--为了生成数字
            int intVal;
            int r = (int)(Math.random()*3);
            if(r==0) {
                intVal = intValL;
            }else if (r==1) {
                intVal = intValU;
            }else {
                intVal = intValN;
            }
            stringBuilder.append((char) intVal);//stringBuilder是一个可变的字符序列，强行转换为char参数，并将此字符串附加到此序列。
        }
        return stringBuilder.toString();//将字符序列转换完字符串
    }

    public SignupGUI() {
        //点击刷新按钮，重新加载验证码
        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                verifyCode();
                verifyLabel.setText(symbol);
            }
        });

        //点击注册按钮，读取所有输入框并验证，正确则向数据库插入
        signupButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nameTextText = nameText.getText();
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
                    personIDTextText=randomStr();
                    aBoolean = new Insert(nameTextText, personIDTextText, sex, phoneTextText, userNameTextText, passWordTextText).insert();//插入数据库
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
        //姓名输入框得到焦点时清空输入框
        nameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                nameVerifyLabel.setText("");
            }
        });
        //姓名输入框失去焦点时验证输入
        nameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String nameTextText=nameText.getText();
                if(!verifyInput(nameTextText,5,false)){
                    nameVerifyLabel.setText("限制5位请重新输入！");
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
