<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Error</title>
</head>

<div class="backcolor"></div>
<div class="body-block shadow-large page_width">
    <section class="section1">
        <div>
            <a class="hbutton" href="${pageContext.request.contextPath}/home">
                <fmt:message key="main.main"/></a>
        </div>
        <div>
            <a id="authorizedLogin">${sessionScope.principal.userName}</a>
        </div>
    </section>

    <header class="section2">
        <a><h2><fmt:message key="error.page"/></h2></a>
    </header>

    <section class="section4">
        <main>
            <div class="frame">
                <h2 style="color: red; text-align: center">
                    ${pageContext.exception.toString().substring(pageContext.exception.toString().indexOf(':') + 2)}
                </h2>
            </div>
        </main>
    </section>
</div>
</body>

</html>