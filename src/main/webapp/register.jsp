<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1"/>
    <title>Kayıt Ol — Okul Sistemi</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/register-style.css"/>
</head>
<body>
<div class="page-wrapper">
    <div class="card">
        <div class="card-header">
            <h1>Hesap Oluştur</h1>
            <p>Hoşgeldiniz! Lütfen bilgilerinizi girin.</p>
        </div>

        <c:if test="${not empty errorMessage}">
            <div class="alert error">${errorMessage}</div>
        </c:if>
        <c:if test="${not empty successMessage}">
            <div class="alert success">${successMessage}</div>
        </c:if>

        <form id="registerForm" action="${pageContext.request.contextPath}/register" method="post" novalidate>
            <div class="form-row">
                <label for="first_name">Adınız</label>
                <input id="first_name" name="first_name" type="text" placeholder="İsim" required />
                <small class="hint">Adınızı girin</small>
            </div>

            <div class="form-row">
                <label for="last_name">Soyadınız</label>
                <input id="last_name" name="last_name" type="text" placeholder="Soyisim" required />
                <small class="hint">Soyadınızı girin</small>
            </div>

            <div class="form-row">
                <label for="email">Email</label>
                <input id="email" name="email" type="email" placeholder="mail@ornek.com" required />
                <small class="hint">Kullanılabilir bir e-posta girin</small>
            </div>

            <div class="form-row password-row">
                <label for="password">Şifre</label>
                <div class="password-wrap">
                    <input id="password" name="password" type="password" placeholder="Şifre" required minlength="8" />
                </div>
                
            </div>

            <div class="form-row">
                <button type="submit" class="btn-primary">Kayıt Ol</button>
            </div>
        </form>

        <div class="card-footer">
            <p>Zaten hesabınız var mı? <a href="${pageContext.request.contextPath}/login.jsp">Giriş Yap</a></p>
        </div>
    </div>
</div>

</body>
</html>
