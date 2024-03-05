<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Management</title>
</head>
<body>

<h2>Product Management</h2>

<form action="AdminServlet" method="post" enctype="multipart/form-data">
    <label for="productName">Name:</label>
    <input type="text" name="productName" required><br>
    
    <label for="id_category">id_category:</label>
    <input type="text" name="id_category" required><br>

    <label for="price">Price:</label>
    <input type="number" name="price" required><br>

    <label for="quantity">Quantity:</label>
    <input type="number" name="quantity" required><br>

    <label for="image">Image:</label>
    <input type="file" name="image" accept="image/*" required><br>
    
    <label for="status">status:</label>
    <input type="number" name="status" required><br>

    <input type="hidden" name="action" value="add">
    <input type="submit" value="Add">
</form>

<table border="1">
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>id_category</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Image</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:forEach var="product" items="${listProduct}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.id_category}</td>
            <td>${product.price}</td>
            <td>${product.quantity}</td>
            <td><img src="${product.image}" alt="Product Image" width="50" height="50"></td>
            <td>${product.status}</td>
            <td>
                <form action="AdminServlet" method="post">
                    <input type="hidden" name="productId" value="${product.id}">
                    <input type="hidden" name="action" value="update">
                    <input type="submit" value="Update">
                </form>
                <form action="AdminServlet" method="post">
                    <input type="hidden" name="productId" value="${product.id}">
                    <input type="hidden" name="action" value="delete">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
