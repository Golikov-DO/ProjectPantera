<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<!DOCTYPE html>
<html>
<head>
    <!--suppress HtmlDeprecatedTag -->
    <title>Текстовые квесты</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<div class="layout">

    <!-- ================= ЛЕВОЕ МЕНЮ ================= -->
    <div class="menu">

        <!-- ===== ИНФОРМАЦИЯ О ПОЛЬЗОВАТЕЛЕ ===== -->
        <p>
            <b>
                <c:choose>
                    <c:when test="${empty sessionScope.user}">
                        Пользователь:
                    </c:when>
                    <c:when test="${sessionScope.user.role == 'ADMIN'}">
                        Администратор
                    </c:when>
                    <c:otherwise>
                        Пользователь:
                    </c:otherwise>
                </c:choose>
            </b>

            <c:choose>
                <c:when test="${empty sessionScope.user}">
                    Гость
                </c:when>
                <c:when test="${sessionScope.user.role == 'ADMIN'}">
                    <!-- ничего не выводим -->
                </c:when>
                <c:otherwise>
                    ${sessionScope.user.login}
                </c:otherwise>
            </c:choose>
        </p>

        <hr>

        <h3>Меню</h3>

        <a href="index.jsp">Главная</a>
        <a href="quests">Выбрать квест</a>

        <c:if test="${sessionScope.user.role == 'ADMIN'}">
            <a href="admin">Админка</a>
        </c:if>

        <hr>

        <c:choose>
            <c:when test="${empty sessionScope.user}">
                <a href="login">Войти</a>
            </c:when>
            <c:otherwise>
                <a href="logout">Выйти</a>
            </c:otherwise>
        </c:choose>

    </div>

    <!-- ================= ЦЕНТРАЛЬНАЯ ОБЛАСТЬ ================= -->
    <div class="content">

        <c:choose>
            <c:when test="${not empty requestScope.view}">
                <jsp:include page="${requestScope.view}"/>
            </c:when>

            <c:otherwise>
                <h2>Добро пожаловать в текстовые квесты</h2>
                <a href="quests">Выбрать квест</a>
            </c:otherwise>
        </c:choose>

    </div>

</div>

</body>
</html>