<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Not Güncelle</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/teacher-update-grade.css">
</head>
<body>
<div class="container">
    <h2>Not Güncelle</h2>

    <!-- Hata mesajı -->
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <!-- Not bulunamazsa -->
    <c:if test="${grade == null}">
        <p style="color: red; text-align: center;">Not bulunamadı!</p>
        <div style="text-align: center;">
            <a href="TeacherServlet?command=TEACHER_LIST_GRADE" class="btn-cancel">Geri Dön</a>
        </div>
    </c:if>

    <!-- Not bulunduysa formu göster -->
    <c:if test="${grade != null}">
        <form action="TeacherServlet" method="post">
            <input type="hidden" name="command" value="TEACHER_UPDATE_GRADE">
            <input type="hidden" name="studentId" value="${grade.studentId}">
            <input type="hidden" name="courseId" value="${grade.courseId}">

            <div class="form-group">
                <label>Öğrenci:</label>
                <span>${grade.studentFirstName} ${grade.studentLastName}</span>
            </div>

            <div class="form-group">
                <label>Ders:</label>
                <span>${grade.courseName}</span>
            </div>

            <div class="form-group">
                <label>Not 1:</label>
                <input type="number" name="grade1" value="${grade.grade1}" min="0" max="100" step="0.01" required>
            </div>

            <div class="form-group">
                <label>Not 2:</label>
                <input type="number" name="grade2" value="${grade.grade2}" min="0" max="100" step="0.01" required>
            </div>

            <div class="form-group">
                <label>Durum:</label>
                <select name="result" required>
                    <option value="Geçti" ${grade.result == 'Geçti' ? 'selected' : ''}>Geçti</option>
                    <option value="Kaldı" ${grade.result == 'Kaldı' ? 'selected' : ''}>Kaldı</option>
                </select>
            </div>

            <div style="text-align: center; margin-top: 30px;">
                <button type="submit" class="btn-form-save">Kaydet</button>
                <a href="TeacherServlet?command=TEACHER_LIST_GRADE" class="btn-cancel">İptal</a>
            </div>
        </form>
    </c:if>
</div>
</body>
</html>
