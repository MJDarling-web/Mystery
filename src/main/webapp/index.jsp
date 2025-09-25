<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Murder Mystery Game</title>
    <link rel="stylesheet" href="style/main.css" type="text/css" />

</head>
<body class="home">
<div>
<h1 class="game_welcome">Welcome to the Murder Mystery Game!</h1>
</div>
<%@include file="jsp/homeHeader.jsp" %>

<nav aria-label="Main">
    <h3><a href="<c:url value='/storyLookup'/>">I'm a player: Find my party</a></h3>
    <h3><a href="<c:url value='/players'/>">Player Dashboard</a></h3>
    <h3><a href="<c:url value='/Host/adminDashboard'/>">Im the Host</a></h3>
</nav>

</body>
</html>
