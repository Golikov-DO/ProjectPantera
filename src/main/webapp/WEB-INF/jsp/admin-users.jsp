<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <!--suppress HtmlDeprecatedTag -->
    <title>Управление пользователями</title>

    <script>
        function resetAllRows() {
            document.querySelectorAll("[id^='edit-']").forEach(e =>
                e.classList.add("hidden")
            );
            document.querySelectorAll("[id^='view-']").forEach(e =>
                e.classList.remove("hidden")
            );
        }

        function disableAddButton(disable) {
            document.getElementById("addBtn").disabled = disable;
        }

        function enableRowEdit(login) {
            if (!document.getElementById("addForm").classList.contains("hidden")) return;

            resetAllRows();

            document.getElementById("view-" + login).classList.add("hidden");
            document.getElementById("edit-" + login).classList.remove("hidden");

            disableAddButton(true);
        }

        function cancelRowEdit(login) {
            document.getElementById("edit-" + login).classList.add("hidden");
            document.getElementById("view-" + login).classList.remove("hidden");

            disableAddButton(false);
        }

        function showAddForm() {
            resetAllRows();

            document.querySelectorAll(".editBtn").forEach(b => b.disabled = true);

            document.getElementById("addBtn").classList.add("hidden");
            document.getElementById("addForm").classList.remove("hidden");
        }

        function cancelAddForm() {
            document.querySelectorAll(".editBtn").forEach(b => b.disabled = false);

            document.getElementById("addForm").classList.add("hidden");
            document.getElementById("addBtn").classList.remove("hidden");
        }
    </script>
</head>
<body>

<h2>Управление пользователями</h2>

<table class="admin-table">
    <tr>
        <th>Логин</th>
        <th>Роль</th>
        <th>Действия</th>
    </tr>

    <c:forEach var="u" items="${requestScope.users}">
        <tr>
            <td>${u.login()}</td>
            <td>${u.role()}</td>
            <td>

                <!-- ===== ПРОСМОТР ===== -->
                <div id="view-${u.login()}">
                    <button class="editBtn"
                            type="button"
                            onclick="enableRowEdit('${u.login()}')">
                        Изменить
                    </button>
                </div>

                <!-- ===== РЕДАКТИРОВАНИЕ ===== -->
                <div id="edit-${u.login()}" style="display:none">

                    <form action="admin-user-save" method="post" class="inline-form">
                        <input type="hidden" name="login" value="${u.login()}">

                        <label>
                            Пароль:
                            <input name="password" placeholder="новый пароль">
                        </label>

                        <label>
                            Роль:
                            <select name="role">
                                <option value="USER" ${u.role() == 'USER' ? 'selected' : ''}>USER</option>
                                <option value="ADMIN" ${u.role() == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
                            </select>
                        </label>

                        <button type="submit">Сохранить</button>
                    </form>

                    <c:choose>
                        <c:when test="${u.role() != 'ADMIN'}">
                            <form action="admin-user-delete" method="get" class="inline-form">
                                <input type="hidden" name="login" value="${u.login()}">
                                <button type="submit" style="color:red">Удалить</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <i>нельзя удалить</i>
                        </c:otherwise>
                    </c:choose>

                    <button type="button"
                            onclick="cancelRowEdit('${u.login()}')">
                        Отмена
                    </button>

                </div>

            </td>
        </tr>
    </c:forEach>
</table>

<hr>

<!-- ===== ДОБАВЛЕНИЕ ПОЛЬЗОВАТЕЛЯ ===== -->
<button id="addBtn" type="button" onclick="showAddForm()">
    Добавить нового пользователя
</button>

<div id="addForm" style="display:none; margin-top:15px">

    <h3>Новый пользователь</h3>

    <form action="admin-user-save" method="post">
        <label>
            Логин:
            <input name="login" required>
        </label><br><br>

        <label>
            Пароль:
            <input name="password" required>
        </label><br><br>

        <label>
            Роль:
            <select name="role">
                <option value="USER">USER</option>
                <option value="ADMIN">ADMIN</option>
            </select>
        </label><br><br>

        <button type="submit">Сохранить</button>
        <button type="button" onclick="cancelAddForm()">Отмена</button>
    </form>

</div>

<hr>

<a href="admin">← Вернуться в админ-панель</a>

</body>
</html>