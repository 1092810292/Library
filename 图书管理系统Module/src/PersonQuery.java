import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 用户的搜索类
 * @author 王陆斌
 * @since JDK1.8
 * @since JDBC6.2
 *
 */
public class PersonQuery {
    private String queryKind;
    private String userName;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String str = "";
    String information="";
    boolean change=false;
    String sql="";

    PersonQuery(String queryKind,String information,boolean change){
        this.queryKind=queryKind;
        this.information=information;
        this.change=change;
    }

    PersonQuery(String queryKind, String userName) {
    this.queryKind = queryKind;
    this.userName=userName;
    }

    public String  personQuery() {
            try {
                if(change){
                    sql = "SELECT "+queryKind+" FROM NormalUser WHERE "+queryKind+"='"+information+"'";
                }else {
                    sql = "SELECT " + queryKind + " FROM NormalUser WHERE userName='" + userName + "'";
                }
                preparedStatement = LibraryGUI.connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                   str=resultSet.getString(queryKind);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                Error.error("搜索用户信息时失败！");
            }
            return str;
        }
    }
