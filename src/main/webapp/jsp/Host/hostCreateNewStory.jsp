<%--
  Created by IntelliJ IDEA.
  User: micahdarling
  Date: 8/4/25
  Time: 9:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../taglib.jsp" %>
<html>
<head>
    <title>Host Create New Story</title>
    <link rel="stylesheet" href="<c:url value='/style/main.css'/>" type="text/css" />
</head>

<body class="host">
<%@include file="/jsp/hostHeader.jsp" %>

<main class="page-main">
    <div class="container">
        <h1>New Story: Part 1/4</h1>
        <!-- Update forwarding to part 2 scene creation and save info in session -->
        <form class="story-form-card" method="post" action="<c:url value='/Host/hostCreateNewStory'/>">
        <label for="title">Title</label>
        <input id="title" name="title" type="text" placeholder="Ex: A Night To Remember" required >

            <label for="description">Description</label>
            <textarea id="description" name="description" rows="5"
                      placeholder="Ex: Dinner party with the very fancy and rich Mr. Schmuckers"></textarea>

            <label for="setting">Setting (such as location)</label>
            <input id="setting" name="setting" type="text"
                   placeholder="Ex: 101 Main St - Schmuckers Manor" />

            <button class="btn-primary" type="submit">Next</button>

        </form>
    </div>
</main>

<%@include file="/jsp/footer.jsp" %>
</body>
</html>