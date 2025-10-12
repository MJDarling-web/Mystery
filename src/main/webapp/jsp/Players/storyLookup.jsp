<%--
  Created by IntelliJ IDEA.
  User: micahdarling
  Date: 9/13/25
  Time: 11:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../taglib.jsp" %>

<html>
<head>
    <title>Story Look up</title>
    <link rel="stylesheet" href="<c:url value='/style/main.css'/>" type="text/css" />

</head>
<body class="player-pages">
<%@include file="/jsp/header.jsp" %>

  <main class="main-other">
    <h2>Submit your game ID to get started</h2>

    <form  action="<c:url value='/Players/storyLookup'/>" method="post">
      <label for="storyId">Story ID</label>
      <input type="number" name="storyId" id="storyId" required/>
      <input type="submit" value="Let's Start Playing!"/>
    </form>
    
  </main>

  <%@include file="/jsp/playerFooter.jsp" %>
</body>
</html>
