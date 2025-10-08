<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="../taglib.jsp" %>
<html>
<head>
    <title>Create Characters: Part 4/4</title>
    <link rel="stylesheet" href="<c:url value='/style/main.css'/>" />
</head>
<body class="host">
<%@include file="/jsp/hostHeader.jsp" %>
<main class="page-main">
    <div class="container">
        <h1>Create Characters: Part 4/4</h1>

        <form class="story-form-card" method="post" action="<c:url value='/Host/hostCreateCharacters'/>">
            <input type="hidden" name="action" value="add"/>

            <label for="name">Name</label>
            <input id="name" name="name" type="text" placeholder="Ex: Mr. Schmuskers"/>

            <label for="bio">Bio/Background</label>
            <textarea id="bio" name="bio" rows="3"
                      placeholder="Ex: Heir to the Schmuskers Candy Co. ..."></textarea>

            <label for="role">Role</label>
            <input id="role" name="role" type="text" placeholder="Ex: friend, nephew, brother, evil twin"/>

            <div class="inline">
                <span>Guilt?</span>
                <label><input type="radio" name="guilty" value="true"> YES</label>
                <label><input type="radio" name="guilty" value="false" checked> No</label>
            </div>

            <div class="inline">
                <span>Murdered?</span>
                <label><input type="radio" name="murdered" value="true"> YES</label>
                <label><input type="radio" name="murdered" value="false" checked> No</label>
            </div>

            <label for="imageUrl">Profile pic (URL)</label>
            <input id="imageUrl" name="imageUrl" type="url" placeholder="https://example.com/photo.jpg"/>

            <button class="btn-primary" type="submit">Add Character</button>
        </form>

        <h2>Current Characters</h2>
        <c:choose>
            <c:when test="${empty draft.characters}">
                <p>No characters added yet.</p>
            </c:when>
            <c:otherwise>
                <table class="table">
                    <thead><tr><th>#</th><th>Name</th><th>Role</th><th>Guilty</th><th>Murdered</th><th></th></tr></thead>
                    <tbody>
                    <c:forEach var="ch" items="${draft.characters}" varStatus="st">
                        <tr>
                            <td>${st.index + 1}</td>
                            <td>${ch.name}</td>
                            <td>${ch.role}</td>
                            <td><c:out value='${ch.guilty ? "Yes" : "No"}'/></td>
                            <td><c:out value='${ch.murdered ? "Yes" : "No"}'/></td>
                            <td>
                                <form method="post" action="<c:url value='/Host/hostCreateCharacters'/>" style="display:inline;">
                                    <input type="hidden" name="action" value="remove"/>
                                    <input type="hidden" name="index" value="${st.index}"/>
                                    <button class="btn danger small" type="submit">Remove</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

        <nav class="wizard-nav">
            <a class="btn" href="<c:url value='/Host/hostCreateClues'/>">← Back</a>
            <form method="post" action="<c:url value='/Host/hostCreateCharacters'/>" style="display:inline;">
                <input type="hidden" name="action" value="next"/>
                <button class="btn primary" type="submit">Next: Review →</button>
            </form>
        </nav>
    </div>
</main>
<%@include file="/jsp/footer.jsp" %>
</body>
</html>
