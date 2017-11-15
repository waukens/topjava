<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="https://javatop.com/jsp/tlds/mytags" prefix="mytags"%>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table border=1>
    <thead>
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${sortedMeals}" var="meal">
            <c:set var="ternaryResult"
                   value="${(meal.exceed == 'true') ? 'color: red' : 'color: green'}" />
            <tr style="${ternaryResult}">
                <td>
                    <mytags:formatLocalDate pattern="yyyy-MM-dd HH:mm" localDateTime="${meal.dateTime}"/>
                </td>

                <td>
                    <c:out value="${meal.description}" />
                </td>

                <td>
                    <c:out value="${meal.calories}" />
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>