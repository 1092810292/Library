import com.sun.deploy.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookManagerUpdateGUI {
    public JPanel bottomPanel;
    private JTextField textField1;
    private JTextField ISBNTextField;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton 搜索Button;
    private JButton 修改Button;

    public BookManagerUpdateGUI() {
        搜索Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                String bookName="";
                String ISBN=ISBNTextField.getText();
                String writer="";
                String publishing="";
                String number="";
                String place="";
                try {
                    //在连接对象的基础上创建会话对象
                    String sql = "select * from Book where ISBN like '%"+ISBN+"%'";
                    preparedStatement = LibraryGUI.connection.prepareStatement(sql);
                    resultSet = preparedStatement.executeQuery();
                    //以上无需修改
                    while(resultSet.next()) {
                        bookName=resultSet.getString("bookName");
                        ISBN=resultSet.getString("ISBN");
                        writer=resultSet.getString("writer");
                        publishing=resultSet.getString("publishing");
                        number=resultSet.getString("number");
                        place=resultSet.getString("place");
                    }
                    textField1.setEditable(true);
                    textField3.setEditable(true);
                    textField4.setEditable(true);
                    textField5.setEditable(true);
                    textField6.setEditable(true);
                    textField1.setText(bookName);
                    ISBNTextField.setText(ISBN);
                    textField3.setText(writer);
                    textField4.setText(publishing);
                    textField5.setText(number);
                    textField6.setText(place);
                } catch (SQLException ex) {
                    Error.error("输入错误！");
                }
            }
        });
        修改Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                String bookName=textField1.getText();
                String ISBN=ISBNTextField.getText();
                String writer=textField3.getText();
                String publishing=textField4.getText();
                String number=textField5.getText();
                String place=textField6.getText();
                boolean result=false;
                try {
                    //在连接对象的基础上创建会话对象
                    Statement statement = LibraryGUI.connection.createStatement();//ct是连接对象，stmt是会话对象
                    String sql = "update Book set bookName=?,writer=?,publishing=?,number=?,place=? where ISBN=?";
                    preparedStatement = LibraryGUI.connection.prepareStatement(sql);
                    preparedStatement.setString(1,bookName);
                    preparedStatement.setString(2,writer);
                    preparedStatement.setString(3,publishing);
                    preparedStatement.setString(4,number);
                    preparedStatement.setString(5,place);
                    preparedStatement.setString(6,ISBN);
                    int rs = preparedStatement.executeUpdate();
                    //以上无需修改
                    if(rs>0) {
                        result=true;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("失败");
                }
                if (result){
                    Succeed.succeed();
                }else{
                    Error.error("无法修改");
                }
            }
        });
    }
}
