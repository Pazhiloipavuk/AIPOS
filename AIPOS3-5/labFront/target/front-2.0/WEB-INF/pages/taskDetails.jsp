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
        </tr>
        </thead>
        <tbody>
        <form:form modelAttribute="task">
            <tr>
                <td>
                    <form:input path="name" form="task" disabled="true"/>
                </td>
                <td>
                    <c:url var="descriptionUrl" value="/description/${task.description.id}"/>
                    <a href="${descriptionUrl}">${task.description.title}</a>
                </td>
            </tr>
        </form:form>
        </tbody>
    </table>
</tags:header>
