<%--
  Created by IntelliJ IDEA.
  User: micahdarling
  Date: 9/13/25
  Time: 11:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="taglib.jsp" %>

<html>
<head>
    <title>Story Look up</title>
  <!--<link rel="stylesheet" href="style/main.css" type="text/css" /> -->

</head>
<body class="home">
  <main aria-label="Main">
    <h2>Submit your game ID to get started</h2>

    <form  action="<c:url value='/storyLookup'/>" method="post">
      <label for="storyId">Story ID</label>
      <input type="number" name="storyId" id="storyId" required />
      <input type="submit" value="Submit"/>
      <button type="submit">Let's start playing!</button>
    </form>
    
  </main>

</body>
</html>
