<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@include file="../containers/head.jsp" %>
<body>

<div class="wrapper">

    <%@include file="../containers/header.jsp" %>

    <div class="middle">

        <div class="container">
            <main class="content">
            <a href="${pageContext.request.contextPath}/login">Войти</a>
            </main>
        </div><!-- .container-->

        <%@include file="../containers/sidebar.jsp" %>
    </div><!-- .middle-->

</div><!-- .wrapper -->
<%@include file="../containers/footer.jsp" %>
</body>
</html>
