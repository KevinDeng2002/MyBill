package util;

// 在这个项目里有多个DAO里都需要获取数据库的连接，
// 并且在本项目中都是一样的数据库连接。 所以就可以把获取数据库连接的代码重构到一个类里。

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    static String ip = "127.0.0.1";
    static int port = 3306;
    static String database = "mybill";
    static String encoding = "UTF-8";
    static String loginName = "root";
    static String passwd = "root";

    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException{
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s",
                ip,port,database,encoding);
        return DriverManager.getConnection(url,loginName,passwd);
    }

}
