<form action="${pageContext.request.contextPath}/forgotPasswordServlet" method="post">
   <!-- Thêm các tr??ng c?n thi?t, ví d?: -->
   <div class="form-outline mb-4">
      <input type="text" name="email" class="form-control form-control-lg"
         placeholder="Enter your email" />
      <label class="form-label" for="emailphone">Email address</label>
   </div>
   <div class="error-message">
    <c:out value="${error_message}" />
    </div>
   <button type="submit" class="btn btn-primary btn-lg">Submit</button>
</form>
