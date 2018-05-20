<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${role.id == 1}">
    <form action="${pageContext.request.contextPath}${path}" name="addUser" method="post">
        <input type="text" name="nameUser" title="nameUser" value="Имя пользователя">
        <input type="text" name="loginUser" title="loginUser" value="Логин пользователя">
        <input type="text" name="passwordUser" title="passwordUser" value="Пароль пользователя">
        <c:if test="${createRole == null}">
            <label>
                <select name="role">
                    <c:forEach var="role" items="${roles}">
                        <option value="${role.id}">${role.role}</option>
                    </c:forEach>
                </select>
            </label>
        </c:if>
        <c:if test="${createRole != null}">
            <input type="hidden" title="roleIdUser" name="roleIdUser" value="${createRole}">
        </c:if>
        <input type="submit" title="createUser" name="createUser" value="Создать">
    </form>
</c:if>
<table class="table-custom">
    <tr class="table-custom">
        <th class="table-custom">Id</th>
        <th class="table-custom">Login</th>
        <th class="table-custom">Name</th>
        <th class="table-custom">Role</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr class="table-custom">
            <td class="table-custom">${user.id}</td>
            <td class="table-custom">${user.login}</td>
            <td class="table-custom">
                <a href="${pageContext.request.contextPath}/users/${user.id}"> ${user.fullName}</a>
            </td>
            <td class="table-custom">${user.role.role}<br></td>
            <c:if test="${role.id == 1}">
                <td class="table-custom">
                    <form action="${pageContext.request.contextPath}${path}" name="deleteUser" method="post">
                        <input type="submit" title="deleteBtn" name="deleteBtn" value="Удалить">
                        <input hidden name="userId" value="${user.id}">
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>