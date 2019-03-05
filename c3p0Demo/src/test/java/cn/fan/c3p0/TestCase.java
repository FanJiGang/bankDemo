package cn.fan.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class TestCase {

    @Test
    public void testC3P0(){
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        Properties pt = new Properties();
        Connection conn=null;
        try {
            pt.load(TestCase.class.getClassLoader().getResourceAsStream("c3p0.properties"));
            String driver = pt.getProperty("driver");
            String url = pt.getProperty("url");
            String username = pt.getProperty("username");
            String password = pt.getProperty("password");
            int initSize = new Integer(pt.getProperty("initSize"));
            int maxActive = new Integer(pt.getProperty("maxActive"));
            dataSource.setDriverClass(driver);
            dataSource.setJdbcUrl(url);
            dataSource.setUser(username);
            dataSource.setPassword(password);
            dataSource.setInitialPoolSize(initSize);
            dataSource.setMaxPoolSize(maxActive);
            conn = dataSource.getConnection();
            System.out.println("conn = " + conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
