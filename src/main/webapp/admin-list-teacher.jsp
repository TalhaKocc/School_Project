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
            <input type="hidden" name="command" value="ADMIN_LIST_TEACHER">
            <table>
                <thead>
                    <tr>
                        <th>Öğretmen Adı</th>
                        <th>Öğretmen Soyadı</th>
                        <th>Öğretmen Maaşı</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="teacher" items="${Teacher_List}">
                        <tr>
                            <td>${teacher.name}</td>
                            <td>${teacher.surname}</td>
                            <td>${teacher.salary}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
    </div>

</body>
</html>
