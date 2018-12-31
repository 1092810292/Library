import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Update {
    PreparedStatement preparedStatement=null;
    private String update;
    private boolean result=false;
    Update(String update){
        this.update=update;
    }

    public boolean update(){
        try {
            Statement statement = LibraryGUI.connection.createStatement();//ct是连接对象，stmt是会话对象
            String sql = "update NormalUser set phone="+update+" where userName="+LibraryGUI.userName;
            preparedStatement = LibraryGUI.connection.prepareStatement(sql);
            int rs = statement.executeUpdate(sql);
            if(rs>0) {
                result=true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Error.error("更新用户手机号时失败！");
            System.out.println("失败");
        }
        return result;
    }
}
