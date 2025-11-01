<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Öğrenci Paneli</title>
<style>
    body {
        margin: 0;
        font-family: Arial, sans-serif;
        background-color: #f4f6f8;
    }

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

    
    .main {
        margin-left: 230px; 
        padding: 20px;
    }

</style>
</head>
<body>

    <div class="sidebar">
        <h2>Öğrenci Paneli</h2>
        <a href="StudentNotes.jsp"> Notları Gör</a>
        <a href="StudentCourses.jsp">Kayıtlı Dersleri Gör</a>
    </div>

    <div class="main">
        <h1>Hoşgeldin, Öğrenci!</h1>
        <p>Soldaki menüden işlemlerini yapabilirsin.</p>
    </div>

</body>
</html>