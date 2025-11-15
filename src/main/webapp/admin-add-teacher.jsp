<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Öğretmen Ekle</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin-add-teacher.css">
</head>
<body>

<div class="container">
    <h2>Öğretmen Ekle</h2>

    <form action="AdminServlet" method="post">
        <input type="hidden" name="command" value="ADMIN_ADD_TEACHER">

        <div class="form-group">
            <label>Ad:</label>
            <input type="text" name="name" required>
        </div>

        <div class="form-group">
            <label>Soyad:</label>
            <input type="text" name="surname" required>
        </div>

        <div class="form-group">
            <label>E-posta:</label>
            <input type="email" name="email" required>
        </div>

        <div class="form-group">
            <label>Şifre:</label>
            <input type="password" name="password" required>
        </div>

        <div class="form-group">
            <label>Rol:</label>
            <select name="role" required>
                <option value="Teacher">Teacher</option>
            </select>
        </div>

        <div class="form-group">
            <label>Maaş:</label>
            <input type="number" name="salary" step="0.01" required>
        </div>

        <div class="buttons">
            <button type="submit" class="btn-save">Kaydet</button>
            <a href="admin-dashboard.jsp" class="btn-cancel">İptal</a>
        </div>
    </form>
</div>

</body>
</html>
