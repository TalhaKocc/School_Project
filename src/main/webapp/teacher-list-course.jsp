<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8">
    <title>Öğrettiğim Dersler</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/teacher-list-course.css">
</head>
<body>
    <div class="container">
        <h2>📚 Öğrettiğim Dersler</h2>
		<form action="TeacherServlet" method="post">
			<input type="hidden" name="command" value="TEACHER_LIST_COURSE">
        <c:if test="${empty Teacher_Course_List}">
            <p>Hiç ders bulunamadı.</p>
        </c:if>

        <c:if test="${not empty Teacher_Course_List}">
            <table class="course-table">
                <thead>
                    <tr>
                        <th>Ders Adı</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="course" items="${Teacher_Course_List}" varStatus="st">
                        <tr>
                            <td>${course.courseName}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
		</form>
        <div class="actions">
            <a href="teacher-dashboard.jsp" class="btn">Geri Dön</a>
        </div>
        
    </div>
</body>
</html>
