<%--
  Created by IntelliJ IDEA.
  User: micahdarling
  Date: 8/4/25
  Time: 9:27 PM
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../taglib.jsp" %>
<html>
<head>
    <title>Host Create Scenes</title>
    <link rel="stylesheet" href="<c:url value='/style/main.css'/>" type="text/css" />
</head>
<body class="host">
<%@include file="/jsp/hostHeader.jsp" %>

<main class="page-main">
    <div class="container">
        <h1>New Story: Part 2/4 — Scenes</h1>

        <c:if test="${not empty error}">
            <div class="alert alert-error">${error}</div>
        </c:if>

        <!-- Add Scene -->
        <form class="card" method="post" action="<c:url value='/Host/hostCreateScenes'/>">
            <input type="hidden" name="action" value="add" />
            <div>
                <label for="sceneTitle">Scene Title</label>
                <input id="sceneTitle" name="sceneTitle" type="text" required
                       placeholder="Ex: The Library at Midnight" />
            </div>

            <div>
                <label for="sceneDescription">Description</label>
                <textarea id="sceneDescription" name="sceneDescription" rows="3"
                          placeholder="Ex: Guests gather; storm knocks power; a scream is heard."></textarea>
            </div>

            <div>
                <label for="displayOrder">Order (optional)</label>
                <input id="displayOrder" name="displayOrder" type="number" min="1" />
            </div>

            <button class="btn-primary" type="submit">Add Scene</button>
        </form>

        <!-- Existing Scenes -->
        <h2>Current Scenes</h2>
        <c:choose>
            <c:when test="${empty storyDraft.scenes}">
                <p>No scenes added yet.</p>
            </c:when>
            <c:otherwise>
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Order</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="s" items="${storyDraft.scenes}" varStatus="st">
                        <tr>
                            <td>${st.index + 1}</td>
                            <td>${s.title}</td>
                            <td>${s.description}</td>
                            <td>${s.displayOrder}</td>
                            <td>
                                <form method="post" action="<c:url value='/Host/hostCreateScenes'/>" style="display:inline;">
                                    <input type="hidden" name="action" value="delete" />
                                    <input type="hidden" name="index" value="${st.index}" />
                                    <button class="btn-link" type="submit">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

        <!-- Next step -->
        <form method="post" action="<c:url value='/Host/hostCreateScenes'/>" style="margin-top: 16px;">
            <input type="hidden" name="action" value="next" />
            <button class="btn-primary" type="submit">Next: Characters</button>
        </form>
    </div>
</main>

<%@include file="/jsp/footer.jsp" %>
</body>
</html>

