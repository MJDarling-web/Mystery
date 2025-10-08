<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="../taglib.jsp" %>
<html>
<head>
    <title>Create Clues: Part 3/4</title>
    <link rel="stylesheet" href="<c:url value='/style/main.css'/>" />
</head>
<body class="host">
<%@include file="/jsp/hostHeader.jsp" %>
<main class="page-main">
    <div class="container">
        <h1>New Clues: Part 3/4</h1>

        <form class="story-form-card" method="post" action="<c:url value='/Host/hostCreateClues'/>">
            <input type="hidden" name="action" value="add"/>

            <label for="clueTitle">Title</label>
            <input id="clueTitle" name="title" type="text" required placeholder="Ex: 1942 WWI Mantle Rifle"/>

            <label for="clueDesc">Description</label>
            <textarea id="clueDesc" name="description" rows="3"
                      placeholder="Short description of the clue"></textarea>

            <label for="clueImg">Image URL</label>
            <input id="clueImg" name="imageUrl" type="url" placeholder="https://example.com/clue.jpg"/>

            <button class="btn-primary" type="submit">Add Clue</button>
        </form>

        <h2>Current Clues</h2>
        <c:choose>
            <c:when test="${empty draft.clues}">
                <p>No clues added yet.</p>
            </c:when>
            <c:otherwise>
                <table class="table">
                    <thead><tr><th>#</th><th>Title</th><th>Image</th><th>Description</th><th></th></tr></thead>
                    <tbody>
                    <c:forEach var="c" items="${draft.clues}" varStatus="st">
                        <tr>
                            <td>${st.index + 1}</td>
                            <td>${c.title}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty c.imageUrl}">
                                        <a href="${c.imageUrl}" target="_blank" rel="noopener">${c.imageUrl}</a>
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </td>
                            <td>${c.description}</td>
                            <td>
                                <form method="post" action="<c:url value='/Host/hostCreateClues'/>" style="display:inline;">
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
            <a class="btn" href="<c:url value='/Host/hostCreateScenes'/>">← Back</a>
            <form method="post" action="<c:url value='/Host/hostCreateClues'/>" style="display:inline;">
                <input type="hidden" name="action" value="next"/>
                <button class="btn primary" type="submit">Next: Characters →</button>
            </form>
        </nav>
    </div>
</main>
<%@include file="/jsp/footer.jsp" %>
</body>
</html>
