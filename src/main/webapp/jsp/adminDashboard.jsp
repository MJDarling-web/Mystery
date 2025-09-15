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
<!-- list out stories for host to view based on their user login or redirect to login page -->

<c:if test="{$not empty stories}">
    <table class="left-aligned-table">
        <thead>
        <tr>
            <th>Story ID</th>
            <th>Title</th>
            <th>description</th>
            <th>Setting</th>
            <th>creator id</th>
        </tr>
        </thead>
        <tbody>
        <c: forEach var ="storyList" items="${stories}">
            <tr>
                <td>${item.id}</td>
                <td>${item.title}</td>
                <td>${item.description}</td>
                <td>${item.setting}</td>
                <td>${item.created}</td>
            </tr>
        </c:>
        </tbody>
        <!-- edit/delete button to forward to that stories "final/review" page-->
    </table>
</c:if>

<!-- include id, title, description, setting, creator id -->
<!-- include edit and delete buttons that then take you to the story details page?-->

<h3><a href="<c:url value='/createStory'/>">Create a new story</a></h3>

</body>
</html>
