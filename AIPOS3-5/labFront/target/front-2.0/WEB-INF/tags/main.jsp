<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="pageTitle" required="true" %>

<html>
<head>
    <title>${pageTitle}</title>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous">
    </script>
    <script src="js/ajaxMethods.js"></script>

</head>
<body>
<main>
    <table>
        <thead>
        <tr>
            <c:url var="tasks" value="/tasks"/>
            <td><button onclick="findAll('${tasks}')">Tasks</button></td>

            <c:url var="descriptions" value="/descriptions"/>
            <td><button onclick="findAll('${descriptions}')">Descriptions</button></td>

            <c:url var="users" value="/users"/>
            <td><button onclick="findAll('${users}')">Users</button></td>

            <c:url var="comments" value="/comments"/>
            <td><button onclick="findAll('${comments}')">Comments</button></td>
        </tr>
        </thead>
    </table>
    <jsp:doBody/>
</main>
</body>
</html>