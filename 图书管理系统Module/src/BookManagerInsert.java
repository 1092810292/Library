import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BookManagerInsert {
    public JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton 添加Button;

    public BookManagerInsert() {
        添加Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String bookName=textField1.getText();
                String ISBN=textField2.getText();
                String writer=textField3.getText();
                String publishing=textField4.getText();
                String number=textField5.getText();
                String place=textField6.getText();
                PreparedStatement preparedStatement = null;
                boolean result=false;
                try {
                    //在连接对象的基础上创建会话对象
                    Statement statement = LibraryGUI.connection.createStatement();//ct是连接对象，stmt是会话对象
                    String sql = "insert into Book(bookName, ISBN, writer, publishing, number, place) values(?,?,?,?,?,?)";
                    preparedStatement = LibraryGUI.connection.prepareStatement(sql);
                    preparedStatement.setString(1,bookName);
                    preparedStatement.setString(2,ISBN);
                    preparedStatement.setString(3,writer);
                    preparedStatement.setString(4,publishing);
                    preparedStatement.setString(5,number);
                    preparedStatement.setString(6,place);
                    int rs = preparedStatement.executeUpdate();
                    //以上无需修改
                    if(rs>0) {
                        result=true;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("添加失败！");
                }
                if(result){
                    Succeed.succeed();
                }else {
                    Error.error("添加失败！");
                }
            }
        });
    }
}
