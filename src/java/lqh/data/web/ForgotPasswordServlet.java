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
// ForgotPasswordServlet.java
@WebServlet(urlPatterns = {"/forgotPasswordServlet"})
public class ForgotPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin từ yêu cầu email 
        String email = request.getParameter("email");

        // Thực hiện kiểm tra sự tồn tại của emailPhone
        UserImpl userImpl = new UserImpl();
        boolean isEmailPhoneExists = userImpl.isEmailPhoneExists(email);

        if (isEmailPhoneExists) {
            // Tạo mã xác nhận ngẫu nhiên, lưu vào cơ sở dữ liệu và gửi đến email người dùng
            String verificationCode = generateRandomCode();
            userImpl.saveVerificationCode(email, verificationCode);

            // Gửi email với mã xác nhận đến người dùng
            sendVerificationEmail(email, verificationCode);

            // Chuyển người dùng đến trang nhập mã xác nhận
            request.setAttribute("email", email);
            request.getRequestDispatcher("./views/verification-code-form.jsp").include(request, response);
        } else {
            // Gửi người dùng đến trang thông báo lỗi (ví dụ: email không tồn tại)
          request.setAttribute("error_message", "Email does not exist");
          request.getRequestDispatcher("./views/forgot-password.jsp").include(request, response);
        }
    }

    private String generateRandomCode() {
        // Logic để tạo mã xác nhận ngẫu nhiên, có thể sử dụng thư viện UUID 
        return UUID.randomUUID().toString().substring(0, 6);
    }

    private void sendVerificationEmail(String email, String verificationCode) {
        // Thực hiện gửi email với JavaMail
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("email@gmail.com", "your_email_password");
        }
    });

    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("your_email@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Verification Code");
        message.setText("Your verification code is: " + verificationCode);

        Transport.send(message);
        System.out.println("Email sent to " + email + " with verification code: " + verificationCode);
    } catch (MessagingException e) {
        e.printStackTrace();
    }
    }
}