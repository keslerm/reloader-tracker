<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

    <form:form method="POST" commandName="saveBatch" modelAttribute="batch">
            <table width="25%">
                <tr>
                    <td>Brass:</td>
                    <td>
                        <form:select path="brass">
                            <form:options items="${brass}" />
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>Bullet:</td>
                    <td>
                        <form:select path="bullet">
                            <form:options items="${bullet}" />
                        </form:select>
                    </td>
                </tr>
            </table>
    </form:form>
</body>
</html>