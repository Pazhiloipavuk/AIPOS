<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAnonymous()" var="isAnonymous"/>
<c:choose>
    <c:when test="${isAnonymous}">
        <button onclick="followByLink('<c:url value="/login"/>')">Sign in</button>
    </c:when>
    <c:otherwise>
        <button onclick="followByLink('<c:url value="/logout"/>')">Sign out</button>
    </c:otherwise>
</c:choose>
    <jsp:doBody/>

