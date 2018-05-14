<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="../containers/head.jsp" %>
<body>

<div class="wrapper">

    <%@include file="../containers/header.jsp" %>

    <div class="middle">

        <div class="container">
            <main class="content">
                <c:forEach var="student" items="${students}">
                    ${student.id}
                    ${student.login}
                    <a href="/students?student=${student.fullName}"> ${student.fullName}</a>
                    ${student.role.role}<br>
                </c:forEach>
            </main><!-- .content -->
        </div><!-- .container-->

        <%@include file="../containers/sidebar.jsp" %>
    </div><!-- .middle-->

</div><!-- .wrapper -->
<%@include file="../containers/footer.jsp" %>
</body>
</html>