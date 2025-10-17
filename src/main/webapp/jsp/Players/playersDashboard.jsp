<%--
  Created by IntelliJ IDEA.
  User: micahdarling
  Date: 8/31/25
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../taglib.jsp" %>

<html>
<head>
    <title>Players Dashboard</title>
    <link rel="stylesheet" href="<c:url value='/style/main.css'/>" type="text/css" />

</head>

<body class="player-pages">
<%@include file="/jsp/header.jsp" %>
    <main class="main-other">
        <p><c:out value="${story.title}"/></p>
        <p><c:out value="${story.description}"/></p>

        <p>-Scenes-</p>
        //Scenes in cards
        <p>-Characters-</p>
        //characters in cards
        <p>-clues-</p>
        //clues

        <button type="submit">Add a clue</button>
        <button type="submit">Add a clue</button>

        <form  action="<c:url value='/Players/storyLookup'/>" method="post">
            <label for="storyId">Story ID</label>
            <input type="number" name="storyId" id="storyId" required/>
            <input type="submit" value="Let's Start Playing!"/>
        </form>
    </main>

<%@include file="/jsp/playerFooter.jsp" %>
</body>
</html>
