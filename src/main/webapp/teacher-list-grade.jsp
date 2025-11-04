<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="tr">
<head>
<meta charset="UTF-8">
<title>Öğrenci Notları</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/teacher-list-grade.css">
</head>
<body>

<div class="container">
    <h2>📋 Öğrencilerin Notları</h2>
    
    <form action="TeacherServlet" method="post">
        <input type="hidden" name="command" value="TEACHER_LIST_GRADE">

        <c:if test="${empty Teacher_Grade_List}">
            <p class="no-data">Henüz not bilgisi bulunamadı.</p>
        </c:if>

        <c:if test="${not empty Teacher_Grade_List}">
            <table class="grade-table">
                <thead>
                    <tr>
                        <th>Öğrenci Adı</th>
                        <th>Öğrenci Soyadı</th>
                        <th>Ders Adı</th>
                        <th>Not 1</th>
                        <th>Not 2</th>
                        <th>Sonuç</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="grade" items="${Teacher_Grade_List}">
                        <tr>
                            <td>${grade.studentFirstName}</td>
                            <td>${grade.studentLastName}</td>
                            <td>${grade.courseName}</td>
                            <td>${grade.grade1}</td>
                            <td>${grade.grade2}</td>
                            <td>${grade.result}</td> 
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
