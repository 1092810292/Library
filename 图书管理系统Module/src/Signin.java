import java.sql.*;
public class Signin {
    public  boolean linkAndCheck(String username, String password) {
        PreparedStatement ps = null;
        Connection ct = null;
        ResultSet rs = null;
        boolean result = false;

        String url = "jdbc:sqlserver://localhost:1433;databaseName=SPJ";//端口：1433，访问的数据库名称：SPJ
        String user = "Library";  //管理员
        String pass = "1998228";  //密码

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
            ct = DriverManager.getConnection(url, user, pass);
            System.out.println("连接数据库成功！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("连接数据库失败！");
        }


        try {
            Statement stmt = ct.createStatement();//ct是连接对象，stmt是会话对象
            String selectUsarName = "SELECT Username FROM NormalUser";
            ps = ct.prepareStatement(selectUsarName);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("Username") == username) {
                    String selectPassWord = "SELECT Password FROM NormalUser";
                    ps = ct.prepareStatement(selectPassWord);
                    rs = ps.executeQuery();
                    if (rs.getString("Password") == password) {
                        result = true;
                    }
                }
            }
            ps.close();
            stmt.close();
            ct.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("失败");
        }
        return result;
    }
}




