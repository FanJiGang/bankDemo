package cn.fan.c3p0;

import cn.fan.utils.DBUtils;

import java.sql.Connection;

public class Demo01 {
    public static void main(String[] args) {
        Connection conn = DBUtils.getConn();
        System.out.println("conn = " + conn);
    }
}
