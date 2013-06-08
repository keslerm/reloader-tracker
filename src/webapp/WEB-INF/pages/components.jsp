<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Components</title>
</head>
<body>
    <table width="100%">
        <c:forEach items="${components}" var="component">
            <tr>
                <td>${component.name}</td>
                <td>${component.type}</td>
                <td>${component.amount}</td>
                <td>${component.cost}</td>
                <td>${component.remaining}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>