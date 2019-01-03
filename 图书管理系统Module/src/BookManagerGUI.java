import com.sun.deploy.util.StringUtils;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BookManagerGUI {
    public JPanel bottonPanel;
    private JTabbedPane tabbedPane;
    private JPanel searchPanel;
    private JPanel personPanel;
    private JRadioButton bookNameRadioButton;
    private JRadioButton ISBNRadioButton;
    private JRadioButton writerRadioButton;
    private JRadioButton publishingRadioButton;
    private JButton findButton;
    private JButton updateButton;
    private JButton addButton;
    private JTextField inTextField;
    private JPanel radioButtonPanel;
    private JPanel inPanel;
    private JScrollPane outScrollPane;
    private JPanel buttonPanel;
    private JTextArea outTextArea;
    private JLabel managerNameLabel;
    private JLabel managerDutyLabel;
    private JPanel LabelPanel;
    private JLabel managerNameOutLabel;
    private JLabel managerDutyOutLabel;
    private JPanel managerOutPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton 确定Button;
    private JButton 确定Button1;
    private JTabbedPane tabbedPane1;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    int choice=0;
    String string="";
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    String systemTime=df.format(new Date());

    public BookManagerGUI() {
        textField3.setText(systemTime);
        textField6.setText(systemTime);
        try {
            //在连接对象的基础上创建会话对象
            Statement statement = LibraryGUI.connection.createStatement();//ct是连接对象，stmt是会话对象
            String  sql = "SELECT * FROM Manager WHERE managerUserName='" + LibraryGUI.userName + "'";
            PreparedStatement preparedStatement = LibraryGUI.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                managerDutyOutLabel.setText(resultSet.getString("managerDuty"));
                managerNameOutLabel.setText(resultSet.getString("managerName"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("失败");
        }
        findButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<String> list;
                String list_str = "";
                String bookInformation = inTextField.getText();
                String queryKind = "";
                outTextArea.setText("");
                if (bookNameRadioButton.isSelected()) {
                    choice = 1;
                } else if (ISBNRadioButton.isSelected()) {
                    choice = 2;
                } else if (writerRadioButton.isSelected()) {
                    choice = 3;
                } else if (publishingRadioButton.isSelected()) {
                    choice = 4;
                }
                switch (choice) {
                    case 1:
                        queryKind = "bookName";
                        list = new Query(queryKind, bookInformation).query();
                        for (Object object : list) {
                            list_str = StringUtils.join(list, "");
                        }
                        outTextArea.append(list_str);
                        break;
                    case 2:
                        queryKind = "ISBN";
                        list = new Query(queryKind, bookInformation).query();
                        for (Object object : list) {
                            list_str = StringUtils.join(list, "");
                        }
                        outTextArea.append(list_str);
                        break;
                    case 3:
                        queryKind = "writer";
                        list = new Query(queryKind, bookInformation).query();
                        for (Object object : list) {
                            list_str = StringUtils.join(list, "");
                        }
                        outTextArea.append(list_str);
                        break;
                    case 4:
                        queryKind = "publishing";
                        list = new Query(queryKind, bookInformation).query();
                        for (Object object : list) {
                            list_str = StringUtils.join(list, "");
                        }
                        outTextArea.append(list_str);
                        break;
                }
            }
        });
        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JFrame frame = new JFrame("BookManagerUpdateGUI");
                frame.setContentPane(new BookManagerUpdateGUI().bottomPanel);
                frame.setDefaultCloseOperation(2);
                frame.pack();
                frame.setVisible(true);
            }
        });
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JFrame frame = new JFrame("BookManagerUpdateGUI");
                frame.setContentPane(new BookManagerInsert().panel1);
                frame.setDefaultCloseOperation(2);
                frame.pack();
                frame.setVisible(true);
            }
        });
        确定Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PreparedStatement preparedStatement;
                String borrowISBN=textField1.getText();
                String borrowAccount=textField2.getText();
                String borrowTime=systemTime;
                boolean result=false;
                String list_str="";
                String string = new PersonQuery("userName",borrowAccount,true).personQuery();
                ArrayList list = new Query("ISBN", borrowISBN).query();
                for (Object object : list) {
                    list_str = StringUtils.join(list, "");
                }
                if(!string.equals(borrowAccount)){
                    Error.error("借书人帐号输入错误！");
                }else if(!list_str.equals(borrowISBN)){
                    Error.error("所借书书号输入错误！");
                }
                try {
                    //在连接对象的基础上创建会话对象
                    Statement statement = LibraryGUI.connection.createStatement();//ct是连接对象，stmt是会话对象
                    String sql = "insert into BorrowReturnBooks(borrowISBN,borrowAccount,borrowTime) values(?,?,?)";
                    preparedStatement = LibraryGUI.connection.prepareStatement(sql);
                    preparedStatement.setString(1,borrowISBN);
                    preparedStatement.setString(2,borrowAccount);
                    preparedStatement.setString(3,borrowTime);
                    int rs = preparedStatement.executeUpdate();
                    //以上无需修改
                    if(rs!=0) {
                        result=true;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("失败");
                }
                if(result){
                    Succeed.succeed();
                }else {
                    Error.error("错误！");
                }
            }
        });
        确定Button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PreparedStatement preparedStatement;
                String borrowISBN=textField4.getText();
                String borrowAccount=textField5.getText();
                String returnTime=systemTime;
                boolean result=false;
                String list_str="";
                String string = new PersonQuery("userName",borrowAccount,true).personQuery();
                ArrayList list = new Query("ISBN", borrowISBN).query();
                for (Object object : list) {
                    list_str = StringUtils.join(list, "");
                }
                if(string.equals(borrowAccount)){
                    Error.error("借书人帐号输入错误！");
                }else if(list_str.equals(borrowISBN)){
                    Error.error("所借书书号输入错误！");
                }
                try {
                    //在连接对象的基础上创建会话对象
                    Statement statement = LibraryGUI.connection.createStatement();//ct是连接对象，stmt是会话对象
                    String sql = "update BorrowReturnBooks set returnTime=? where borrowISBN=? and borrowAccount=?";
                    preparedStatement = LibraryGUI.connection.prepareStatement(sql);
                    preparedStatement.setString(1,returnTime);
                    preparedStatement.setString(2,borrowISBN);
                    preparedStatement.setString(3,borrowAccount);
                    int rs = preparedStatement.executeUpdate();
                    //以上无需修改
                    if(rs!=0) {
                        result=true;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("失败");
                }
                if(result){
                    Succeed.succeed();
                }else {
                    Error.error("错误！");
                }
            }
        });
    }
}
