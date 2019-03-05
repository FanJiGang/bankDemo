package cn.fan.dbcp;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class TestCase {

    BasicDataSource dataSource ;

    @Before
    public void init(){
        Properties properties = new Properties();
        try {
            properties.load(TestCase.class.getClassLoader().getResourceAsStream("db.properties"));
            String driver=properties.getProperty("driverClassName");
            String url=properties.getProperty("url");
            String username=properties.getProperty("username");
            String password=properties.getProperty("password");
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testDBCP(){
        Connection connection=null;
        try {
            connection = dataSource.getConnection();
            System.out.println("connection = " + connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
