<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Giriş Ekranı</title>
</head>
<body>
 <div align="center">
  <h1>Okul Sistemine Hoşgeldiniz</h1>
  <form action="<%=request.getContextPath()%>/login"method="post">
   <table style="with: 100%">
    <tr>
     <td>Eposta</td>
     <td><input type="text" name="username" /></td>
    </tr>
    <tr>
     <td>Şifre</td>
     <td><input type="password" name="password" /></td>
    </tr>
   </table>
   <input type="submit" value="Giriş Yap" />
  </form>
 </div>
</body>
</html>