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
    <title>Players Dashboard</title>
    <link rel="stylesheet" href="style/main.css" type="text/css" />
    <%@include file="../taglib.jsp" %>

</head>
<body class="home">
<p><c:out value="${story.title}"/></p>
<p><c:out value="${story.description}"/></p>

<p>-Scenes-</p>
<p>Characters</p>
//characters in cards
<p>-clues-</p>
//clues

<button type="submit">Add a clue</button>
<button type="submit">Add a clue</button>

</body>
</html>
