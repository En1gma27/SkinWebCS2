/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package lqh.data.web;

import lqh.data.models.Product;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import lqh.data.dao.ProductDao;
import lqh.data.impl.ProductImpl;
import lqh.data.dao.Database;


@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    addProduct(request, response);
                    break;
                case "update":
                    updateProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
            }
        } else {
            // Default action: show product list
            showProductList(request,response);
        }    
         request.getRequestDispatcher("./views/admin.jsp").include(request, response);
 }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
    }

    private void showProductList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        List<Product> listProduct = Database.getProductDao().findAll();
        request.setAttribute("listProduct", listProduct);
       
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // Extract data from the form
        String name = request.getParameter("name");
        int id_category = Integer.parseInt(request.getParameter("id_category"));
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        Part imagePart = request.getPart("image");
        String imageName = extractImageName(imagePart);

        // Save the image to the server
        String imagePath = saveImage(imagePart, imageName);
        Product product = new Product();
        product.setName(name);
        product.setId_category(id_category);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setStatus(status);
        product.setImage(imagePath);

        // Add product vào database thông qua class ProductImpl
        ProductDao productDao = new ProductImpl();
        productDao.add(product);

        // Lấy danh sách sản phẩm mới và đặt nó vào thuộc tính request
        List<Product> updatedProductList = productDao.findAll();
        request.setAttribute("productList", updatedProductList);

        // Chuyển hướng đến trang JSP
        request.getRequestDispatcher("./views/admin.jsp").include(request, response);
}
    //extractImageName được thêm vào để trích xuất tên tệp từ phần header của phần multipart.
    private String extractImageName(Part part) {
            String contentDisposition = part.getHeader("content-disposition");
            String[] items = contentDisposition.split(";");
            for (String item : items) {
                if (item.trim().startsWith("filename")) {
                    return item.substring(item.indexOf("=") + 2, item.length() - 1);
                }
            }
            return "";
    }
    
 private String saveImage(Part part, String imageName) throws IOException {
    String uploadPath = getServletContext().getRealPath("/images"); // Đường dẫn tới thư mục lưu trữ ảnh
    String imagePath = uploadPath + File.separator + imageName;

    try (InputStream input = part.getInputStream();
        OutputStream output = new FileOutputStream(imagePath)) {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
    }

    return imagePath;
}

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Similar logic as addProduct, but update the existing product
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Extract the product ID from the request
        int productId = Integer.parseInt(request.getParameter("productId"));

        // Delete the product from the database
        ProductDao productDao = new ProductImpl();
        productDao.delete(productId);

        // Redirect to show the updated product list
        response.sendRedirect("AdminServlet");
    }
}