<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@tag description="Page navigation bar" pageEncoding="UTF-8" %>
<%@attribute name="navbar" fragment="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>

<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">

    <div class="container">
        <c:if test="${sessionScope.authenticated == null || sessionScope.principal.role == null}">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                    <fmt:message key="navigation.info"/>
                </a>
        </c:if>
        <c:if test="${sessionScope.principal.role != null}">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
                    <fmt:message key="navigation.info"/>
                </a>
        </c:if>

        <div>
            <select id="locales">
                <option value=""><fmt:message key="lang.change"/></option>
                <option value="en"><fmt:message key="lang.en"/></option>
                <option value="ua"><fmt:message key="lang.ua"/></option>
            </select>
            <script type="text/javascript">
                $(document).ready(function () {
                    $("#locales").change(function () {
                        let selectedOption = $('#locales').val();
                        if (selectedOption !== '') {
                            window.location.replace('?locale=' + selectedOption);
                        }
                    });
                });
            </script>
        </div>
    </div>

    <c:if test="${sessionScope.authenticated != null}">
        <c:if test="${sessionScope.principal != null}">
            <div class="nav-item active mr-5">
        <span class="text-white align-middle">
                <fmt:message key="navigation.loggedAs"/>
            ${sessionScope.principal.firstName} ${sessionScope.principal.lastName}
        </span>
            </div>
            <div>
                <form action="${pageContext.request.contextPath}/logout" method="POST">
                    <button type="submit" class="btn btn-secondary btn-md">
                        <fmt:message key="navigation.logout"/>
                    </button>
                </form>
            </div>
        </c:if>
    </c:if>

</nav>

</body>
</html>