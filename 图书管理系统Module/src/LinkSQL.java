import java.sql.*;
public class LinkSQL {
    Connection connection = null;
    private final String user="1092810292";
    private final String pass="wanglubing123";
    private String url="";

     LinkSQL() {
        url = "jdbc:sqlserver://localhost:1433;databaseName=Library";//端口：localhost，访问的数据库名称：databaseName
    }
    LinkSQL(String databaseName) {
        url = "jdbc:sqlserver://localhost:1433;databaseName=" + databaseName;//端口：localhost，访问的数据库名称：databaseName
    }
//    LinkSQL(String localhost,String databaseName) {
//        url = "jdbc:sqlserver://localhost:" + localhost + ";databaseName=" + databaseName;//端口：localhost，访问的数据库名称：databaseName
//    }

    public Connection link(){
        try {
            //1.加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("加载驱动成功！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("加载驱动失败！");
        }
        try {
            //2.连接
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("连接数据库成功！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("连接数据库失败！");
        }
        return connection;
    }
}
