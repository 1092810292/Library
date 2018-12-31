import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 该类实现数据库的用户表的插入功能，搭配注册功能使用
 * @author 王陆斌
 * @since JDK1.8
 */
public class Insert {
    private PreparedStatement preparedStatement=null;
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

    /**
     * 该方法实现了数据库的NormalUser表的插入方法
     * @return 插入成功返回true
     */
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
            Error.error("注册插入时失败！");
            System.out.println("失败");
        }
        return result;
    }
}
