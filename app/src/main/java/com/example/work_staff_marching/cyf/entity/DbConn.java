package com.example.work_staff_marching.cyf.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConn {
    public static Connection conn = null;

    public static Connection getConn() {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            String driver_url = "jdbc:mysql://172.20.10.5:3306/wsm?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
            conn = DriverManager.getConnection(driver_url, "root", "");

            if (conn != null) {

                System.out.println("连接成功");

            } else {
                System.out.println("连接失败");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return conn;

    }

    public static void closeAll(Connection conn, PreparedStatement ps) {

        System.out.println("关闭成功");

        if (conn != null) {

            try {

                conn.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

        if (ps != null) {

            try {

                ps.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

    }



    public static void closeAll(Connection conn, PreparedStatement ps,
                                ResultSet rs) {

        if (conn != null) {

            try {

                conn.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

        if (ps != null) {

            try {

                ps.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

        if (rs != null) {

            try {

                rs.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

    }

}
