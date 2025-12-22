<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<body>
<div class="container">


    <form class="form-horizontal" method=POST>
        <fieldset>

            <!-- Form Name -->
            <legend>Edit user ${requestScope.user.login}</legend>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="login">Login</label>
                <div class="col-md-4">
                    <input id="login" value="${requestScope.user.login}"
                           name="login" type="text" placeholder="placeholder"
                           class="form-control input-md">
                    <span class="help-block">help</span>
                </div>
            </div>

            <input type="hidden" id="id" value="${requestScope.user.id}"/>

            <!-- Password input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="password">Password</label>
                <div class="col-md-4">
                    <input id="password" value="${requestScope.user.password}"
                           name="password" type="password" placeholder="need not empty"
                           class="form-control input-md" required="">
                    <span class="help-block">3...32 symbols</span>
                </div>
            </div>

            <!-- Select Basic -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="role">Role</label>
                <div class="col-md-4">
                    <select id="role" name="role" class="form-control">
                        <c:forEach var="role" items="${applicationScope.roles}">
                            <option value="${role}" ${role==requestScope.user.role?"selected":""}>${role}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <!-- Button (Double) -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="create">Action</label>
                <div class="col-md-8">
                    <c:if test="${requestScope.user==null}">
                        <button id="create" value="createButton" name="create" class="btn btn-success">Create</button>
                    </c:if>
                    <c:if test="${requestScope.user!=null}">
                        <button id="update" value="updateButton" name="update" class="btn btn-info">Update</button>
                    </c:if>
                </div>
            </div>

        </fieldset>
    </form>
</div>
</body>
<%@include file="footer.jsp" %>

