/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lqh.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lqh.data.dao.UserDao;
import lqh.data.driver.MySQLDriver;
import lqh.data.models.User;
import lqh.utils.API;


public class UserImpl implements UserDao{
    Connection con = MySQLDriver.getConnection();
    @Override
    public User findUser(String emailphone, String password) {
        String sql;
        if (emailphone.contains("@")) {
            sql = "SELECT * FROM users WHERE email = '" + emailphone+ " ' and password = '" + API.getMd5(password) + "' ";
        } else {
            sql = "SELECT * FROM users WHERE phone = '" + emailphone+ " ' and password = '" + API.getMd5(password) + "' ";
        }
        PreparedStatement sttm;
        try {
            sttm = con.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            if (rs.next()) return new User(rs);
        } catch (SQLException ex) {
            Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  null;
    }

    @Override
    public User findUser(String emailphone) {
        String sql;
        if (emailphone.contains("@")) {
            sql = "SELECT id,name,email,phone,password,role FROM users WHERE email = '" + emailphone+ "'" ;
        } else {
            sql = "SELECT id,name,email,phone,password,role FROM users WHERE phone = '" + emailphone+ "'";
        }
        PreparedStatement sttm;
        try {
            sttm = con.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            if (rs.next()) return new User(rs);
        } catch (SQLException ex) {
            Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  null;
    }

    @Override
    public void insertUser(String name, String email, String phone, String password) {
        String sql  = "insert into users(name, email, phone, password, role) "
                + "values ('"+ name+"','"+email+"','"+phone+"','"+password+"','')";
        
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.execute();
        } catch (Exception e) {
        }
    }
    //CHECK_EMAIL_ton tai hay khong trong database
    public boolean isEmailPhoneExists(String emailPhone) {
        String CHECK_EMAIL_EXISTENCE = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (Connection connection = MySQLDriver.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_EMAIL_EXISTENCE)) {
            preparedStatement.setString(1, emailPhone);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    //RESET_PASSWORD_SQL

    public void resetPassword(String emailPhone, String newPassword) {
        String RESET_PASSWORD_SQL = "UPDATE users SET password = ? WHERE email = ?";
        try (Connection connection = MySQLDriver.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RESET_PASSWORD_SQL)) {
            preparedStatement.setString(1, API.getMd5(newPassword));
            preparedStatement.setString(2, emailPhone);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //save VERIFICATION_CODE de cho cac lan quen pass sau
    public void saveVerificationCode(String email, String verificationCode) {
        String SAVE_VERIFICATION_CODE = "UPDATE users SET verification_code = ? WHERE email = ?";
        try (Connection connection = MySQLDriver.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_VERIFICATION_CODE)) {
            preparedStatement.setString(1, verificationCode);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //verify VerificationCode trong database co dung hay khong neu dung thi ok reset mk dc 
    public boolean verifyVerificationCode(String email, String verificationCode) {
    String sql = "SELECT * FROM users WHERE email = ? AND verification_code = ?";
    try (Connection connection = MySQLDriver.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, verificationCode);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            return resultSet.next();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}
    
}
