import java.sql.*;

/**
 * 该类实现了用户的搜索校验
 * @author 王陆斌
 * @since JDK1.8
 */
public class NormalSignin {
    private String userName ;
    private String passWord ;
    PreparedStatement preparedStatement = null;
    ResultSet nameResultSet = null;
    boolean result = false;

    NormalSignin(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    /**
     * 用户的搜索校验方法
     * @return 搜索到返回true
     * @throws SQLException
     */
    public boolean normalSign() throws SQLException {
        try {
            //在连接对象的基础上创建会话对象
            Statement statement = LibraryGUI.connection.createStatement();//ct是连接对象，stmt是会话对象
            String sql = "SELECT userName FROM NormalUser WHERE userName=" +"'"+ userName+"'"+" AND passWord="+"'"+passWord+"'";
            preparedStatement = LibraryGUI.connection.prepareStatement(sql);
            nameResultSet = preparedStatement.executeQuery();
            if (nameResultSet.next()) {
                result = true;
            }
        } catch (SQLException ex) {
            Error.error("失败");
        }
        return result;
    }
}
