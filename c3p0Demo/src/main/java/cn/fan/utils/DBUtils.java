package cn.fan.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {

    private static ComboPooledDataSource cpds;

    static{
        cpds = new ComboPooledDataSource();
    }

    public static Connection getConn(){
        Connection conn=null;
        try {
            conn = cpds.getConnection();
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

    public static void commit(Connection conn){
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void rollback(Connection conn){
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
