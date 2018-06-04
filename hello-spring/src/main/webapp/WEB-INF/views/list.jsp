<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List</title>
</head>
<body>

<c:forEach items="${stringList}" var="string">
    <li>
        <c:out value="${string}"/>
    </li>
</c:forEach>

</body>
</html>
