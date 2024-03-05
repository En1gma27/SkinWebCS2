<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Verification Code Form</title>
</head>
<body>
    <h2>Verification Code Form</h2>

    <form action="/verifyCodeServlet" method="post">
        <label for="verificationCode">Enter Verification Code:</label>
            <input type="text" name="verificationCode" required><br>

            <!-- Tr??ng ?? nh?p m?t kh?u m?i -->
            <label for="newPassword">Enter New Password:</label>
            <input type="password" name="newPassword" required><br>

           
             <label for="email">Enter mail:</label>
             <input type="mail" name="email" required><br>
            
            <input type="submit" value="Verify">
    </form>
</body>
</html>