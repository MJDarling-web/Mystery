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
<button type="button" class="icon" aria-expanded="false" aria-controls="myLinks" onclick="toggleMenu(this)">
  <span class="bar"></span>
  <span class="bar"></span>
  <span class="bar"></span>
  <span class="sr-only">Menu</span>
</button>

    <div id="myLinks">
      <a href="<c:url value='/'/>">Home</a>
      <a href="<c:url value='/story'/>">Story</a>
      <a href="<c:url value='/clues'/>">Clues</a>
      <a href="<c:url value='/characters'/>">Characters</a>
      <a href="<c:url value='/Host/adminDashboard'/>">Admin Dashboard</a>

      <!--<a href="<c:url value='/createStory'/>">Create Story</a>-->
    </div>
</div>

  <div style="padding-left:16px">
    <h3>Title</h3>
    <p>paragraphy text</p>
    <p>paragraph text</p>
  </div>

<script>
  function toggleMenu(btn) {
    var panel = document.getElementById("myLinks");
    var open = getComputedStyle(panel).display !== 'none';
    panel.style.display = open ? 'none' : 'flex';
    if (btn) btn.setAttribute('aria-expanded', open ? 'false' : 'true');
  }
</script>
