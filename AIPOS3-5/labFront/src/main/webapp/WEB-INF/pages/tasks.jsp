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
            </tr>
        </c:forEach>
        </tbody>
    </table>
</tags:header>
