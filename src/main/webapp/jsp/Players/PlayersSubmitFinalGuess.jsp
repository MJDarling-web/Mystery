<%--
  Created by IntelliJ IDEA.
  User: micahdarling
  Date: 9/25/25
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/jsp/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Submit Final Guess</title>
    <link rel="stylesheet" href="<c:url value='/style/main.css'/>" type="text/css" />
</head>
<body class="player-pages">
<%@include file="/jsp/header.jsp" %>

<main class="main-other">

<h3>Submit Final Guess</h3>
    <!--TODO should populate with selectable bubbles for each character and player can submit a reason against them-->
    <!-- Maybe this can be in a form with the player submitting their name? Because there won't be logins for the player at this stage-->
    <!--div with border here, picture bubble here-->
    <!--within div the suspects name, for each character in the game-->
    <!--select option to highlight a character-->
    <!--Submit button-->
    <section class="accuse-section">
        <h4>Select the character you believe is guilty</h4>

        <c:choose>
            <c:when test="${not empty characters}">
                <!-- Wrap in a form so the selected suspect is submitted -->
                <form method="post" action="<c:url value='/Players/PlayersSubmitFinalGuess'/>">

                    <div class="character-grid">
                        <c:forEach var="ch" items="${characters}">
                            <label class="character-card">
                                <input type="radio" name="suspect" value="${ch.id}">
                                <div class="card-visual">
                                    <img src="${ch.pictureUrl}" alt="${ch.name}" />
                                    <h5>${ch.name}</h5>
                                </div>
                            </label>
                        </c:forEach>
                    </div>

                    <!-- Reasoning enter -->
                    <div class="reason-block">
                        <label for="reason"></label>
                        <textarea id="reason" name="reason" rows="3" placeholder="Write your raccusation"></textarea>
                    </div>

                    <!-- Submit button -->
                    <button type="submit" class="btn-primary">Accuse!</button>

                </form>
            </c:when>

            <c:otherwise>
                <p class="muted">No characters available to accuse.</p>
            </c:otherwise>
        </c:choose>
    </section>

    <!---->
    <!---->
</main>

<%@include file="/jsp/playerFooter.jsp" %>
</body>
</html>

