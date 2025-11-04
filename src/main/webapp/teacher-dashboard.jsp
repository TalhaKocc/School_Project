<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Öğretmen Paneli</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/teacher-dashboard.css">
</head>
<body>

    <div class="sidebar">
        <h2>Öğretmen Paneli</h2>
        <a href="TeacherServlet?command=TEACHER_LIST_COURSE">Kayıtlı Dersleri Gör</a>
        <a href="TeacherServlet?command=TEACHER_ADD_GRADE">Not Girme Ekranı</a>
        <a href="TeacherServlet?command=TEACHER_UPDATE_GRADE">Not Güncelleme Ekranı</a>
        <a href="TeacherServlet?command=TEACHER_LIST_GRADE">Not Görme Ekranı</a>
    </div>

    <div class="main">
        <h1>Hoşgeldiniz, Öğretmen!</h1>
        <p>Soldaki menüden derslerinizi ve öğrenci notlarını yönetebilirsiniz.</p>
    </div>

    <button class="logout-btn" onclick="window.location.href='index.jsp'">Çıkış Yap</button>


</body>
</html>
