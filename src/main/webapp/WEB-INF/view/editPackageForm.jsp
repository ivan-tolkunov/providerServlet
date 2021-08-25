<%@ page import="ua.ivan.provider.model.SitePackage" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" />
<!DOCTYPE html>
<html lang="${language}">
<head>
  <meta charset="UTF-8">
  <title><fmt:message key="admin.edit.new.package" /></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
        rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
        crossorigin="anonymous">
  <style>
    a {
      text-decoration: none;
    }
    form {
      max-width: 600px;
      margin: 200px auto;
    }

    button {
      margin-top: 10px;
    }
  </style>
</head>
<body>
<nav class="navbar navbar-light bg-light">
  <div class="container">
  </div>
  <ul class="nav nav-pills">
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false"><fmt:message key="language" /></a>
      <div class="dropdown-menu" style="">
        <a class="dropdown-item" href="?language=ukr"><fmt:message key="language.ukr" /></a>
        <a class="dropdown-item" href="?language=en"><fmt:message key="language.en" /></a>
      </div>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/main"><fmt:message key="main.page" /></a>
    </li>
  </ul>
</nav>
<%SitePackage sitePackage = (SitePackage) request.getAttribute("editPackage");%>
<form class="form-signin" method="post" action="/admin/editPackage">
  <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="registration.info" /></h1>
  <input type="hidden" name="id" value='<%=sitePackage.getId()%>'>

  <label for="inputPassword" class="sr-only"><fmt:message key="edit.name" /></label>
  <input type="text" id="inputName" class="form-control" name="name" value='<%=sitePackage.getName()%>' required>

  <label for="inputPassword" class="sr-only"><fmt:message key="edit.description" /></label>
  <input type="text" id="inputLastName" class="form-control" name="description" value='<%=sitePackage.getDescription()%>' required>

  <label for="inputEmail" class="sr-only"><fmt:message key="edit.price" /></label>
  <input type="text" id="inputEmail" class="form-control" autofocus="" name="price" value='<%=sitePackage.getPrice()%>' required>

  <label for="inputPassword" class="sr-only"><fmt:message key="edit.type" /></label>
  <input type="text" id="inputPassword" class="form-control" name="type" value='<%=sitePackage.getType()%>' required>

  <button class="btn btn-lg btn-success btn-block" type="submit"><fmt:message key="label.edit" /></button>
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
        crossorigin="anonymous"></script>
</body>
</html>
