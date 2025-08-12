<%--
  Created by IntelliJ IDEA.
  User: micahdarling
  Date: 8/12/25
  Time: 5:10 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Load an icon library to show a hamburger menu (bars) on small screens -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<%@include file="taglib.jsp" %>
<div class="topnav" id="myTopnav">
  <a href="<c:url value='/'/>">Home</a>
  <a href="<c:url value='/story'/>">Story</a>
  <a href="<c:url value='/clues'/>">Clues</a>
  <a href="<c:url value='/characters'/>">Characters</a>
  <a href="<c:url value='/createStory'/>">Create Story</a>
  <!-- make light gray unless usser is signed in admin user-->

<!-- make responsive to small screen to have hidden hamburger navbar -->
</div>
