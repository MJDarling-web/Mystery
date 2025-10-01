<%--
  Created by IntelliJ IDEA.
  User: micahdarling
  Date: 8/12/25
  Time: 5:10 PM
  To change this template use File | Settings | File Templates.
--%>
<header class="host-header">
  <div class="left">
    <a href="<c:url value='/'/>" class="btn">Home</a>
    <a href="<c:url value='/Host/adminDashboard'/>" class="btn">Admin Dashboard</a>
  </div>
  <h2>Welcome: ${user.userName}</h2>
</header>
