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
        <a href="AdminServlet?command=LIST_TEACHER">Öğretmenleri Gör</a>
        <a href="AdminServlet?command=LIST_STUDENT">Öğrencileri Gör</a>
        <a href="AdminServlet?command=EDIT_GRADES">Not Düzenle</a>
        <a href="AdminServlet?command=ADD_COURSE">Yeni Ders Ekle</a>
    </div>

    <div class="main">
        <h1>Hoşgeldin, Admin!</h1>
        <p>Sol menüden yönetim işlemlerini gerçekleştirebilirsin.</p>

        <div class="info-box">
            <h3>Yönetim Özeti</h3>
            <ul>
                <li>Toplam Öğretmen: <strong>${teacherCount}</strong></li>
                <li>Toplam Öğrenci: <strong>${studentCount}</strong></li>
                <li>Toplam Ders: <strong>${courseCount}</strong></li>
            </ul>
        </div>
    </div>

    <button class="logout-btn" onclick="window.location.href='index.jsp'">Çıkış Yap</button>

</body>
</html>
