/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lqh.data.dao;

import java.util.List;
import lqh.data.models.Product;


public interface ProductDao {
    public List<Product> findAll();
    public List<Product> findByProductid(int product_id);
    public boolean add(Product product);
    public boolean insert(Product product);
    public boolean update(Product product);
    public boolean delete(int id);
    public Product findProduct(int id);
    
}
