import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PersonQuery {
    private String queryKind;
    private String userName;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String bookName = "";
    String ISBN = "";
    String writer = "";
    String publishing = "";
    ArrayList<String> list = new ArrayList<String>();
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
                //在连接对象的基础上创建会话对象
                Statement statement = LibraryGUI.connection.createStatement();//ct是连接对象，stmt是会话对象
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
                System.out.println("失败");
            }
            return str;
        }
    }
