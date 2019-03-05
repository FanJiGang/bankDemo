package cn.fan.bank;

import cn.fan.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo1 {
    public static void main(String[] args) {
        Connection conn = DBUtils.getConn();
        try {
            conn.setAutoCommit(false);
            String dml="update account set money=money-? where id=?";
            PreparedStatement ps = conn.prepareStatement(dml);
            ps.setInt(1,100);
            ps.setInt(2,1);
            int i = ps.executeUpdate();
            if (i != 1) {
                throw new SQLException("扣款失败!");
            }
            ps.setInt(1,-100);
            ps.setInt(2,2);
            i=ps.executeUpdate();
            if (i != 1) {
                throw new SQLException("对方转账失败!");
            }
            DBUtils.commit(conn);
        } catch (SQLException e) {
            DBUtils.rollback(conn);
            e.printStackTrace();
        } finally {
            DBUtils.close(conn);
        }
    }
}
