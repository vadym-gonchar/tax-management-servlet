<%@ page import="java.time.format.DateTimeFormatter" %>
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
    <title>Do Report</title>

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

    <c:if test="${sessionScope.principal.role =='ROLE_USER'}">
    <p class="h5 mb-4">
        <fmt:message key="reportForm.preparedBy"/>
        ${sessionScope.principal.firstName} ${sessionScope.principal.lastName}
    </p>
    </c:if>

    <c:if test="${sessionScope.principal.role =='ROLE_ADMIN'}">
    <p class="h5 mb-4">
        <fmt:message key="reportForm.preparedBy"/>
        ${requestScope.reportUpdate.user.firstName} ${requestScope.reportUpdate.user.lastName}
    </p>
    </c:if>

    <form method="POST" class="form-horizontal mt-5" action="${reportUpdate.id != null ? '/updateDo' : '/create'}">
        <h6 class="mb-2"><fmt:message key="reportForm.reportDateLabel"/></h6>
        <!-- Date picker -->
        <div style="margin-bottom: 15px; max-width:450px" class="input-group">
            <input name="id" type="hidden" value="${reportUpdate.id}"/>

            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <fmt:message key="reportForm.reportDate" var="placeholder"/>

            <input autocomplete="off" data-date-format="dd/mm/yyyy" id="datepicker" type="text"
                   name="reportDate"
                   value="${reportUpdate.reportDate.format(DateTimeFormatter.ofPattern('dd/MM/yyyy'))}"
                   class="form-control"
                   placeholder="${placeholder}"
                   <c:if test="${sessionScope.principal.role == 'ROLE_ADMIN'}">readonly</c:if>
                    <c:choose>
                        <c:when test="${sessionScope.principal.role == 'ROLE_USER'}">
                            <c:if test="${requestScope.reportUpdate.status.name == 'approved'}">readonly</c:if>
                        </c:when>
                    </c:choose>
            />
        </div>

        <h6><fmt:message key="reportForm.totalRate"/><fmt:message key="reportForm.currency"/></h6>
        <!-- Add Rate -->
        <div style="margin-bottom: 15px; max-width:450px" class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <fmt:message key="reportForm.rate" var="placeholder"/>

            <input type="text" name="rate" autocomplete="off"
                   oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1');"
                   value="${reportUpdate.rate}"
                   class="form-control"
                   placeholder="${placeholder}"
                   <c:if test="${sessionScope.principal.role == 'ROLE_ADMIN'}">readonly</c:if>
                    <c:choose>
                        <c:when test="${sessionScope.principal.role == 'ROLE_USER'}">
                            <c:if test="${requestScope.reportUpdate.status.name == 'approved'}">readonly</c:if>
                        </c:when>
                    </c:choose>
            />
        </div>
        <!--=== Radio Buttons ===-->
        <c:if test="${sessionScope.principal.role =='ROLE_ADMIN'}">
            <c:if test="${requestScope.reportUpdate.status.name == 'pending'}">
                <div class="h6 mt-5">
                    <div class="font-italic"><p><fmt:message key="reportForm.label"/></p></div>
                    <div style="color:mediumseagreen">
                        <input checked type="radio" value="approved" name="status"/>
                        <fmt:message key="reportForm.approve"/>
                    </div>
                    <br>
                    <div style="color:orangered">
                        <input type="radio" value="rejected" name="status"/>
                        <fmt:message key="reportForm.reject"/>
                    </div>
                </div>
            </c:if>
        </c:if>
        <c:if test="${sessionScope.principal.role =='ROLE_USER'}">
            <input type="hidden" name="status" value="pending">
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
        <!--=== Comment Section ===-->
        <c:choose>
            <c:when test="${sessionScope.principal.role =='ROLE_ADMIN'}">
            <span><textarea rows="5" cols="53" ${requestScope.reportUpdate.status.name != 'pending'?'disabled="disabled"':''}
                            name="comment"><c:out value="${reportUpdate.comment}"/></textarea></span>
            </c:when >
            <c:otherwise>
            <span><textarea rows="5" cols="53" ${sessionScope.principal.role == 'ROLE_USER'?'disabled="disabled"':''}
                            name="comment"><c:out value="${reportUpdate.comment}"/></textarea></span>
            </c:otherwise>
        </c:choose>

        <br/>
        <!--=== Submit Button ===-->
        <c:if test="${sessionScope.principal.role =='ROLE_ADMIN'}">
            <c:if test="${requestScope.reportUpdate.status.name == 'pending'}">
                <button type="submit" class="btn btn-info col-2 mt-3"><fmt:message
                        key="reportForm.submitReportButton"/></button>
            </c:if>
        </c:if>

        <c:if test="${sessionScope.principal.role =='ROLE_USER'}">
            <c:if test="${requestScope.reportUpdate.status.name == null}">
                <button type="submit" class="btn btn-info col-2 mt-3"><fmt:message
                        key="reportForm.submitReportButton"/></button>
            </c:if>
            <c:if test="${requestScope.reportUpdate.status.name == 'pending'}">
                <button type="submit" class="btn btn-info col-2 mt-3"><fmt:message
                        key="reportForm.submitReportButton"/></button>
            </c:if>
            <c:if test="${requestScope.reportUpdate.status.name == 'rejected'}">
                <button type="submit" class="btn btn-info col-2 mt-3"><fmt:message
                        key="reportForm.submitReportButton"/></button>
            </c:if>
        </c:if>

    </form>
    <br/>
    <!--=== Back link ===-->
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