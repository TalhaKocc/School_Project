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
        <h2>📚 My Courses</h2>
        <form action="StudentServlet" method="post">
            <input type="hidden" name="command" value="LIST_COURSE">
            <table>
                <thead>
                    <tr>
                        <th>Course ID</th>
                        <th>Course Name</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="course" items="${Course_List}">
                        <tr>
                            <td>${course.id}</td>
                            <td>${course.name}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
    </div>

</body>
</html>
