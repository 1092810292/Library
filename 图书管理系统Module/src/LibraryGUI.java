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
    private String passWord;


    private void signin() throws SQLException {
        //如果是普通用户
        if(normalRadioButton.isSelected()) {
            userName = userNameText.getText();
            passWord = new String(passwordText.getPassword());
            NormalSignin normal=new NormalSignin(userName,passWord);
            try {
                if(normal.normalSign()) {
                    JFrame frame = new JFrame("NormalGUI");
                    frame.setContentPane(new NormalGUI().bottomPanel);
                    frame.setDefaultCloseOperation(2);
                    frame.pack();
                    frame.setVisible(true);
                    frame.setLocation(900,500);
                }else{
                    Error.error("用户帐号或密码错误请重新输入！");
                    passwordText.setText(null);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        //如果是管理员（实现中）
        if(managerRadioButton.isSelected()) {
            String userName = userNameText.getText();
            String passWord = new String(passwordText.getPassword());
            ManagerSignin managerSignin=new ManagerSignin(userName,passWord);
            try {
                if("用户管理员".equals(managerSignin.managerSign())) {
                    JFrame frame = new JFrame("UserManagerGUI");
                    frame.setContentPane(new UserManagerGUI().bottomPanel);
                    frame.setDefaultCloseOperation(2);
                    frame.pack();
                    frame.setVisible(true);
                    frame.setLocation(900,500);
                }else if("图书管理员".equals(managerSignin.managerSign())){
                    JFrame frame = new JFrame("BookManagerGUI");
                    frame.setContentPane(new BookManagerGUI().bottonPanel);
                    frame.setDefaultCloseOperation(2);
                    frame.pack();
                    frame.setVisible(true);
                }else if("普通职员".equals(managerSignin.managerSign())){
                    JFrame frame = new JFrame("NormalManagerGUI");
                    frame.setContentPane(new NormalManagerGUI().bottomPanel);
                    frame.setDefaultCloseOperation(2);
                    frame.pack();
                    frame.setVisible(true);
                }
                else {
                    Error.error("管理员帐号或密码错误请重新输入！");
                    passwordText.setText(null);
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
                frame.setLocation(900,500);
            }
        });
        //鼠标单击登录按钮，进入signin方法，搜索数据库，符合用户名和密码就弹出用户（NormalUser）窗口(ok)
        signinButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    signin();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        //当用户名输入框获得焦点，如果框内输入为“请输入用户名”则清空
        userNameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String str = userNameText.getText();
                if(null != str && userNameText.getText().equals("请输入用户名")){
                    userNameText.setText(null);
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
                passWord= new String(passwordText.getPassword());
                if (null != passwordText && passWord.equals("请输入密码")) {
                    passwordText.setEchoChar((char)42);
                    passwordText.setText(null);
                }
            }
        });
        //当密码输入框失去焦点，如果框内未输入，则取消加密并显示“请输入密码”
        passwordText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                passWord= new String(passwordText.getPassword());
                if(passWord==null || (passWord!=null && passWord.length()==0)){
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
                frame.setLocation(900,500);
            }
        });
    }

    public static void main(String[] args) throws SQLException {
        LinkSQL linkSQL = new LinkSQL();
        connection = linkSQL.link();
        if(connection!=null) {
            JFrame frame = new JFrame("LibraryGUI");
            frame.setContentPane(new LibraryGUI().bottomPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            frame.setLocation(900,500);
        }else {
            JFrame frame = new JFrame("Mistake");
            frame.setContentPane(new Mistake().mistakePanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            frame.setLocation(900,500);
        }
    }
}
