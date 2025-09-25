<%--
  Created by IntelliJ IDEA.
  User: micahdarling
  Date: 9/25/25
  Time: 2:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/jsp/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head><title>Character Details</title></head>
<body>

<c:if test="${not empty character}">
  <h1><c:out value="${character.name}"/></h1>
  <c:if test="${not empty character.pronouns}"><p>Pronouns: <c:out value="${character.pronouns}"/></p></c:if>
  <c:if test="${not empty character.relation}"><p>Relation: <c:out value="${character.relation}"/></p></c:if>
  <c:if test="${not empty character.bio}"><p>Bio: <c:out value="${character.bio}"/></p></c:if>
</c:if>

<c:url var="backUrl" value="/players"/>
<p><a href="${backUrl}">Back to Dashboard</a></p>

</body>
</html>

