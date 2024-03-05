/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package lqh.data.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import lqh.data.dao.Database;
import lqh.data.models.Category;
import lqh.data.models.Product;
import lqh.data.models.User;


public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> listCategory = Database.getCategoryDao().findAll();
        request.setAttribute("list"
                + "Category", listCategory);
        List<Product> listProduct = Database.getProductDao().findAll();
        request.setAttribute("listProduct", listProduct);
        //set id_category attribute for display category by category id
        request.setAttribute("id_category", request.getParameter("id_category"));
        
        // them gio hang
        addProductToCart(request);
        
        request.setAttribute("title", "Home Page");
        request.getRequestDispatcher("./views/home.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // check auth
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login");
            return;
        } else {
            //Lay id cua product muon them vao don hang
            int productOrderId = Integer.parseInt(request.getParameter("product_id")) ;
            List<Integer> productOrderList = (List<Integer>) request.getSession().getAttribute("productOrderList");
            if (productOrderList == null) {
                productOrderList = new ArrayList<>();
            }

            // kiem  product order id co o trong list order chua? chua thi add
            if (!productOrderList.contains(productOrderId)) {
                productOrderList.add(productOrderId);
            }

            //list chua id product duoc dat hang
            request.getSession().setAttribute("productOrderList", productOrderList);
            request.getSession().setAttribute("productOrderCount", productOrderList.size());
        }
   
        // redirect lai trang home de hien thi so hang
        response.sendRedirect("home");
    }
    
    void addProductToCart(HttpServletRequest request) {
        int id_product;
        try {
            id_product = Integer.parseInt(request.getParameter("id_product"));
            
        } catch (Exception e) {
            id_product = 0;
        }
        
        List<Product> cart = (List<Product>)request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        
        if (id_product > 0) {
            Product product = Database.getProductDao().findProduct(id_product);
            boolean isProductInCart = false;
            for (Product pro: cart) {
                if (pro.getId() == id_product) {
                    pro.setQuantity(pro.getQuantity() + 1);
                    isProductInCart = true;
                }
            }
            if (!isProductInCart) {
                cart.add(product);
            }
        }
        request.getSession().setAttribute("cart", cart);

        
        
        
    }
  

}
