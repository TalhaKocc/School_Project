<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Paneli</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin-dashboard.css">
</head>
<body>

    <div class="sidebar">
        <h2>Admin Paneli</h2>
        <a href="AdminServlet?command=ADMIN_LIST_TEACHER">Öğretmenleri Gör</a>
        <a href="AdminServlet?command=ADMIN_LIST_STUDENT">Öğrencileri Gör</a>
        <a href="AdminServlet?command=ADMIN_ADD_TEACHER">Yeni Öğretmen Ekle</a>
        <a href="AdminServlet?command=ADMIN_ADD_COURSE">Yeni Ders Ekle</a>
    </div>

    <div class="main">
        <h1>Hoşgeldin, Admin!</h1>
        <p>Sol menüden yönetim işlemlerini gerçekleştirebilirsin.</p>


    </div>

    <button class="logout-btn" onclick="window.location.href='index.jsp'">Çıkış Yap</button>

</body>
</html>
