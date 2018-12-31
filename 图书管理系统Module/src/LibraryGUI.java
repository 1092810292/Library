import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 这个类是用户开始使用的时候所展示的GUI登录界面
 * @since JDK1.8
 * @author 王陆斌
 * */
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

    /**
     * 这个方法由登录按钮调用，当点击登录按钮之后，读取是单选框按钮的值判断是普通用户登录还是管理员登录，然后分类搜索数据库对应的表，搜索成功则进入对应页面，搜索失败则请求用户重新输入
     * @throws SQLException
     */
    private void signin() throws SQLException {
        //普通用户登录
        if(normalRadioButton.isSelected()) {
            userName = userNameText.getText();
            passWord = new String(passwordText.getPassword());
            NormalSignin normal=new NormalSignin(userName,passWord);//验证登录名和密码是否符合
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
            } catch (SQLException e) {
                Error.error("错误！");
            }
        }
        //管理员登录
        if(managerRadioButton.isSelected()) {
            userName = userNameText.getText();
            passWord = new String(passwordText.getPassword());
            ManagerSignin managerSignin=new ManagerSignin(userName,passWord);//验证登录名和密码是否符合
            try {
                if("图书管理员".equals(managerSignin.managerSign())){
                    JFrame frame = new JFrame("BookManagerGUI");
                    frame.setContentPane(new BookManagerGUI().bottonPanel);
                    frame.setDefaultCloseOperation(2);
                    frame.pack();
                    frame.setVisible(true);
                }
                else {
                    Error.error("管理员帐号或密码错误请重新输入！");
                    passwordText.setText(null);
                }
            } catch (SQLException e) {
                Error.error("错误！");
            }
        }
    }

    /**
     * 这是这个类的构造方法，生成登录页面时自动调用
     */
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

        //鼠标单击登录按钮，进入signin方法
        signinButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    signin();
                } catch (SQLException e1) {
                    Error.error("错误");
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

        //当密码输入框获得焦点，如果框内输入为“请输入密码”，则清空框内输入，并加密
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

        //设置密码框属性，生成页面时不加密并显示“请输入密码”
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
                frame.setLocation(900,300);
            }
        });
    }

    /**
     * 该系统的main方法，当JVM打开该文件时，连接数据库，连接成功则转到LibraryGUI窗口，失败则转到Mistake窗口
     * @param args
     * @throws SQLException
     */
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
