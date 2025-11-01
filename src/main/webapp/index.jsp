<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8">
    <title>Okul Sistemi</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index-style.css">
</head>
<body>

    <div class="container">
        <div class="card">
            <h1>Okul Sistemine Hoşgeldiniz</h1>
            <p>Devam etmek için bir seçenek belirleyin:</p>
            <div class="buttons">
                <a href="${pageContext.request.contextPath}/login.jsp" class="btn btn-primary">Giriş Yap</a>
                <a href="${pageContext.request.contextPath}/register.jsp" class="btn btn-outline">Kayıt Ol</a>
            </div>
        </div>
    </div>

</body>
</html>
