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
            <td>Action</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="comment" items="${comments}">
            <tr>
                <td>
                    <c:url var="commentUrl" value="/comment/${comment.id}"/>
                    <button onclick="findById('${commentUrl}')">${comment.content}</button>
                </td>
                <td>
                    <c:url var="descriptionUrl" value="/description/${comment.description.id}"/>
                    <button onclick="findById('${descriptionUrl}')">${comment.description.title}</button>
                </td>
                <td>
                    <c:url var="authorUrl" value="/user/${comment.author.id}"/>
                    <button onclick="findById('${authorUrl}')">${comment.author.username}</button>
                </td>
                <td>
                    <c:if test="${currentUser.id == comment.author.id}">
                        <c:url var="deleteUrl" value="/comment/${comment.id}"/>
                        <button onclick="deleteEntity('${deleteUrl}')">Delete</button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</tags:header>