<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Öğretmen Paneli</title>
<style>
    body {
        margin: 0;
        font-family: Arial, sans-serif;
        background-color: #f4f6f8;
    }

    /* === SIDEBAR === */
    .sidebar {
        position: fixed;
        left: 0;
        top: 0;
        width: 230px;
        height: 100%;
        background-color: #1e1f26;
        color: white;
        display: flex;
        flex-direction: column;
        align-items: start;
        padding-top: 30px;
        box-shadow: 2px 0 10px rgba(0,0,0,0.3);
    }

    .sidebar h2 {
        font-size: 20px;
        text-align: center;
        width: 100%;
        margin-bottom: 30px;
        color: #00c4ff;
        letter-spacing: 1px;
    }

    .sidebar a {
        text-decoration: none;
        color: white;
        padding: 12px 20px;
        width: 100%;
        display: block;
        font-size: 16px;
        transition: all 0.3s;
    }

    .sidebar a:hover {
        background-color: #00c4ff;
        color: #1e1f26;
    }

    /* === MAIN CONTENT === */
    .main {
        margin-left: 230px;
        padding: 20px;
    }

</style>
</head>
<body>

    <div class="sidebar">
        <h2>Öğretmen Paneli</h2>
        <a href="teacherCourses.jsp">Kayıtlı Dersleri Gör</a>
        <a href="addGrades.jsp">Not Girme Ve Güncelleme Ekranı</a>
        <a href="updateGrades.jsp">Not Görme Ekranı</a>
    </div>

    <div class="main">
        <h1>Hoşgeldiniz, Öğretmen!</h1>
        <p>Soldaki menüden derslerinizi ve öğrenci notlarını yönetebilirsiniz.</p>
    </div>

</body>
</html>
