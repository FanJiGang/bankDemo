package cn.fan.test;

import cn.fan.utils.DBUtils;

import java.sql.Connection;

public class Demo02 {
    public static void main(String[] args) {
        Connection conn = DBUtils.getConn();
        System.out.println("conn = " + conn);
    }
}
