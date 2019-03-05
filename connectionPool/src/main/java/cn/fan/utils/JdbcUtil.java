package cn.fan.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static{
        Properties ps = new Properties();
        try {
            ps.load(JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            driver = ps.getProperty("driverClassName");
            url = ps.getProperty("url");
            username = ps.getProperty("username");
            password =ps.getProperty("password");
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConn(){
        Connection conn =null;
        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
