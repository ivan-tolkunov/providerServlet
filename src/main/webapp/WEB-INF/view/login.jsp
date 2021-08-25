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
    <head>
        <meta charset="UTF-8">
        <title><fmt:message key="login.title" /></title>
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
</head>
<body class="text-center">
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
    </ul>
</nav>
<%if ("1".equals(request.getParameter("error"))) {%>
<div class="alert alert-danger" role="alert">
    <fmt:message key="label.error.login" />
</div>
<%}%>
<%if ("2".equals(request.getParameter("error"))) {%>
<div class="alert alert-danger" role="alert">
    <fmt:message key="label.error.money" />
</div>
<%}%>
<form class="form-signin" method="post" action="/main">
    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="login.sign" /></h1>

    <label for="inputEmail" class="sr-only"><fmt:message key="label.email" /></label>
    <input type="email" id="inputEmail" class="form-control" autofocus="" name="email" required>
    <label for="inputPassword" class="sr-only"><fmt:message key="label.password" /></label>
    <input type="password" id="inputPassword" class="form-control" name="password" required>

    <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="login" /></button>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
        crossorigin="anonymous"></script>
</body>
</html>
