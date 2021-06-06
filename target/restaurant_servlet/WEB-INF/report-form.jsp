<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="parse" uri="custom.tld" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Create Report</title>

    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.min.css"
          rel="stylesheet"/>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style type="text/css">
        .datepicker td, .datepicker th {
            width: 1.5em;
            height: 1.5em;
        }
    </style>

</head>

<body>

<!--=== Navigation ===-->
<navbar:navbar/>

<div class="container">

    <p class="h4 mt-4"><fmt:message key="reportForm.taxReport"/></p>

    <p class="h5 mb-4">
        <fmt:message key="reportForm.preparedBy"/>
        ${sessionScope.principal.firstName} ${sessionScope.principal.lastName}
    </p>

    <form method="POST" class="form-horizontal mt-5" action="/create">

        <input name="id" class="hidden" type="text" value="${requestScope.reportUpdate.id}"/>

        <c:if test="${sessionScope.principal.role =='ROLE_USER'}">
            <h6 class="mb-2"><fmt:message key="reportForm.reportDateLabel"/></h6>
        </c:if>

        <!-- Date picker -->
        <input style="margin-bottom: 15px; max-width:450px" class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>

        <fmt:message key="reportForm.reportDate" var="placeholder"/>
        <input autocomplete="new-password" data-date-format="dd/mm/yyyy" id="datepicker" type="text" class="form-control"
               placeholder="${placeholder}"/>
        <span>
                <b><fmt:message key="reportForm.statementDate"/></b>
        </span>

        <input name="id" class="hidden" type="text" value="${requestScope.reportUpdate.id}"/>


        <c:if test="${sessionScope.principal.role =='ROLE_USER'}">
            <h6 class="mt-2"><fmt:message key="reportForm.rateLabel"/></h6>
        </c:if>

        <!-- Add Rate -->
        <div style="margin-bottom: 15px; max-width:450px" class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>

            <fmt:message key="reportForm.rate" var="placeholder"/>
            <input type="text" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1');"
                   class="form-control"
                   placeholder="${placeholder}"/>

            <span><b><fmt:message key="reportForm.totalRate"/>
                        <fmt:message key="reportForm.currency"/></b>
            </span>

            <input name="id" class="hidden" type="text" value="${requestScope.reportUpdate.id}"/>
        </div>

        <p><b><fmt:message key="reportForm.userType"/></b></p>
        <p><b><fmt:message key="reportForm.email"/></b></p>

        <c:if test="${sessionScope.principal.role =='ROLE_ADMIN'}">
            <div class="h6 mt-5">
                <div class="font-italic"><p><fmt:message key="reportForm.label"/></p></div>
                <div style="color:mediumseagreen"><input checked type="radio" value=""/></div>
                <br>
                <div style="color:orangered"><input type="radio" value=""/></div>
            </div>
        </c:if>
        <br>
        <c:if test="${sessionScope.principal.role =='ROLE_ADMIN'}">
            <p class="h6 mt-5 font-italic">
                <fmt:message key="reportForm.inspComnt"/>
            </p>
        </c:if>
        <c:if test="${sessionScope.principal.role =='ROLE_USER'}">
            <p class="h6 mt-5 font-italic">
                <fmt:message key="reportForm.userComnt"/>
            </p>
        </c:if>
        <c:if test="${sessionScope.principal.role =='ROLE_USER'}">
        <span>
            <textarea rows="5" cols="53" readonly></textarea>
        </span>
        </c:if>
        <c:if test="${sessionScope.principal.role =='ROLE_ADMIN'}">
        <span>
            <textarea rows="5" cols="53"></textarea>
        </span>
        </c:if>
        <br/>
        <c:if test="${sessionScope.principal.role =='ROLE_ADMIN'}">
            <button type="submit" class="btn btn-info col-2 mt-3"><fmt:message key="reportForm.submitReportButton"/>
            </button>
        </c:if>
        <c:if test="${sessionScope.principal.role =='ROLE_USER'}">
            <button type="submit" class="btn btn-info col-2 mt-3"><fmt:message key="reportForm.submitReportButton"/>
            </button>
        </c:if>
    </form>

    <br/>
    <a href="${pageContext.request.contextPath}/home"><fmt:message key="reportForm.back"/></a>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

<script type="text/javascript">
    $('#datepicker').datepicker({
        weekStart: 1,
        daysOfWeekHighlighted: "6,0",
        autoclose: true,
        todayHighlight: true,
    });
</script>

</body>
</html>