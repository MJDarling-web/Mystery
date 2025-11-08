<%--
  Created by IntelliJ IDEA.
  User: micahdarling
  Date: 8/17/25
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- AdminDashboard pulls stores through GET related to host ID servlet used is adminDashboardServlet-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../taglib.jsp" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="<c:url value='/style/main.css'/>" type="text/css" />
</head>

<body>
    <%@include file="/jsp/hostHeader.jsp" %>

<main class="user-pages">

    <h1 class="game_welcome">Hey there, this is your admin dashboard, eventually it'll first make you login with aws</h1>
    <h3>Review your stories, see all your stories or create a new one for a game</h3>
<!-- list out stories for host to view based on their user login or redirect to login page -->

        <c:if test="${not empty stories}">
        <table class="left-aligned-table">
            <thead>
            <tr>
                <th>Story ID</th>
                <th>Title</th>
                <th>description</th>
                <th>Setting</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var ="item" items="${stories}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.title}</td>
                    <td>${item.description}</td>
                    <td>${item.setting}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/Host/startEdit?storyId=${item.id}">
                            Edit
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        <!-- edit/delete button to forward to that stories "final/review" page-->
    </table>
</c:if>

<!-- include id, title, description, setting, creator id -->
<!-- include edit and delete buttons that then take you to the story details page?-->

        <h3><a href="<c:url value='/Host/hostCreateNewStory'/>">Create a new story</a></h3>

    </main>
<%@include file="/jsp/footer.jsp" %>
</body>
</html>
