<%--
  Created by IntelliJ IDEA.
  User: micahdarling
  Date: 9/24/25
  Time: 9:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="../taglib.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

        <!-- BEGIN FORM -->
        <form method="post" action="<c:url value='/Host/hostEditFinishedStory'/>">

            <!-- Story -->
            <section class="panel">
                <h2>Story</h2>
                <label><strong>Title:</strong>
                    <input type="text" name="title" value="${fn:escapeXml(draft.title)}" required />
                </label>
                <br/>
                <label><strong>Desc:</strong><br/>
                    <textarea name="description" rows="3">${fn:escapeXml(draft.description)}</textarea>
                </label>
                <br/>
                <label><strong>Setting:</strong>
                    <input type="text" name="setting" value="${fn:escapeXml(draft.setting)}" />
                </label>
            </section>

            <!-- Characters -->
            <section class="panel">
                <h2>Characters</h2>
                <c:forEach var="ch" items="${draft.characters}" varStatus="st">
                    <div class="card">
                        <p><strong>#${st.index + 1}</strong></p>
                        <label>Name:
                            <input type="text" name="characterName" value="${fn:escapeXml(ch.name)}" />
                        </label>
                        <br/>
                        <label>Bio:<br/>
                            <textarea name="characterBio" rows="2">${fn:escapeXml(ch.bio)}</textarea>
                        </label>
                        <br/>
                        <label>Role:
                            <input type="text" name="characterRole" value="${fn:escapeXml(ch.role)}" />
                        </label>
                        <br/>
                        <label>Image URL:
                            <input type="url" name="characterImageUrl" value="${fn:escapeXml(ch.imageUrl)}" />
                        </label>
                        <br/>
                        <!-- For checkboxes we submit index values for the ones checked -->
                        <label>
                            <input type="checkbox" name="characterGuilty" value="${st.index}"
                                   <c:if test="${ch.guilty}">checked</c:if> />
                            Guilty?
                        </label>
                        <!-- Optional murdered flag shown in UI only -->
                        <p style="margin-top:4px;"><em>Murder Victim?</em> <c:out value='${ch.murdered?"Yes":"No"}'/></p>
                    </div>
                </c:forEach>
            </section>

            <!-- Scenes -->
            <section class="panel">
                <h2>Scenes</h2>
                <c:forEach var="s" items="${draft.scenes}" varStatus="st">
                    <div class="card">
                        <p><strong>Scene ${st.index + 1}</strong></p>
                        <label>Title:
                            <input type="text" name="sceneTitle" value="${fn:escapeXml(s.title)}" />
                        </label>
                        <br/>
                        <label>Order:
                            <input type="number" name="sceneOrder" value="${s.displayOrder}" />
                        </label>
                        <br/>
                        <label>Description:<br/>
                            <textarea name="sceneDescription" rows="2">${fn:escapeXml(s.description)}</textarea>
                        </label>
                    </div>
                </c:forEach>
            </section>

            <!-- Clues -->
            <section class="panel">
                <h2>Clues</h2>
                <c:forEach var="c" items="${draft.clues}" varStatus="st">
                    <div class="card">
                        <p><strong>Clue ${st.index + 1}</strong></p>
                        <label>Title:
                            <input type="text" name="clueTitle" value="${fn:escapeXml(c.title)}" />
                        </label>
                        <br/>
                        <label>Description:<br/>
                            <textarea name="clueDescription" rows="2">${fn:escapeXml(c.description)}</textarea>
                        </label>
                        <br/>
                        <label>Image URL:
                            <input type="url" name="clueImageUrl" value="${fn:escapeXml(c.imageUrl)}" />
                        </label>
                    </div>
                </c:forEach>
            </section>

            <nav class="wizard-nav">
                <a class="btn" href="<c:url value='/Host/hostCreateCharacters'/>">← Back</a>
                <button class="btn primary" type="submit">Save</button>
            </nav>
        </form>
        <!-- END FORM -->

    </div>
</main>

<%@include file="/jsp/footer.jsp" %>
</body>
</html>


