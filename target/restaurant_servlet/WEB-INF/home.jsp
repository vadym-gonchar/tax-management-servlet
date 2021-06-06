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
    <title>Reports</title>

    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>


    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap"
          rel="stylesheet">


    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>
    <script src="${pageContext.request.contextPath}/js/general.js"></script>

    <style>
        td.rejected {
            background: LightSalmon;
        }

        td.approved {
            background: PaleGreen;
        }

        td.pending {
            background: SeaShell;
        }

    </style>
</head>

<body>

<!--=== Navigation ===-->
<navbar:navbar/>

<div class="container">
    <br>
    <h3><fmt:message key="home.directoryName"/></h3>
    <br>

    <br>

    <!--=== USER ===-->
    <c:if test="${sessionScope.principal.role =='ROLE_USER'}">

        <div>
            <div class="btn-toolbar justify-content-between">
                <!--Create Report Button-->
                <div>
                    <h6><fmt:message key="home.createButtonLabel"/></h6>
                    <a class="btn custom-btn btn-primary"
                       href="${pageContext.request.contextPath}/create"><fmt:message key="home.createButton"/></a>
                </div>
                <!--Upload File-->
                <form action="${pageContext.request.contextPath}/report/upload" method="POST"
                      enctype="multipart/form-data">
                    <h6><fmt:message key="home.uploadButtonLabel"/></h6>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-primary" type="submit"><fmt:message key="home.uploadButton"/></button>
                        </div>
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" id="inputFile" name="file">
                            <label class="custom-file-label" for="inputFile" style="color:lightgray"><fmt:message key="home.chooseFile"/></label>
                        </div>
                    </div>
                </form>
                <!--Dropdown-->
                <form action="${pageContext.request.contextPath}/home" method="POST">
                    <div>
                        <h6><fmt:message key="home.dropdownLabel"/></h6>
                        <select id="status" name="status" class="form-control selectpicker"
                                onchange="this.form.submit(); return false">
                            <option value=""><fmt:message key="home.statusLabel"/></option>
                            <c:forEach var="s" items="${requestScope.statuses}">
                                <option ${s.name == requestScope.status?'selected="selected"':''} value="${s.name}">
                                    <fmt:message key="${s.name}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
        </div>
    </c:if>

    <!--=== ADMIN ===-->
<c:if test="${sessionScope.principal.role =='ROLE_ADMIN'}">
    <form action="${pageContext.request.contextPath}" method="POST">
        <div class="row">
            <!--Search field-->
            <div class="col-xs-6 col-md-4">
                <h6><fmt:message key="home.searchLabel"/></h6>
                <div class="input-group">
                    <fmt:message key="home.findBy" var="searchPlaceholder"/>
                    <input class="form-control  mb-3 " type="search" placeholder="${searchPlaceholder}"
                           onchange="this.form.submit(); return false;"/>
                    <input type="hidden" value=""/>
                    <div class="input-group-btn">
                        <button class="btn btn-success mb-3" type="submit"><fmt:message key="home.search"/></button>
                    </div>
                </div>
            </div>
            <div>
                <!--Dropdown-->
                <form action="${pageContext.request.contextPath}/home" method="POST">
                    <div>
                        <h6><fmt:message key="home.dropdownLabel"/></h6>
                        <select name="status" class="form-control selectpicker"
                                onchange="this.form.submit(); return false">
                            <option value=""><fmt:message key="home.statusLabel"/></option>
                            <c:forEach var="s" items="${requestScope.statuses}">
                                <option ${s.name == requestScope.status?'selected="selected"':''} value="${s.name}">
                                    <fmt:message key="${s.name}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
        </div>
    </form>
</c:if>
    <!--=== TABLE ===-->
    <table class="table table-bordered table-striped" id="table">
        <thead class="thead-dark">
        <tr>

            <c:if test="${sessionScope.principal.role =='ROLE_ADMIN'}">
                <th columns="0" onclick="tableSort(this, 'table')"><fmt:message key="home.viewUserReports"/></th>
            </c:if>

            <c:if test="${sessionScope.principal.role =='ROLE_USER'}">
                <th ><fmt:message key="home.report"/></th>
            </c:if>

            <th columns="1" onclick="tableSort(this, 'table')">
                <a href="#">
                    <fmt:message key="home.reportDate"/></a>
            </th>

            <th columns="2" onclick="tableSort(this, 'table')">
                <a href="#">
                    <fmt:message key="home.dateSubmitted"/></a>
            </th>

            <th columns="3" onclick="tableSort(this, 'table')">
                <a href="#">
                    <fmt:message key="home.status"/></a>
            </th>

        </tr>
        </thead>

        <tbody>
        <c:forEach var="reportItem" items="${requestScope.reportItems}">

            <c:if test="${requestScope.status eq reportItem.status}">

                <tr class="rows">
                    <td class="${reportItem.status}">
                        <c:if test="${sessionScope.principal.role =='ROLE_ADMIN'}">
                            <a href="${pageContext.request.contextPath}/openReportForUpdate/{reportId}(reportId=${reportItem.id})}">
                                ${reportItem.user.firstName} ${reportItem.user.lastName}</a>
                        </c:if>

                        <c:if test="${sessionScope.principal.role =='ROLE_USER'}">
                            <a href="${pageContext.request.contextPath}/openReportForUpdate/{reportId}(reportId=${reportItem.id})}">
                                <fmt:message key="home.viewReport"/>
                            </a>
                        </c:if>
                    </td>

                    <td class="${reportItem.status}">
                        <c:out value="${reportItem.reportDate}"/>
                    </td>

                    <td class="${reportItem.status}">
                        <parse:parseLocalDateTime value="${reportItem.createdAt}"/>
                    </td>

                    <td class="${reportItem.status}">
                        <fmt:message key="${reportItem.status}"/>
                    </td>
                </tr>
            </c:if>

            <c:if test="${empty requestScope.status}">
                <tr class="rows">
                    <td class="${reportItem.status}">
                        <c:if test="${sessionScope.principal.role =='ROLE_ADMIN'}">
                            <a href="${pageContext.request.contextPath}/openReportForUpdate/{reportId}(reportId=${reportItem.id})}">
                                    ${reportItem.user.firstName} ${reportItem.user.lastName}</a>
                        </c:if>

                        <c:if test="${sessionScope.principal.role =='ROLE_USER'}">
                            <a href="${pageContext.request.contextPath}/openReportForUpdate/{reportId}(reportId=${reportItem.id})}">
                                <fmt:message key="home.viewReport"/>
                            </a>
                        </c:if>
                    </td>

                    <td class="${reportItem.status}">
                        <c:out value="${reportItem.reportDate}"/>
                    </td>

                    <td class="${reportItem.status}">
                        <parse:parseLocalDateTime value="${reportItem.createdAt}"/>
                    </td>

                    <td class="${reportItem.status}">
                        <fmt:message key="${reportItem.status}"/>
                    </td>

                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>

    <!--=== Pagination ===-->
    <div>
        <div style="float: left;">
            <p><fmt:message key="page.current"/> ${requestScope.pageNo}</p>
        </div>

        <div style="float: right;">
            <%-- <nav aria-label="Page navigation" class="paging">
                 <ul class="pagination">

                     <li class="page-item">
                         <a class="page-link"><fmt:message key="home.previous"/></a>
                     </li>

                     <li class="page-item disabled">
                         <a class="page-link" href="#">...</a>
                     </li>

                     <li class="page-item">
                         <a class="page-link"><fmt:message key="home.next"/></a>
                     </li>

                 </ul>
             </nav>--%>

            <div class="pages">
                <div class="pagination">
                    <c:forEach var="i" begin="1" end="${requestScope.totalPages}" step="1">
                        <a href="${pageContext.request.contextPath}/home?page=${i}&sort=${requestScope.sort}&direct=${requestScope.direct}">
                                ${i} &nbsp; </a>
                    </c:forEach>
                </div>
            </div>

        </div>
    </div>

</div>
</body>
</html>
