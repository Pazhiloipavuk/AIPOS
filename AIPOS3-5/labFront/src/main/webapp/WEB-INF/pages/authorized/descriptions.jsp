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
            <td>Title of description</td>
            <td>Content</td>
            <td>Task</td>
            <td>Author</td>
            <td>Action</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="description" items="${descriptions}">
            <tr>
                <td>
                    <c:url var="descriptionUrl" value="/description/${description.id}"/>
                    <button onclick="findById('${descriptionUrl}')">${description.title}</button>
                </td>
                <td>
                        ${description.content}
                </td>
                <td>
                    <c:url var="taskUrl" value="/task/${description.task.id}"/>
                    <button onclick="findById('${taskUrl}')">${description.task.name}</button>
                </td>
                <td>
                    <c:url var="authorUrl" value="/user/${description.author.id}"/>
                    <button onclick="findById('${authorUrl}')">${description.author.username}</button>
                </td>
                <td>
                    <c:if test="${currentUser.id == description.author.id}">
                        <c:url var="deleteUrl" value="/description/${description.id}"/>
                        <button onclick="deleteEntity('${deleteUrl}')">Delete</button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>

        <c:url var="saveUrl" value="/descriptions"/>
        <form:form method="PUT" modelAttribute="description" action="${saveUrl}">
            <input type="hidden" name="_method" value="PUT" form="description">
            <tr>
                <form:hidden path="id" form="description"/>
                <td>
                    <form:input path="title" form="description"/>
                    <form:errors path="title" cssStyle="color: red"/>
                </td>
                <td>
                    <form:textarea path="content" form="description"/>
                    <form:errors path="content" cssStyle="color: red"/>
                </td>
                <td>
                    <form:select path="task.id" multiple="false" form="description">
                        <form:option value="-1" label="--- Select ---"/>
                        <form:options items="${tasks}" itemValue="id" itemLabel="name"/>
                    </form:select>
                    <form:errors path="task.id" cssStyle="color: red"/>
                </td>
                <td>
                    <input disabled value="${currentUser.username}"/>
                    <form:hidden path="author.id" value="${currentUser.id}" form="description"/>
                </td>
                <td>
                    <form:button onclick="sendForm('description', '${saveUrl}', 'PUT')">Save</form:button>
                </td>
            </tr>
        </form:form>
        </tbody>
    </table>
</tags:header>
