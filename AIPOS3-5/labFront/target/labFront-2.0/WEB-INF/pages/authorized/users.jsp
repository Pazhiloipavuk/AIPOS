<%@ page isErrorPage="true" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:header>
    <table>
        <thead>
        <tr>
            <td>Username</td>
            <td>Action</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>
                    <c:url var="userUrl" value="/user/${user.id}"/>
                    <button onclick="findById('${userUrl}')">${user.username}</button>
                </td>
                <td>
                    <c:url var="deleteUrl" value="/user/${user.id}"/>
                    <button onclick="deleteEntity('${deleteUrl}')">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</tags:header>
