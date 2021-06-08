<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<head>
    <meta charset="utf-8">
    <title>Registration Page</title>

    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
    <script src="${request.contextPath}/js/signup_request.js"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</head>

<body>

<navbar:navbar/>

<div class="d-flex justify-content-center">

    <div id="loginbox" style="margin-top: 30px;"
         class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

        <div class="panel panel-primary">

            <h5 class="panel-heading"><fmt:message key="registration.info"/></h5>

            <div style="padding-top: 15px" class="panel-body">

                <section ng-app="get_form" ng-controller="GetController">
                    <form method="POST" class="form-horizontal">

                        <!-- User name -->
                        <div style="margin-bottom: 15px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <fmt:message key="registration.username" var="placeholder"/>
                            <input id="login" type="text" placeholder="${placeholder}" class="form-control" autofocus required/>
                        </div>

                        <!-- Password -->
                        <div style="margin-bottom: 15px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                            <fmt:message key="registration.password" var="placeholder"/>
                            <input id="password" type="password" placeholder="${placeholder}" name="password"
                                   class="form-control" autocomplete="on" required/>
                        </div>

                        <!-- Confirm Password -->
                        <div style="margin-bottom: 15px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                            <fmt:message key="registration.psswConfirm" var="placeholder"/>
                            <input id="confirmpassword" type="password" placeholder="${placeholder}" name="confirmpassword"
                                   class="form-control" autocomplete="on" required/>
                        </div>

                        <!-- First name -->
                        <div style="margin-bottom: 15px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <fmt:message key="registration.firstName" var="placeholder"/>
                            <input id="firstName" type="text" placeholder="${placeholder}" class="form-control" required/>
                        </div>

                        <!-- Last name -->
                        <div style="margin-bottom: 15px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <fmt:message key="registration.lastName" var="placeholder"/>
                            <input id="lastName" type="text" placeholder="${placeholder}" class="form-control" required/>
                        </div>

                        <!-- Email -->
                        <div style="margin-bottom: 15px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <fmt:message key="registration.email" var="placeholder"/>
                            <input id="email" type="email" placeholder="${placeholder}" class="form-control" required/>
                        </div>

                        <!-- Register Button -->
                        <div style="margin-top: 10px" class="form-group">
                            <div class="controls">
                                <input class="btn btn-primary btn-signup" type="submit"
                                       ng-click="signup('${pageContext.request.contextPath}/registration')"
                                       value="<fmt:message key="registration.register"/>"/>
                                <button type="reset" class="btn btn-light">
                                    <fmt:message key="registration.reset"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </section>

            </div>

        </div>

    </div>

</div>

</body>

</html>

