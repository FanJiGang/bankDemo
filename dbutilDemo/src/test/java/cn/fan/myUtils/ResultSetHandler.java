package cn.fan.myUtils;

import java.sql.ResultSet;

public interface ResultSetHandler<T> {
    /*
    定义了数据封装的规范
     */
    T handle(ResultSet rs);
}
