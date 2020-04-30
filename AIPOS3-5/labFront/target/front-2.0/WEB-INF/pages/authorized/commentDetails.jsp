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
            <td>Content</td>
            <td>Description</td>
            <td>Author</td>
            <td>Save</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <c:set var="isDisable" value="${comment.author.id != currentUser.id}"/>
            <c:url var="saveUrl" value="/comment/${comment.id}"/>
            <form:form method="PUT" modelAttribute="comment" action="${saveUrl}">
                <input type="hidden" name="_method" value="PUT" form="description">
                <form:hidden path="id" form="comment"/>
                <td>
                    <form:textarea path="content" form="comment" disabled="${isDisable}"/>
                    <form:errors path="content" cssStyle="color: red"/>
                </td>
                <td>
                    <c:url var="descriptionUrl" value="/description/${comment.description.id}"/>
                    <a href="${descriptionUrl}">${comment.description.title}</a>
                    <form:hidden path="description.id" form="comment"/>
                    <form:hidden path="description.title" form="comment"/>
                </td>
                <td>
                    <c:url var="authorUrl" value="/user/${comment.author.id}"/>
                    <a href="${authorUrl}">${comment.author.username}</a>
                    <form:hidden path="author.id" form="comment"/>
                    <form:hidden path="author.username" form="comment"/>
                </td>
                <td>
                    <c:if test="${currentUser.id == comment.author.id}">
                        <form:button onclick="sendForm('comment', '${saveUrl}', 'PUT')">Update</form:button>
                    </c:if>
                </td>
            </form:form>
        </tr>
        </tbody>
    </table>
</tags:header>
