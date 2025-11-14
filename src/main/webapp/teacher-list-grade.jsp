<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Not Listesi</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/teacher-list-grade.css">
</head>

<body>
<div class="container">
    <h2>Not Listesi</h2>

    <table>
        <thead>
        <tr>
            <th>Öğrenci Adı</th>
            <th>Öğrenci Soyadı</th>
            <th>Ders</th>
            <th>Not 1</th>
            <th>Not 2</th>
            <th>Durum</th>
            <th>İşlem</th>
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

                <td>
                    <a class="btn-list-update"
                       href="TeacherServlet?command=TEACHER_LOAD_GRADE&gradeId=${grade.gradeId}">
                        Güncelle
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
