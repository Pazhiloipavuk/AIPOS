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
            <td>Name of task</td>
            <td>Title of description</td>
            <td>Update</td>
        </tr>
        </thead>
        <tbody>
        <c:url var="saveUrl" value="/task/${task.id}"/>
        <form:form method="PUT" modelAttribute="task" action="${saveUrl}">
            <input type="hidden" name="_method" value="PUT" form="task">
            <tr>
                <form:hidden path="id" form="task"/>
                <td>
                    <form:input path="name" form="task"/>
                    <form:errors path="name" cssStyle="color: red"/>
                </td>
                <td>
                    <c:url var="descriptionUrl" value="/description/${task.description.id}"/>
                    <a href="${descriptionUrl}">${task.description.title}</a>
                    <form:hidden path="description.title" form="task"/>
                    <form:hidden path="description.id" form="task"/>
                </td>
                <td><form:button onclick="sendForm('task', '${saveUrl}', 'PUT')">Update</form:button></td>
            </tr>
        </form:form>
        </tbody>
    </table>
</tags:header>
