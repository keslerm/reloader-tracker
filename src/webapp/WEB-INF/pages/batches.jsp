<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
    <table width="100%">
        <c:forEach items="${batches}" var="batch">
            <tr>
                <td>${batch.description}</td>
                <td>${batch.brass.name}</td>
                <td>${batch.primer.name}</td>
                <td>${batch.bullet.name}</td>
                <td>${batch.powder.name}</td>
                <td>${batch.powderCharge}</td>
                <td>${batch.count}</td>
                <td>${batch.costPerRound.costPerRound}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>