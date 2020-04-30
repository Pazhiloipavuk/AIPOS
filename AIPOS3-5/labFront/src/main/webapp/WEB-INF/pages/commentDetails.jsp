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
            <td>Title of description</td>
            <td>Author</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <c:url var="saveUrl" value="/comment/${comment.id}"/>
            <form:form modelAttribute="comment">
                <td>
                    <form:textarea path="content" disabled="true"/>
                </td>
                <td>
                    <c:url var="descriptionUrl" value="/description/${comment.description.id}"/>
                    <a href="${descriptionUrl}">${comment.description.title}</a>
                </td>
                <td>
                    <c:url var="authorUrl" value="/user/${comment.author.id}"/>
                    <a href="${authorUrl}">${comment.author.username}</a>
                </td>
            </form:form>
        </tr>
        </tbody>
    </table>
</tags:header>
