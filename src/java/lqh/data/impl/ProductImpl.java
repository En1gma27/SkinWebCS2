/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lqh.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lqh.data.dao.ProductDao;
import lqh.data.driver.MySQLDriver;
import lqh.data.models.Product;


public class ProductImpl implements ProductDao{
    Connection con = MySQLDriver.getConnection();
    @Override
    public List<Product> findAll() {
        List<Product> listProduct = new ArrayList<>();
        String sql = "select * from products";
        
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int id_category = rs.getInt("id_category");
                String name = rs.getString("name");
                String image = rs.getString("image");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                listProduct.add(new Product(id, id_category, name, image, price, quantity, status));
            }
            rs.close();
             
        } catch (SQLException ex) {
            Logger.getLogger(ProductImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return listProduct;
    }
    
    
        public List<Product> findByProductid(int product_id) {
        List<Product> listProduct = new ArrayList<>();
        String sql = "select * from products where product_id="+product_id;
        
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int id_category = rs.getInt("id_category");
                String name = rs.getString("name");
                String image = rs.getString("image");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                listProduct.add(new Product(id, id_category, name, image, price, quantity, status));
            }
            rs.close();
             
        } catch (SQLException ex) {
            Logger.getLogger(ProductImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return listProduct;
    }
    
    
    @Override
   public boolean add(Product product) {
    String sql = "INSERT INTO products (id_category, name, image, price, quantity, status) VALUES (?, ?, ?, ?, ?, ?)";
    try (PreparedStatement sttm = con.prepareStatement(sql)) {
         sttm.setInt(1, product.getId_category());
         sttm.setString(2, product.getName());
         sttm.setString(3, product.getImage());
         sttm.setDouble(4, product.getPrice());
         sttm.setInt(5, product.getQuantity());
         sttm.setBoolean(6, product.isStatus());

        int rowsAffected = sttm.executeUpdate();
            // Check if any rows were affected
            return rowsAffected > 0;
     
    } catch (SQLException ex) {
        Logger.getLogger(ProductImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}
  
    
    @Override
    public boolean update(Product product) {
       String sql = "UPDATE products SET id_category=?, name=?, image=?, price=?, quantity=?, status=? WHERE id=?";
    
    try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
        preparedStatement.setInt(1, product.getId_category());
        preparedStatement.setString(2, product.getName());
        preparedStatement.setString(3, product.getImage());
        preparedStatement.setDouble(4, product.getPrice());
        preparedStatement.setInt(5, product.getQuantity());
       
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException ex) {
        Logger.getLogger(ProductImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
    }
    
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM products WHERE id=?";

       try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
           preparedStatement.setInt(1, id);

           int rowsAffected = preparedStatement.executeUpdate();
           return rowsAffected > 0;
       } catch (SQLException ex) {
           Logger.getLogger(ProductImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;
    }
    @Override
    //tham số id_product trong hàm này đc hàm DoUpdate trong CartServlet tham chiếu tới khi người dùng nhập vào quantity 1 số 
   
    public Product findProduct(int id_product) {
        String sql = "select * from products where id = " + id_product;
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            if (rs.next()) return new Product(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
   

    @Override
    public boolean insert(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
