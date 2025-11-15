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
    <h2>Kurs Ekle</h2>

    <form action="AdminServlet" method="post">
        <input type="hidden" name="command" value="ADMIN_ADD_COURSE">

        <div class="form-group">
            <label>Ders Adı:</label>
            <input type="text" name="courseName" required>
        </div>

        <div class="buttons">
            <button type="submit" class="btn-save">Kaydet</button>
            <a href="admin-dashboard.jsp" class="btn-cancel">İptal</a>
        </div>
    </form>
</div>

</body>
</html>
