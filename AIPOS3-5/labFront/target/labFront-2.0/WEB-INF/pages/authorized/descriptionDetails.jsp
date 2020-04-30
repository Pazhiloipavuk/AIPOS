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
        <c:set var="isDisable" value="${description.author.id != currentUser.id}"/>
        <c:url var="saveUrl" value="/description/${description.id}"/>
        <form:form method="PUT" modelAttribute="description" action="${saveUrl}">
            <tr>
                <form:hidden path="id" form="description" value="${description.id}"/>
                <td>
                    <form:input path="title" form="description" disabled="${isDisable}"/>
                    <form:errors path="title" cssStyle="color: red"/>
                </td>
                <td>
                    <form:textarea path="content" form="description" disabled="${isDisable}"/>
                    <form:errors path="content" cssStyle="color: red"/>
                </td>
                <td>
                    <form:select path="task.id" multiple="false" form="description" disabled="${isDisable}">
                        <form:option value="${description.task.id}" label="${description.task.name}"/>
                        <form:options items="${tasks}" itemValue="id" itemLabel="name"/>
                    </form:select>
                    <form:errors path="task.id" cssStyle="color: red"/>
                    <form:hidden path="task.name" form="description"/>
                </td>
                <td>
                    <c:url var="authorUrl" value="/user/${description.author.id}"/>
                    <a href="${authorUrl}">${description.author.username}</a>
                    <form:hidden path="author.id" form="description"/>
                    <form:hidden path="author.username" form="description"/>
                </td>
                <td>
                    <c:if test="${currentUser.id == description.author.id}">
                        <form:button onclick="sendForm('description', '${saveUrl}', 'PUT')">Update</form:button>
                    </c:if>
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

        <c:url var="addCommentUrl" value="/comments"/>
        <form:form method="PUT" modelAttribute="comment" action="${addCommentUrl}">
            <input type="hidden" name="_method" value="PUT" form="comment">
            <tr>
                <td>
                    <form:textarea path="content" form="comment"/>
                    <form:errors path="content" cssStyle="color: red"/>
                </td>
                <form:hidden path="description.id" form="comment" value="${description.id}"/>
                <td>
                    <input hidden id="author.id" name="author.id" value="${currentUser.id}" form="comment"/>
                    <input value="${currentUser.username}" disabled="true"/>
                </td>
                <td><form:button onclick="sendForm('comment', '${addCommentUrl}', 'PUT')">Add comment</form:button></td>
            </tr>
        </form:form>
        </tbody>
    </table>
</tags:header>
