<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:header>
    <table>
        <tbody>
            <tr>
                <td><a href="<c:url value="/oauth2/authorization/google"/>">Google</a></td>
            </tr>
            <tr>
                <td><a href="<c:url value="/oauth2/authorization/facebook"/>">Facebook</a></td>
            </tr>
            <tr>
                <td><a href="<c:url value="/oauth2/authorization/github"/>">GitHub</a></td>
            </tr>
        </tbody>
    </table>
</tags:header>
