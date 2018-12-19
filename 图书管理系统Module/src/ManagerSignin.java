import java.sql.*;

public class ManagerSignin {
    private String userName = "";
    private String passWord = "";
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String result="";

    ManagerSignin(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String managerSign() throws SQLException {
        try {
            //在连接对象的基础上创建会话对象
            Statement statement = LibraryGUI.connection.createStatement();//ct是连接对象，stmt是会话对象
            String sql = "SELECT managerDuty FROM Manager WHERE managerUserName=" + userName+" AND managerPassWord="+ passWord;
            preparedStatement = LibraryGUI.connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result=resultSet.getString("managerDuty");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("失败");
        }
        return result;
    }
}
