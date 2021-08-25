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
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
  <title><fmt:message key="index.title" /></title>
  <style>
    .content_index {
      margin: 50px auto;
    }

    .description {
      margin-top: 50px;
      margin-left: 15%;
      width: 70%;
    }
  </style>
</head>
<body>
<nav class="navbar navbar-light bg-light">
  <div class="container">
  </div>
  <ul class="nav nav-pills">
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true"
         aria-expanded="false"><fmt:message key="language" /></a>
      <div class="dropdown-menu" style="">
        <a class="dropdown-item" href="?language=ukr"><fmt:message key="language.ukr" /></a>
        <a class="dropdown-item" href="?language=en"><fmt:message key="language.en" /></a>
      </div>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/login"><fmt:message key="login" /></a>
    </li>
  </ul>
</nav>
<div class="content_index">
  <div class="title">
    <center>
      <h1><fmt:message key="index.title" /></h1>
    </center>
  </div>
  <div class="description">
    <p><fmt:message key="index.introduction" /></p>
    <p><fmt:message key="index.list.one.label" /></p>
    <ol class="list-group list-group-numbered">
      <li class="list-group-item"><i><fmt:message key="index.list.one.first" /></i></li>
      <li class="list-group-item"><i><fmt:message key="index.list.one.second" /></i></li>
      <li class="list-group-item"><i><fmt:message key="index.list.one.third" /></i></li>
      <li class="list-group-item"><i><fmt:message key="index.list.one.fourth" /></i></li>
      <li class="list-group-item"><i><fmt:message key="index.list.one.fives" /></i></li>
      <li class="list-group-item"><i><fmt:message key="index.list.one.sixth" /></i></li>
      <li class="list-group-item"><i><fmt:message key="index.list.one.seventh" /></i></li>
    </ol>
    <br>
    <p><fmt:message key="index.list.two.label" /></p>
    <ol class="list-group list-group-numbered">
      <li class="list-group-item"><i><fmt:message key="index.list.two.first" /></i></li>
      <li class="list-group-item"><i><fmt:message key="index.list.two.second" /></i></li>
      <li class="list-group-item"><i><fmt:message key="index.list.two.third" /></i></li>
      <li class="list-group-item"><i><fmt:message key="index.list.two.fourth" /></i></li>
      <li class="list-group-item"><i><fmt:message key="index.list.two.fives" /></i></li>
    </ol>
    <br>
    <p><fmt:message key="index.conclusion" /></p>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
        crossorigin="anonymous"></script>
</body>
</html>