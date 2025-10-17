<%--
  Created by IntelliJ IDEA.
  User: micahdarling
  Date: 8/12/25
  Time: 5:10 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp" %>

<div class="topnav">
<!--<button type="button" class="icon" aria-expanded="false" aria-controls="myLinks" onclick="toggleMenu(this)">
  <span class="bar"></span>
  <span class="bar"></span>
  <span class="bar"></span>
  <span class="sr-only">Menu</span>
</button>-->

    <div id="myLinks">
      <a href="<c:url value='/'/>">Home</a>
      <a href="<c:url value='/story'/>">Story</a>
    </div>
</div>
