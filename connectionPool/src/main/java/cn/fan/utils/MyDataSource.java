package cn.fan.utils;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
/**
 * 这是一个数据库连接池
 */
public class MyDataSource implements DataSource {

    List<Connection> list = new ArrayList<Connection>();

    //创建连接
    public MyDataSource() {
        for (int i = 0; i < 10; i++) {
            Connection conn = new ConnectionWrap(JdbcUtil.getConn(),list);
            list.add(conn);
        }
    }

    //该连接池对外公布的获取连接的方法
    @Override
    public Connection getConnection() throws SQLException {
        if ((list.size()==0)) {
            for (int i = 0; i < 5; i++) {
                list.add(JdbcUtil.getConn());
            }
        }
        return list.remove(0);
    }

    /*//归还连接
    public void addBack(Connection conn){
        list.add(conn);
    }*/

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
