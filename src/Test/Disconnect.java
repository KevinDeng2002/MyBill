package Test;

//养成关闭好习惯

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Disconnect {
    public static void main(String[] args) {
        Connection c = null;
        Statement s = null;
        try {
             Class.forName("com.mysql.jdbc.Driver");
             c = DriverManager
                    .getConnection(
                            "jdbc:mysql://127.0.0.1:3306/mybill?characterEncoding=UTF-8",
                            "root", "root");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            // 先关闭statement，再关闭Connection
            if(s != null)
                try{
                    s.close();
                }catch (SQLException e)
                {
                    e.printStackTrace();
                }
            if (c != null)
            {
                try{
                    c.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
