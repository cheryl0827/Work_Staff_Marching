package com.example.work_staff_marching.cyf.dao;

import com.example.work_staff_marching.cyf.entity.DbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static Connection con= DbConn.getConn();
    private static PreparedStatement ps=null;
    private static ResultSet rs=null;
    public static boolean add_user(String userName,String indentificationCard,String phone,int countryID,String address,int role,String password,int registerStatus) throws SQLException {
        String sql="insert into user(userName,indentificationCard,phone,countryID,address,role,password,registerStatus) value (?,?,?,?,?,?,?,?)";
        boolean flag=false;
        ps=con.prepareStatement(sql);
        ps.setString(1,userName);
        ps.setString(2,indentificationCard);
        ps.setString(3,phone);
        ps.setInt(4,countryID);
        ps.setString(5,address);
        ps.setInt(6,role);
        ps.setString(7,password);
        ps.setInt(8,registerStatus);
        int count=ps.executeUpdate();
        if(count==1){
            flag=true;
        }
        else
            flag=false;
        return flag;
    }

}
