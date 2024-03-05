/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package lqh.data.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lqh.data.dao.Database;
import lqh.data.models.Product;
import lqh.data.models.User;


@WebServlet(name = "ShoppingCartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

   

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("clear")!=null){
           request.getSession().setAttribute("cart", new ArrayList<>());
        }
        request.setAttribute("title", " Cart Page");
        request.getRequestDispatcher("./views/cart.jsp").include(request, response);
            // Lấy tham số sắp xếp từ URL
        String sortBy = request.getParameter("sortBy");
        // Lấy danh sách sản phẩm từ cart 
        List<Product> cart = (List<Product>) request.getSession().getAttribute("cart");
        // Nếu có yêu cầu sắp xếp, thực hiện sắp xếp
        if (sortBy != null && !sortBy.isEmpty()) {
            sortCart(cart, sortBy);
        }
        // Chuyển thông tin danh sách sản phẩm đã sắp xếp vào request attribute
        request.setAttribute("cart", cart);
        // Chuyển hướng đến trang hiển thị giỏ hàng (cart.jsp)
        request.getRequestDispatcher("./víews/cart.jsp").include(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       updateDelete(request,response);
        
        
    }

   //hàm update quantity cảu product
  void DoUpdate(HttpServletRequest request, int id_product) {
    List<Product> cart = (List<Product>) request.getSession().getAttribute("cart");
    int quantity = Integer.parseInt(request.getParameter("quantity"));
    for (Product pro : cart) {
        if (pro.getId() == id_product && quantity > 0) {
            pro.setQuantity(quantity);
            break;
        }
    }

    request.getSession().setAttribute("cart", cart);
}
 void DoDelete (HttpServletRequest request, int id) {
    List<Product> cart = (List<Product>) request.getSession().getAttribute("cart");

    for (Product pro : cart) {
        if (pro.getId() == id ) {
            cart.remove(pro);
            break;
        }
    }

    request.getSession().setAttribute("cart", cart);
}
  void updateDelete (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
     String action = request.getParameter("action");
        int id_product = Integer.parseInt(request.getParameter("id_product"));

        switch (action) {
            case "update":
               DoUpdate(request, id_product);
                break;
            case "delete":
                 DoDelete (request,id_product);
                break;
        }
          request.getRequestDispatcher("./views/cart.jsp").include(request, response);
    }  
  //ham sort dung thuat toan bubble sort
  void sortCart(List<Product> cart, String sortBy) {
    for (int i = 0; i < cart.size() - 1; i++) {
        for (int j = 0; j < cart.size() - i - 1; j++) {
            Product product1 = cart.get(j);
            Product product2 = cart.get(j + 1);

            // So sánh giá sản phẩm để xác định thứ tự sắp xếp
            int compareResult = 0;

            if ("priceAsc".equals(sortBy)) {
                compareResult = Double.compare(product1.getPrice(), product2.getPrice());
            } else if ("priceDesc".equals(sortBy)) {
                compareResult = Double.compare(product2.getPrice(), product1.getPrice());
            }

            // Nếu cần sắp xếp, đổi chỗ hai sản phẩm
            if (compareResult > 0) {
                Collections.swap(cart, j, j + 1);
            }
        }
    }
}
}
    
