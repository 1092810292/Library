import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert {
    PreparedStatement preparedStatement=null;
    private String update;
    private boolean result=false;
    private String nameTextText;
    private String personIDTextText;
    private String phoneTextText;
    private String userNameTextText;
    private String passWordTextText;
    private boolean sex;
    private String sexText;
    Insert( String nameTextText,String personIDTextText,boolean sex,String phoneTextText,String userNameTextText,String passWordTextText){
        this.nameTextText=nameTextText;
        this.personIDTextText=personIDTextText;
        this.sex=sex;
        this.phoneTextText=phoneTextText;
        this.userNameTextText=userNameTextText;
        this.passWordTextText=passWordTextText;
        if(sex){
            sexText="男";
        }else {
            sexText="女";
        }
    }

    public boolean insert(){
        try {
            //在连接对象的基础上创建会话对象
            Statement statement = LibraryGUI.connection.createStatement();//ct是连接对象，stmt是会话对象
            String sql = "insert into NormalUser(name,personID,sex,phone,userName,passWord) values('"+nameTextText+"','"+personIDTextText+"','"+sexText+"','"+phoneTextText+"','"+userNameTextText+"','"+passWordTextText+"')";
            preparedStatement = LibraryGUI.connection.prepareStatement(sql);
            int rs = statement.executeUpdate(sql);
            //以上无需修改
            if(rs>0) {
                result=true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("失败");
        }
        return result;
    }
}
