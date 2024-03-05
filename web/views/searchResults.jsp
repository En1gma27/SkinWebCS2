<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <!-- Thêm các th? head c?n thi?t -->
    <style>
        body {
            background-color: #495057; /* Màu xám nh?t */
        }
    </style>
</head>
<body>
    <div class="container my-5">
        <header class="mb-4">
            <h3 style="color: white;">Search Results</h3>
        </header>

        <c:if test="${foundProducts != null}">
            <div class="row">
                <c:forEach items="${foundProducts}" var="product">
                    <div class="col-lg-3 col-md-6 col-sm-6 d-flex">
                        <div class="card w-100 my-2 shadow-2-strong">
                            <img src="./assets/images/${product.image}" class="card-img-top" style="aspect-ratio: 1 / 1; background-color: #495057;" />
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title" style="color: white;">${product.name}</h5>
                                <p class="card-text" style="color: white;">${product.price}</p>
                                <p class="card-text" style="color: white;">${product.quantity}</p>
                                <div class="card-footer d-flex align-items-end pt-3 px-0 pb-0 mt-auto">
                                    <a href="<c:if test="${user == null}">login
                                   </c:if>
                                   <c:if test="${user != null}">home?id_product=${product.id}
                                   </c:if>
                                " class="btn btn-primary shadow-0 me-1">Add to cart</a>
                                    <a href="#!" class="btn btn-light border px-2 pt-2 icon-hover">
                                        
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <c:if test="${foundProducts == null}">
            <p>No results found for "${param.productName}"</p>
        </c:if>
    </div>
</body>
</html>
