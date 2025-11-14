<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Not Ekleme</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/teacher-add-grade.css">
</head>
<body>
<div class="container">
    <h2>Not Ekleme Ekranı</h2>

    <!-- Hata ve başarı mesajları -->
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="success">${success}</div>
    </c:if>

    <form action="TeacherServlet" method="post" accept-charset="UTF-8">
        <input type="hidden" name="command" 
               value="${not empty selectedCourseId ? 'TEACHER_ADD_GRADE' : 'LOAD_ADD_GRADE_PAGE'}"/>

        <!-- Ders Seçimi -->
        <label for="courseId">Ders Seç:</label>
        <select name="courseId" id="courseId" required onchange="this.form.submit()">
            <option value="">-- Ders seçin --</option>
            <c:forEach var="course" items="${Teacher_Course_List}">
                <option value="${course.courseId}" 
                    <c:if test="${course.courseId == selectedCourseId}">selected</c:if>>
                    ${course.courseName}
                </option>
            </c:forEach>
        </select>

        <!-- Ders seçildiyse öğrenci ve not alanları -->
        <c:if test="${not empty selectedCourseId}">
            <label for="studentId">Öğrenci Seç:</label>
            <select name="studentId" id="studentId" required>
                <option value="">-- Öğrenci seçin --</option>
                <c:forEach var="student" items="${Student_List}">
                    <option value="${student.studentId}">
                        ${student.firstName} ${student.lastName}
                    </option>
                </c:forEach>
            </select>

            <c:if test="${empty Student_List}">
                <p class="info">Bu derse kayıtlı öğrenci bulunmamaktadır.</p>
            </c:if>

            <label for="grade1">Not 1:</label>
            <input type="number" name="grade1" id="grade1" required min="0" max="100" placeholder="0-100"/>

            <label for="grade2">Not 2:</label>
            <input type="number" name="grade2" id="grade2" required min="0" max="100" placeholder="0-100"/>

            <label for="result">Sonuç:</label>
            <input type="text" name="result" id="result" required placeholder="Geçti / Kaldı"/>

            <button type="submit">Notu Kaydet</button>
        </c:if>
    </form>


</div>
</body>
</html>
