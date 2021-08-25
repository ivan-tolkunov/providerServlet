<%@ page import="ua.ivan.provider.model.User" %>
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
    <title>Donate Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
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
<%
    User user = (User) request.getSession().getAttribute("user");
%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarNav">

        </div>
        <ul class="nav nav-pills">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                   aria-haspopup="true" aria-expanded="false"><fmt:message key="language" /></a>
                <div class="dropdown-menu" style="">
                    <a class="dropdown-item" href="?language=ukr"><fmt:message key="language.ukr" /></a>
                    <a class="dropdown-item" href="?language=en"><fmt:message key="language.en" /></a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link"><fmt:message key="label.balance" /> <%=request.getSession().getAttribute("userBalance")%></a>
            </li>
            <li class="nav-item">
                <form action="/logout" method="get">
                    <button class="btn btn-link" type="submit"><fmt:message key="label.logout" /></button>
                </form>
            </li>
        </ul>
    </div>
</nav>

<form class="form-signin" method="post" action="/donate">
    <input type="hidden" name="user_id" value='<%=user.getId()%>'>
    <label for="inputSum" class="sr-only"><fmt:message key="label.balance" /></label>
    <input type="number" id="inputSum" class="form-control" autofocus="" name="sum" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="label.confirm" /></button>
</form>
</body>
</html>
