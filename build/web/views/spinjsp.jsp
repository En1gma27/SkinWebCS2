<%-- 
    Document   : spinjsp
    Created on : Dec 23, 2023, 11:28:35 PM
    Author     : quanghuy
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Spin to Win</title>
    <style>
        #slotReel {
            width: 300px;
            height: 150px;
            overflow: hidden;
            border: 1px solid #ccc;
        }

        #slotInner {
            display: flex;
            animation: none;
        }

        .slotItem {
            width: 100px;
            height: 150px;
            flex-shrink: 0;
        }

        @keyframes spinReel {
            to {
                transform: translateX(-500px);
            }
        }
    </style>
</head>
<body>

<div id="slotReel">
    <div id="slotInner">
        <div class="slotItem">Product A</div>
        <div class="slotItem">Product B</div>
        <div class="slotItem">Product C</div>
        <div class="slotItem">Product D</div>
        <div class="slotItem">Product E</div>
    </div>
</div>

<button onclick="spinReel()">Spin</button>

<div id="result"></div>

<script>
    function spinReel() {
        var slotReel = document.getElementById("slotInner");
        slotReel.style.animation = "none";
        
        for (let i = 0; i < 10; i++) {
            setTimeout(() => {
                slotReel.style.animation = null;
                slotReel.style.animation = "spinReel 2s ease-out 0s 1 normal";
            }, i * 2000);
        }

        setTimeout(() => {
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    document.getElementById("result").innerHTML = xhr.responseText;
                }
            };
            xhr.open("GET", "SpinServlet", true);
            xhr.send();
        }, 10 * 2000);
    }
</script>

</body>
</html>
