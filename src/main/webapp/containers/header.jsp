<%--
  Created by IntelliJ IDEA.
  User: serge
  Date: 13.05.2018
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="header">
    <H1>Система учета успеваемости (alfa v 0.1)</H1>
    <%if (request.getSession().getAttribute("login") != null) {%>
    <div class="logout">
        <%=(String) request.getSession().getAttribute("login")%>
        <a href="${pageContext.request.contextPath}/login?action=logout">Выйти</a>
    </div>
    <%}%>
</header>
<!-- .header-->
