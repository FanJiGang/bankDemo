package cn.fan.database;

import cn.fan.utils.JdbcUtil;
import cn.fan.utils.MyDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        DataSource ds = new MyDataSource();
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = ds.getConnection();
            String dql="select * from account where id=?";
            ps = conn.prepareStatement(dql);
            ps.setInt(1,3);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double money = resultSet.getDouble(3);
                System.out.println("id = " + id);
                System.out.println("name = " + name);
                System.out.println("money = " + money);
                System.out.println("----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            JdbcUtil.close(conn);
        }
    }
}
