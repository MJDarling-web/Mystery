<%--
  Created by IntelliJ IDEA.
  User: micahdarling
  Date: 8/31/25
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Players Page</title>
    <link rel="stylesheet" href="style/main.css" type="text/css" />
    <%@include file="taglib.jsp" %>

</head>
<body class="home">

<nav aria-label="Main">
  <h3><a href="<c:url value='/story'/>">Story</a></h3>
  <h3><a href="<c:url value='/clues'/>">Clues</a></h3>
  <h3><a href="<c:url value='/characters'/>">Characters</a></h3>
</nav>

</body>
</html>
