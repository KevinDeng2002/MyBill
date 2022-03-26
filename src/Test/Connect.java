package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static void main(String[] args) {
        try{
            // 此行必须加
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager
                    .getConnection(
                            "jdbc:mysql://127.0.0.1:3306/mybill?characterEncoding=UTF-8",
                            "root", "root");
            System.out.println("连接成功，获取连接对象： " + c);
        }catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
