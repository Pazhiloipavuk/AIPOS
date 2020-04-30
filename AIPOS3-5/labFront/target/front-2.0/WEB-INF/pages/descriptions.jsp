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
            </tr>
        </c:forEach>
        </tbody>
    </table>
</tags:header>
