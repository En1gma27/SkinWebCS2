<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %> 
        <style>
        body {
            background-color: #000000;
        }
        </style>
            <div class="sort-buttons">
                <button class="sort-button" onclick="changeImage('left')">?</button>
                <button class="sort-button" onclick="changeImage('right')">?</button>
            </div>
           
             <img src="./assets/icon/download.png" alt="Best Skin Image" class="img-fluid" >
            <h1>
                <c:if test="${id_category==null}">
                  
                   
            </div>
                </c:if>
                
                <c:if test="${id_category!=null}">
                    <c:forEach items="${listCategory}" var = "category">
                        <c:if test="${category.id == id_category}">
                             
                        </c:if>
                    </c:forEach>
                </c:if>
            </h1>
          

        
