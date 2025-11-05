<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />

  <!-- Dynamic title -->
  <title><c:out value="${pageTitle}" default="Mystery Game" /></title>

  <!-- Bootstrap -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <!-- main css -->
  <link rel="stylesheet" href="<c:url value='/style/main.css'/>" type="text/css" />
</head>
