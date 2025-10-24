<%--
  Created by IntelliJ IDEA.
  User: micahdarling
  Date: 9/25/25
  Time: 2:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../taglib.jsp" %>

<html>
<head>
    <title>Found Clue</title>
    <link rel="stylesheet" href="<c:url value='/style/main.css'/>" type="text/css" />

</head>

<body class="player-pages">

<main class="main-other">
<h1>Oh shoot, you found a clue?</h1>
    <form method="post" action="<c:url value='/Players/playersSubmitFoundClue'/>">
        <input type="tel" placeholder="Enter clue ID" required/>
        <button type="submit">Share CLUE!</button>
    </form>
</main>

<%@include file="/jsp/playerFooter.jsp" %>
</body>
</html>
