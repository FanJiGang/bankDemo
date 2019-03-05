package cn.fan.dbUtil;

import cn.fan.entity.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestCase {

    @Test
    public void testAdd(){
        /*
        dbutils只是帮我们简化了CRUD的代码,但是连接的创建及获取工作,
        不在考虑范围
         */
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
        String dml="update account set money=? where id=?";
        String dql="select * from account where id<?";
        try {
            /*int update = qr.update(dml, "1234", "2");
            System.out.println("update = " + update);*/

            /*List<User> list = qr.query(dql, new ResultSetHandler<List<User>>() {
                @Override
                public List<User> handle(ResultSet resultSet) throws SQLException {
                    List<User> list = new ArrayList<User>();
                    while (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        double money = resultSet.getDouble(3);
                        list.add(new User(id, name, money));
                    }
                    return list;
                }
            }, 3);*/
            List<User> list = qr.query(dql, new BeanListHandler<User>(User.class), 5);
            for (User user : list) {
                System.out.println("user = " + user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
