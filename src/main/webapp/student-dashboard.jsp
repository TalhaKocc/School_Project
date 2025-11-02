<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Öğrenci Paneli</title>


<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/student-dashboard.css">
</head>
<body>

    <div class="sidebar">
        <h2>Öğrenci Paneli</h2>
        <a href="student-grade-list.jsp">Notları Gör</a>
        <a href="StudentServlet?command=LIST_COURSE">Dersleri Gör</a>
    </div>

    <div class="main">
        <h1>Hoşgeldin, Öğrenci!</h1>
        <p>Soldaki menüden işlemlerini yapabilirsin.</p>
    </div>

    <button class="logout-btn" onclick="window.location.href='index.jsp'">Çıkış Yap</button>

</body>
</html>
