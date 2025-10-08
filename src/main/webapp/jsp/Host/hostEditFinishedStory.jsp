<%--
  Created by IntelliJ IDEA.
  User: micahdarling
  Date: 9/24/25
  Time: 9:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="../taglib.jsp" %>
<html>
<head>
    <title>Finished Story - Review</title>
    <link rel="stylesheet" href="<c:url value='/style/main.css'/>" />
</head>
<body class="host">
<%@include file="/jsp/hostHeader.jsp" %>
<main class="page-main">
    <div class="container">
        <h1>Finished Story – review the story and make any edits you need.</h1>

        <section class="panel">
            <h2>Story</h2>
            <p><strong>Title:</strong> ${draft.title}</p>
            <p><strong>Desc:</strong> ${draft.description}</p>
            <p><strong>Setting:</strong> ${draft.setting}</p>
        </section>

        <section class="panel">
            <h2>Characters</h2>
            <c:forEach var="ch" items="${draft.characters}">
                <div class="card">
                    <p><strong>Name:</strong> ${ch.name}</p>
                    <p><strong>Bio:</strong> ${ch.bio}</p>
                    <p><strong>Role:</strong> ${ch.role}</p>
                    <p><strong>Murder Victim?</strong> <c:out value='${ch.murdered?"Yes":"No"}'/></p>
                    <p><strong>Guilty?</strong> <c:out value='${ch.guilty?"Yes":"No"}'/></p>
                    <c:if test="${not empty ch.imageUrl}">
                        <p><a href="${ch.imageUrl}" target="_blank" rel="noopener">Image URL</a></p>
                    </c:if>
                </div>
            </c:forEach>
        </section>

        <section class="panel">
            <h2>Scenes</h2>
            <c:forEach var="s" items="${draft.scenes}" varStatus="st">
                <div class="card">
                    <p><strong>Scene ${st.index + 1}:</strong> ${s.title}</p>
                    <p><strong>Order:</strong> ${s.displayOrder}</p>
                    <p>${s.description}</p>
                </div>
            </c:forEach>
        </section>

        <section class="panel">
            <h2>Clues</h2>
            <c:forEach var="c" items="${draft.clues}" varStatus="st">
                <div class="card">
                    <p><strong>Clue ${st.index + 1}:</strong> ${c.title}</p>
                    <p>${c.description}</p>
                    <c:if test="${not empty c.imageUrl}">
                        <p><a href="${c.imageUrl}" target="_blank" rel="noopener">Image URL</a></p>
                    </c:if>
                </div>
            </c:forEach>
        </section>

        <nav class="wizard-nav">
            <a class="btn" href="<c:url value='/Host/hostCreateCharacters'/>">← Back</a>
            <form method="post" action="<c:url value='/Host/hostEditFinishedStory'/>" style="display:inline;">
                <button class="btn primary" type="submit">Save</button>
            </form>
        </nav>
    </div>
</main>
<%@include file="/jsp/footer.jsp" %>
</body>
</html>

