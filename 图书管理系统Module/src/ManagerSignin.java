import java.sql.*;

public class ManagerSignin {
    private String userName = "";
    private String passWord = "";
    PreparedStatement preparedStatement = null;
    ResultSet nameResultSet = null;
    ResultSet passResultSet = null;
    boolean result = false;

    ManagerSignin(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public boolean managerSign() throws SQLException {
        try {
            //在连接对象的基础上创建会话对象
            Statement statement = LibraryGUI.connection.createStatement();//ct是连接对象，stmt是会话对象
            String sql = "SELECT userName FROM NormalUser WHERE userName=" + userName+" AND passWord="+ passWord;
            preparedStatement = LibraryGUI.connection.prepareStatement(sql);
            nameResultSet = preparedStatement.executeQuery();
            if (nameResultSet.next()) {
                result = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("失败");
        }
        return result;
    }
}
