<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${role.id == 1}">
    <form action="${pageContext.request.contextPath}/roles" name="addRole" method="post">
        <input type="text" name="nameRole" title="nameRole" value="Название роли">
        <input type="submit" title="addRole" name="addRole" value="Создать">
    </form>
</c:if>
<table class="table-custom">
    <tr class="table-custom">
        <th class="table-custom">Id</th>
        <th class="table-custom">Role</th>
    </tr>
    <c:forEach var="roles" items="${roles}">
        <tr class="table-custom">
            <td class="table-custom">${roles.id}</td>
            <td class="table-custom">${roles.role}</td>
            <c:if test="${role.id == 1}">
                <td class="table-custom">
                    <form action="${pageContext.request.contextPath}/roles" name="deleteRole" method="post">
                        <input type="submit" title="deleteRole" name="deleteRole" value="Удалить">
                        <input hidden name="roleId" value="${roles.id}">
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>