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
            <input type="hidden" name="command" value="ADMIN_LIST_COURSE">
            <table>
                <thead>
                    <tr>
                        <th>Ders Id</th>
                        <th>Ders Adı</th>   
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="course" items="${Course_List}">
                        <tr>
                            <td>${course.courseId}</td>
                            <td>${course.courseName}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
    </div>

</body>
</html>
