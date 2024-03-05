/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package lqh.data.web;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.util.Properties;
import java.util.UUID;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lqh.data.impl.UserImpl;

// VerifyCodeServlet.java
@WebServlet("/verifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy mã xác nhận, email và mật khẩu mới từ yêu cầu
        String verificationCode = request.getParameter("verificationCode");
        String email = request.getParameter("email");
        String newPassword = request.getParameter("newPassword");

        // Kiểm tra mã xác nhận có đúng không (đây là nơi bạn cần kiểm tra trong cơ sở dữ liệu)
        UserImpl userImpl = new UserImpl();
        if (userImpl.verifyVerificationCode(email, verificationCode)) {
            // Mã xác nhận đúng, thực hiện cập nhật mật khẩu mới
            userImpl.resetPassword(email, newPassword);

            // Chuyển hướng người dùng đến trang thông báo thành công hoặc trang đăng nhập
            request.setAttribute("sucsseee", "reset mkthanh cong");
            request.getRequestDispatcher("./views/login.jsp").include(request, response);
        } else {
            // Mã xác nhận không đúng, chuyển hướng người dùng đến trang nhập lại mã xác nhận
            request.setAttribute("ERRROR", "NHA PSAI CODE ROI THANG NGU");
            request.getRequestDispatcher("./views/verification-code-form.jsp").include(request, response);
        }
    }
}
