<%--
  Created by IntelliJ IDEA.
  User: serge
  Date: 12.05.2018
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="../containers/head.jsp" %>
<body>

<div class="wrapper">

    <%@include file="../containers/header.jsp" %>

    <div class="middle">

        <div class="container">
            <main class="content">
                <%@include file="../containers/errorMessage.jsp" %>
                <%@include file="../containers/login.jsp" %>
                <br>
            </main>
        </div>

        <%@include file="../containers/sidebar.jsp" %>
    </div>

</div>
<%@include file="../containers/footer.jsp" %>
</body>
</html>
