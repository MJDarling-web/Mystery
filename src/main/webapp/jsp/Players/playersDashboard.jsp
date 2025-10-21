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

<main class="player-main main-other"><!-- keep your class, add player-main for layout -->

    <!-- Hero title like the mock -->
    <h1 class="player-hero">Welcome to <span><c:out value="${story.title}"/></span></h1>
    <h3>A live action $murder mystery game</h3>

    <!-- STORY DETAILS -->
    <section class="player-section">
        <p class="section-intro">
            Welcome to your main page— Here you'll view game details include the scenes as well as share found clues, and finally submit your final guess.
        </p>

        <div class="story-details">
            <p><strong>Game:</strong> <c:out value="${story.title}"/></p>
            <p><strong>Details:</strong> <c:out value="${story.description}"/></p>
            <p><strong>Setting:</strong> <c:out value="${story.setting}"/></p>
        </div>
    </section>

    <!-- SCENES -->
    <section class="player-section">
        <h2 class="section-title">Scenes</h2>

        <c:choose>
            <c:when test="${not empty unlockedScenes}">
                <ul class="cards-list">
                    <c:forEach var="scene" items="${unlockedScenes}" varStatus="s">
                        <li class="card">
                            <h3 class="card-title">
                                Scene ${s.index + 1}:
                                <c:out value="${scene.title}"/>
                            </h3>

                            <c:if test="${not empty scene.description}">
                                <p class="muted">
                                    <strong>Details:</strong>
                                    <c:out value="${scene.description}"/>
                                </p>
                            </c:if>
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <p class="muted">No scenes yet.</p>
            </c:otherwise>
        </c:choose>
    </section>


    <!-- CLUES -->
    <section class="player-section">
        <h2 class="section-title">CLUES</h2>

        <c:choose>
            <c:when test="${not empty foundClues}">
                <ul class="cards-list">
                    <c:forEach var="clue" items="${foundClues}" varStatus="cstat">
                        <li class="card">
                            <h3 class="card-title"> CLUE ${cstat.index + 1}: <c:out value="${clue.title}"/></h3>
                            <p class="muted"><strong>Location:</strong> <c:out value="${clue.locationFound}"/></p>
                            <p class="muted"><strong>Description:</strong> <c:out value="${clue.description}"/></p>
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <p class="muted">No clues yet.</p>
            </c:otherwise>
        </c:choose>

        <div class="center">
            <a class="btn btn-primary" href="<c:url value='/Players/playersSubmitFoundClue'/>">Add Clue</a>
        </div>
    </section>

    <!-- CHARACTERS -->
    <section class="player-section">
        <h2 class="section-title">CHARACTERS</h2>

        <c:choose>
            <c:when test="${not empty characters}">
                <div class="grid grid-2">
                    <c:forEach var="ch" items="${characters}">
                        <article class="character-card">
                            <!-- Avatar: prefer provided initials; otherwise show a placeholder -->
                            <!-- needs characters initials in the bubble or picture, needs pronouns -->
                            <div class="char-body">
                                <p><strong>NAME: </strong> <c:out value="${ch.name}"/></p>
                                <p><strong>Bio: </strong> <c:out value="${ch.bio}"/></p>
                                <p><strong>Role: </strong> <c:out value="${ch.role}"/></p>
                            </div>
                        </article>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <p class="muted">No characters yet.</p>
            </c:otherwise>
        </c:choose>
    </section>

    <!-- SUBMIT GUESS -->
    <section class="player-section center">
        <form action="<c:url value='/Players/PlayersSubmitFinalGuess'/>" method="get">
            <button class="btn btn-success" type="submit">Submit Guess</button>
        </form>
    </section>

</main>


<%@include file="/jsp/playerFooter.jsp" %>
</body>
</html>
