<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="../taglib.jsp" %>
<html>
<head>
    <title>Create Scenes: Part 2/4</title>
    <link rel="stylesheet" href="<c:url value='/style/main.css'/>" />
</head>
<body class="host">
<%@include file="/jsp/hostHeader.jsp" %>
<main class="page-main">
    <div class="container">
        <h1>New Scenes: Part 2/4</h1>

        <form class="story-form-card" method="post" action="<c:url value='/Host/hostCreateScenes'/>">
            <input type="hidden" name="action" value="add"/>
            <label for="sceneTitle">Scene Title</label>
            <input id="sceneTitle" name="sceneTitle" type="text" required placeholder="Ex: Cocktail Hour"/>

            <label for="sceneDescription">Tasks / Description</label>
            <textarea id="sceneDescription" name="sceneDescription" rows="3"
                      placeholder="Ex: Meet at least 3 guests and introduce your name"></textarea>

            <label for="displayOrder">Order (optional)</label>
            <input id="displayOrder" name="displayOrder" type="number" min="1"/>

            <button class="btn-primary" type="submit">Add Scene</button>
        </form>

        <h2>Current Scenes</h2>
        <c:choose>
            <c:when test="${empty draft.scenes}">
                <p>No scenes added yet.</p>
            </c:when>
            <c:otherwise>
                <table class="table">
                    <thead><tr><th>#</th><th>Title</th><th>Description</th><th>Order</th><th></th></tr></thead>
                    <tbody>
                    <c:forEach var="s" items="${draft.scenes}" varStatus="st">
                        <tr>
                            <td>${st.index + 1}</td>
                            <td>${s.title}</td>
                            <td>${s.description}</td>
                            <td>${s.displayOrder}</td>
                            <td>
                                <form method="post" action="<c:url value='/Host/hostCreateScenes'/>" style="display:inline;">
                                    <input type="hidden" name="action" value="delete"/>
                                    <input type="hidden" name="index" value="${st.index}"/>
                                    <button class="btn danger small" type="submit">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

        <nav class="wizard-nav">
            <a class="btn" href="<c:url value='/Host/hostCreateNewStory'/>">← Back</a>
            <form method="post" action="<c:url value='/Host/hostCreateScenes'/>" style="display:inline;">
                <input type="hidden" name="action" value="next"/>
                <button class="btn primary" type="submit">Next: Clues →</button>
            </form>
        </nav>
    </div>
</main>
<%@include file="/jsp/footer.jsp" %>
</body>
</html>
