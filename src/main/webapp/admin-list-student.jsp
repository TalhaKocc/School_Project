<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Courses</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/student-course.css">
</head>
<body>

    <div class="container">
        <h2>Kayıtlı Derslerim </h2>
        <form action="AdminServlet" method="post">
            <input type="hidden" name="command" value="ADMIN_LIST_STUDENT">
            <table>
                <thead>
                    <tr>
                        <th>Öğrenci Adı</th>
                        <th>Öğrenci Soyadı</th>
                        <th>Öğrenci No</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="student" items="${Student_List}">
                        <tr>
                            <td>${student.name}</td>
                            <td>${student.surname}</td>
                            <td>${student.no}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
    </div>

</body>
</html>
