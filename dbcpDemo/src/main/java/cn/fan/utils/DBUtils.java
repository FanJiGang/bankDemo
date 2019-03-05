package cn.fan.utils;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    private static DataSource ds;

    static {
        Properties pt = new Properties();
        try {
            pt.load(DBUtils.class.getClassLoader().getResourceAsStream("db.properties"));
            BasicDataSourceFactory bdsf = new BasicDataSourceFactory();
            ds=bdsf.createDataSource(pt);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     

    public static Connection getConn(){
        Connection connection=null;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Connection connection){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
