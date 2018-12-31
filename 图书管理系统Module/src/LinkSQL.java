import java.sql.*;

/**
 * 该类实现数据库的用户表的插入功能，搭配注册功能使用
 * @author 王陆斌
 * @since JDK1.8
 */
public class LinkSQL {
    private Connection connection = null;
    private String user="1092810292";
    private String pass="wanglubing123";
    private String url;

     LinkSQL() {
        url = "jdbc:sqlserver://localhost:1433;databaseName=Library";//端口：localhost，访问的数据库名称：databaseName
    }
    LinkSQL(String databaseName) {
        url = "jdbc:sqlserver://localhost:1433;databaseName=" + databaseName;//端口：localhost，访问的数据库名称：databaseName
    }
    LinkSQL(String databaseName,String user,String pass) {
        url = "jdbc:sqlserver://localhost:1433;databaseName=" + databaseName;//端口：localhost，访问的数据库名称：databaseName
        this.user=user;
        this.pass=pass;
    }

    /**
     * 连接数据库的方法
     * @return 返回类型为connection
     */
    public Connection link(){
        try {
            //1.加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("加载驱动成功！");
        } catch (Exception e) {
            Error.error("加载驱动失败！");
            e.printStackTrace();
        }
        try {
            //2.连接
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("连接数据库成功！");
        } catch (Exception e) {
            Error.error("连接数据库失败！");
            e.printStackTrace();
        }
        return connection;
    }
}
