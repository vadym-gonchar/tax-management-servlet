<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<head>
    <meta charset="utf-8">
    <title>Login page</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</head>
<body>

<navbar:navbar/>

<div class="d-flex justify-content-center">
    <div id="loginbox" style="margin-top: 50px;"
         class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

        <div class="panel panel-info">

            <h5 class="panel-heading"><fmt:message key="login.signIn"/></h5>

            <div style="padding-top: 10px" class="panel-body">

                <div class="form-group">
                    <div class="col-xs-15">
                        <div>
                            <c:choose>
                                <c:when test="${requestScope.errorMsg != null}">
                                    <div class="alert alert-danger col-xs-offset-1 col-xs-10">
                                        <fmt:message key="login.error.nameOrPssw"/>
                                    </div>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>

                <form action="${pageContext.request.contextPath}/login"
                      method="POST" class="form-horizontal">

                    <!-- User name -->
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>

                        <fmt:message key="login.username" var="searchPlaceholder"/>
                        <input type="text" name="username" placeholder="${searchPlaceholder}" class="form-control"
                               autofocus required>
                    </div>

                    <!-- Password -->
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>

                        <fmt:message key="login.password" var="searchPlaceholder"/>
                        <input type="password" name="password" placeholder="${searchPlaceholder}" class="form-control"
                               autocomplete="on" required>
                    </div>

                    <!-- Login Button -->
                    <div style="margin-top: 10px" class="form-group">
                        <div class="controls">
                            <input type="submit" class="btn btn-success btn-sm"
                                   value="<fmt:message key="login.login"/>"/>
                        </div>
                    </div>

                </form>

            </div>

        </div>

        <a href="${pageContext.request.contextPath}/registration"><fmt:message key="login.register"/></a>
    </div>

</div>

</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

</html>