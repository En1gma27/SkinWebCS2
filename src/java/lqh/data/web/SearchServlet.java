package lqh.data.web;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lqh.data.dao.ProductDao;
import lqh.data.dao.Database;
import lqh.data.models.Product;
import lqh.data.driver.MySQLDriver;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
        String productName = request.getParameter("productName");   
        try (Connection connection = MySQLDriver.getConnection()) {
            // Thực hiện truy vấn tìm kiếm sản phẩm
            
            String sql = "SELECT * FROM products WHERE name LIKE ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "%" + productName + "%");
                try (ResultSet rs = statement.executeQuery()) {
                    List<Product> listProduct = new ArrayList<>();
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
                    request.setAttribute("foundProducts", listProduct);
                }
            }
        } catch (SQLException ex) {
            // Xử lý ngoại lệ khi có lỗi kết nối hoặc truy vấn
            ex.printStackTrace();
        }

        // Chuyển hướng đến trang hiển thị kết quả tìm kiếm (searchResults.jsp)
        request.getRequestDispatcher("./views/searchResults.jsp").forward(request, response);
    }
}
