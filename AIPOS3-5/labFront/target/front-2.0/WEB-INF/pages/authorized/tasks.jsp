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
            <td>Name</td>
            <td>Description</td>
            <td>Action</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="task" items="${tasks}">
            <tr>
                <td>
                    <c:url var="taskUrl" value="/task/${task.id}"/>
                    <button onclick="findById('${taskUrl}')">${task.name}</button>
                </td>
                <td>
                    <c:url var="descriptionUrl" value="/description/${task.description.id}"/>
                    <button onclick="findById('${descriptionUrl}')">${task.description.title}</button>
                </td>
                <td>
                    <c:url var="deleteUrl" value="/task/${task.id}"/>
                    <button onclick="deleteEntity('${deleteUrl}')">Delete</button>
                </td>
            </tr>
        </c:forEach>

        <c:url var="saveUrl" value="/tasks"/>
        <form:form method="PUT" modelAttribute="task" action="${saveUrl}">
            <input type="hidden" name="_method" value="PUT" form="task">
            <tr>
                <td>
                    <form:input path="name" form="task"/>
                    <form:errors path="name" cssStyle="color: red"/>
                </td>
                <td><form:button onclick="sendForm('task', '${saveUrl}', 'PUT')">Save</form:button></td>
            </tr>
        </form:form>
        </tbody>
    </table>
</tags:header>
