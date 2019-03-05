package cn.fan.myUtils;

import cn.fan.entity.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.*;

public class CommonUtils {
    private static ComboPooledDataSource dataSource;

    static{
        dataSource = new ComboPooledDataSource();
    }

    /*
    测试通用的查询方法
     */
    @Test
    public void testQuery(){
        User user = query("select * from account where id=?", new ResultSetHandler<User>() {
            @Override
            public User handle(ResultSet rs) {
                User user = null;
                try {
                    while (rs.next()) {
                        user = new User(rs.getInt(1), rs.getString(2), rs.getDouble(3));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return user;
            }
        }, 3);
        System.out.println("user = " + user);
    }
    /*
    * 测试通用的增/删/改方法
    */
    @Test
    public void testUpdate(){
        int count = update("insert into account values(null,?,?)", "xiaoming", 4000);
        System.out.println("count = " + count);
    }

    //通用的增/删/改方法
    public int update(String sql,Object... args){
        Connection conn=null;
        int i=0;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ParameterMetaData pmd = ps.getParameterMetaData();
            for (int j = 0; j < pmd.getParameterCount(); j++) {
                ps.setObject(j+1,args[j]);
            }
            i = ps.executeUpdate();
        } catch (SQLException e) {
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
        return i;
    }

    //通用的查询方法
    public <T> T query(String sql,ResultSetHandler<T> resultSetHandler,Object... args){
        Connection conn=null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ParameterMetaData pmd = ps.getParameterMetaData();
            for (int i = 0; i < pmd.getParameterCount(); i++) {
                ps.setObject(i+1,args[i]);
            }
            ResultSet rs = ps.executeQuery();
            return resultSetHandler.handle(rs);
        } catch (SQLException e) {
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
        return null;
    }
}
