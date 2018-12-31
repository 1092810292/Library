import java.sql.*;
import java.util.ArrayList;

public class Query {
    private String queryKind;
    private String bookInformation;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String bookName="";
    String ISBN="";
    String writer="";
    String publishing="";
    String number="";
    String place="";
    ArrayList<String> list=new ArrayList<String>();
    String str="";
    Query(String queryKind, String bookInformation){
        this.queryKind=queryKind;
        this.bookInformation=bookInformation;
    }
    public ArrayList query(){
        try {
            String sql = "select * from Book where "+queryKind+" like '%"+bookInformation+"%'";
            preparedStatement = LibraryGUI.connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                bookName=resultSet.getString("bookName");
                ISBN=resultSet.getString("ISBN");
                writer=resultSet.getString("writer");
                publishing=resultSet.getString("publishing");
                number=resultSet.getString("number");
                place=resultSet.getString("place");
                str=bookName+"/"+writer+"编著/"+publishing+"出版/ISBN"+ISBN+"/库存为："+number+"/书架号为："+place+"\n";
                list.add(str);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Error.error("搜索图书信息时失败！");
            System.out.println("失败");
        }
        return list;
    }
}
