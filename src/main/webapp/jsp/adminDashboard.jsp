<%--
  Created by IntelliJ IDEA.
  User: micahdarling
  Date: 8/17/25
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <%@include file="taglib.jsp" %>
</head>
<body>
<h1 class="game_welcome">Hey there, this is your admin dashboard, eventually it'll first make you login with aws</h1>
<h3>Review your stories, see all your stories or create a new one for a game</h3>
<h3><a href="<c:url value='/createStory'/>">View Stories</a></h3>
<h3>Want to change your characters you've created, check them here at characters</h3>
<h3><a href="<c:url value='/characters'/>">View Characters</a></h3>
<h3>Need to update some clues or create new clues, see below</h3>
<h3><a href="<c:url value='/clues'/>">clues</a></h3>
<h4>To create a new story simply navigate to View Stories and start a new story, you'll be able to add characters, clues, and modify the scenese as needed. And later you can reuse those clues and characters for other games too!</h4>
<a href="createCharacters.jsp"></a>

</body>
</html>
