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
            <td>Title</td>
            <td>Content</td>
            <td>Task</td>
            <td>Author</td>
        </tr>
        </thead>
        <tbody>
        <c:url var="saveUrl" value="/description/${description.id}"/>
        <form:form modelAttribute="description">
            <tr>
                <td>
                    <form:input path="title" disabled="true"/>
                </td>
                <td>
                    <form:textarea path="content" disabled="true"/>
                </td>
                <td>
                    <form:select path="task.id" multiple="false" disabled="true">
                        <form:option value="${description.task.id}" label="${description.task.name}"/>
                    </form:select>
                </td>
                <td>
                    <form:select path="author.id" multiple="false" disabled="true">
                        <form:option value="${description.author.id}" label="${description.author.username}"/>
                    </form:select>
                </td>
            </tr>
        </form:form>
        </tbody>
    </table>


    <h2>Comments of description</h2>
    <table>
        <thead>
        <tr>
            <td>Content</td>
            <td>Author</td>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not description.comments.isEmpty()}">
            <c:forEach var="comment" items="${description.comments}">
                <tr>
                    <td>
                            ${comment.content}
                    </td>
                    <td>
                        <c:url var="authorUrl" value="/user/${comment.author.id}"/>
                        <a href="${authorUrl}">${comment.author.username}</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</tags:header>
