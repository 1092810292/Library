import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.SQLException;

public class LibraryGUI {
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
    public static Connection connection = null;
    public static String userName;


    private void signin() {
        //如果是普通用户
        if(normalRadioButton.isSelected()) {
            userName = userNameText.getText();
            String passWord = new String(passwordText.getPassword());
            NormalSignin normal=new NormalSignin(userName,passWord);
            try {
                if(normal.normalSign()) {
                    JFrame frame = new JFrame("NormalGUI");
                    frame.setContentPane(new NormalGUI().bottomPanel);
                    frame.setDefaultCloseOperation(2);
                    frame.pack();
                    frame.setVisible(true);
                }else{
                    Error.error();
                    userNameText.setText("");
                    passwordText.setText("");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        //如果是管理员
        if(managerRadioButton.isSelected()) {
            String userName = userNameText.getText();
            String passWord = new String(passwordText.getPassword());
            ManagerSignin managerSignin=new ManagerSignin(userName,passWord);
            try {
                if(managerSignin.managerSign()) {
                    JFrame frame = new JFrame("ManagerGUI");
                    frame.setContentPane(new ManagerGUI().panel1);
                    frame.setDefaultCloseOperation(2);
                    frame.pack();
                    frame.setVisible(true);
                }else {
                    Error.error();
                    userNameText.setText("");
                    passwordText.setText("");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public LibraryGUI() {
        //鼠标单击游客登录按钮,弹出搜索（Query）窗口(ok)
        visitorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = new JFrame("QueryGUI");
                frame.setContentPane(new QueryGUI().bottomPanel);
                frame.setDefaultCloseOperation(2);
                frame.pack();
                frame.setVisible(true);
            }
        });
        //鼠标单击登录按钮，进入signin方法，搜索数据库，符合用户名和密码就弹出用户（NormalUser）窗口(ok)
        signinButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                signin();
            }
        });
        //当用户名输入框获得焦点，如果框内输入为“请输入用户名”则清空
        userNameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String str = userNameText.getText();
                if(null != str && userNameText.getText().equals("请输入用户名")){
                    userNameText.setText("");
                }
            }
        });
        //当用户名输入框失去焦点，如果框内未输入，则显示“请输入用户名”
        userNameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String str = userNameText.getText();
                if(null == str || userNameText.getText().equals("")){
                    userNameText.setText("请输入用户名");
                }
            }
        });
        //当密码输入框获得焦点，清空框内输入，并加密
        passwordText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordText.setText("");
                passwordText.setEchoChar((char)42);
            }
        });
        //当密码输入框失去焦点，如果框内未输入，则取消加密并显示“请输入密码”
        passwordText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                char[] str=passwordText.getPassword();
                if(str==null || (str!=null && str.length==0)){
                    passwordText.setEchoChar((char)0);
                    passwordText.setText("请输入密码");
                }
            }
        });
        //设置密码框属性，生成时不加密并显示“请输入密码”
        passwordText.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                passwordText.setEchoChar((char)0);
                passwordText.setText("请输入密码");
            }
        });
        //单击注册按钮，弹出注册（signup）窗口
        signupButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = new JFrame("SignupGUI");
                frame.setContentPane(new SignupGUI().signupPanel);
                frame.setDefaultCloseOperation(2);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) throws SQLException {
        LinkSQL linkSQL = new LinkSQL();
        connection = linkSQL.link();
        JFrame frame = new JFrame("LibraryGUI");
        frame.setContentPane(new LibraryGUI().bottomPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }
}
