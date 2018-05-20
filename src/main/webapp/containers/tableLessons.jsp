<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table-custom">
    <c:if test="${role.id == 1 || role.id == 4}">
        <form action="${pageContext.request.contextPath}/lessons" name="createLesson" method="post">
            <label>
                <select name="subject">
                    <c:forEach var="subject" items="${subjects}">
                        <option value="${subject.id}">${subject.name}</option>
                    </c:forEach>
                </select>
            </label>
            <c:if test="${role.id == 1}">
                <label>
                    <select name="teacher">
                        <c:forEach var="teacher" items="${teachers}">
                            <option value="${teacher.id}">${teacher.fullName}</option>
                        </c:forEach>
                    </select>
                </label>
            </c:if>
            <label>
                <select name="student">
                    <c:forEach var="student" items="${students}">
                        <option value="${student.id}">${student.fullName}</option>
                    </c:forEach>
                </select>
            </label>
            <label>
                <select name="mark">
                    <c:forEach var="mark" items="${marks}">
                        <option value="${mark}">${mark}</option>
                    </c:forEach>
                </select>
            </label>

            <input type="checkbox" name="attendance" title="attendance" value="Attendance">
            <input type="submit" title="createLesson" name="createLesson" value="Создать">
        </form>
    </c:if>
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
            <c:if test="${role.id == 1 || role.id == 4}">
                <td class="table-custom">
                    <form action="${pageContext.request.contextPath}/lessons" name="deleteLesson" method="post">
                        <input type="submit" title="deleteBtn" name="deleteBtn" value="Удалить">
                        <input hidden name="lessonId" value="${lesson.id}">
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>