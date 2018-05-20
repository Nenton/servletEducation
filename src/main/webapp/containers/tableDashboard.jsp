<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table-custom">
    <tr class="table-custom">
        <th class="table-custom">Id</th>
        <th class="table-custom">Subject</th>
        <th class="table-custom">Teacher</th>
        <th class="table-custom">Student</th>
        <th class="table-custom">Mark</th>
        <th class="table-custom">Attendance</th>
    </tr>
    <c:forEach var="lesson" items="${lessons}">
        <tr class="table-custom">
            <td class="table-custom">${lesson.id}</td>
            <td class="table-custom">${lesson.subject.name}</td>
            <td class="table-custom">
                <a href="${pageContext.request.contextPath}/users/${lesson.teacher.id}"> ${lesson.teacher.fullName}</a>
            </td>
            <td class="table-custom">
                <a href="${pageContext.request.contextPath}/users/${lesson.student.id}"> ${lesson.student.fullName}</a>
            </td>
            <td class="table-custom">${lesson.mark}</td>
            <td class="table-custom">${lesson.attendance}</td>
        </tr>
    </c:forEach>
</table>