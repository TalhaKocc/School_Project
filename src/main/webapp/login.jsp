<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Giriş Yap</title>

<!-- Dış CSS dosyasına bağlanma (contextPath kullan) -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login-style.css">

</head>
<body>
    <div class="container">
        <h2>Okul Sistemine Giriş Yap</h2>
        <form action="<%=request.getContextPath()%>/login" method="post">
            <input class="input" type="email" name="email" placeholder="E-posta" required><br>
            <input class="input" type="password" name="password" placeholder="Şifre" required><br>
            <button class="btn" type="submit">Giriş Yap</button>
        </form>
        <a class="link" href="register.jsp">Hesabın yok mu? Kayıt ol</a>

        <% if (request.getAttribute("error") != null) { %>
            <p class="error"><%= request.getAttribute("error") %></p>
        <% } %>
    </div>
</body>
</html>
