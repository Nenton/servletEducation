<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errorMsg != null}">

    <div id="login_message">
        <div class="msg error">
            <div class="msg_text"><b>Не удается войти.</b><br>Пожалуйста, проверьте правильность написания
                <b>логина</b> и <b>пароля</b>.
                <ul class="listing">
                    <li><span>Возможно, нажата клавиша CAPS-lock?</span></li>
                    <li><span>Может быть, у Вас включена неправильная <b>раскладка</b>? (русская или английская)</span>
                    </li>
                    <li><span>Попробуйте набрать свой пароль в текстовом редакторе и <b>скопировать</b> в графу «Пароль»</span>
                    </li>
                </ul>
            </div>
        </div>
    </div>

</c:if>

<c:if test="${message != null}">
    <div id="login_message">
        <p>
                ${message}
        </p>
    </div>
</c:if>