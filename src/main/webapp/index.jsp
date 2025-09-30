<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Murder Mystery Game</title>
    <link rel="stylesheet" href="style/main.css" type="text/css" />
</head>

<body class="home">
<%@include file="/jsp/header.jsp" %>

    <main class="home-main">
        <h1 class="game_welcome">Welcome to the Murder Mystery Game!</h1>

        <%@include file="jsp/homeHeader.jsp" %>

    <nav aria-label="Main">
        <h3><a href="<c:url value='/Players/storyLookup'/>">I'm a player: Find my party</a></h3>
        <h3><a href="<c:url value='/Host/adminDashboard'/>">Im A Host</a></h3>
    </nav>


    </main>

    <%@include file="/jsp/footer.jsp" %>
</body>
</html>
