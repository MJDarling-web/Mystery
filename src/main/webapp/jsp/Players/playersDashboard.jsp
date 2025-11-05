<%--
  Created by IntelliJ IDEA.
  User: micahdarling
  Date: 8/31/25
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../taglib.jsp" %>
<c:set var="pageTitle" value="Player Dashboard"/>

<html>
<!--TODO header should update with game name in it, not just on page-->
<%@include file="/jsp/head.jsp" %>
<body class="player-pages">
<%@include file="/jsp/header.jsp" %>

<main class="player-main main-other"><!-- keep your class, add player-main for layout -->

    <!-- Hero title like the mock -->
    <h1 class="player-hero">Welcome to <span><c:out value="${story.title}"/></span></h1>
    <h1>A live action $murder mystery game</h1>

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
        <p>Submit a found clue below</p>
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
            <p>Clues left to find: ${remainingClues}</p>
        <div class="center mt-3">
            <a class="btn btn-success" href="<c:url value='/Players/playersSubmitFoundClue'/>">Add Clue</a>
        </div>
    </section>

    <!-- CHARACTERS -->
    <!--TODO each player object needs to be clickable to a player page?-->
    <!--TODO profile for each picture-->
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
                                <!--Profile Picture-->
                                <div class="char-avatar">
                                    <img src="<c:out value='${ch.pictureUrl}'/>" alt="${fn:substring(ch.name, 0, 1)}'s profile picture">
                                </div>
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
    <!-- TODO can show up once all clues have been found-->
    <!--TODO -->
    <section class="player-section">
        <h2 class="section-title">Who-done-it</h2>
        <p>submit your guess, but careful you only get one chance.</p>
        <br>
        <div class="center mt-3">
            <a class="btn btn-danger" href="<c:url value='/Players/PlayersSubmitFinalGuess'/>">Accuse!!!</a>
        </div>
    </section>

</main>


<%@include file="/jsp/playerFooter.jsp" %>
</body>
</html>
