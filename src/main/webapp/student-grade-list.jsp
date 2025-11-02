<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Courses</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/student-grade.css">
</head>
<body>

    <div class="container">
        <h2>Ders Notları </h2>
        <form action="StudentServlet" method="post">
            <input type="hidden" name="command" value="LIST_GRADE">
            <table>
                <thead>
                    <tr>
                        <th>Ders Adı</th>
                        <th>Öğretmen Adı</th>
                        <th>Öğretmen Soyadı</th>
                        <th>Not 1</th>
                        <th>Not 2</th>
                        <th>Başarı Durumu</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="grades" items="${Grade_List}">
                        <tr>
                            <td>${grades.courseName}</td>
                            <td>${grades.firstName}</td>
                            <td>${grades.lastName}</td>
                            <td>${grades.note1}</td>
                            <td>${grades.note2}</td>
                            <td>${grades.result}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
    </div>

</body>
</html>
