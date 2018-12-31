import java.sql.*;

/**
 * 该类实现了管理员帐号密码的搜索校验
 * @author 王陆斌
 * @since JDK1.8
 */
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

    /**
     * 管理员帐号密码的搜索校验的方法
     * @return 返回搜索到的职务，类型为String
     * @throws SQLException
     */
    public String managerSign() throws SQLException {
        try {
            //在连接对象的基础上创建会话对象
            String sql = "SELECT managerDuty FROM Manager WHERE managerUserName=" + userName+" AND managerPassWord="+ passWord;//搜索符合帐号密码的管理员的职务
            preparedStatement = LibraryGUI.connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result=resultSet.getString("managerDuty");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Error.error("管理员登录时搜索失败！");
        }
        return result;
    }
}
