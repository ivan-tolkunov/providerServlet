<%@ page import="ua.ivan.provider.model.User" %><%--
  Created by IntelliJ IDEA.
  User: memlo
  Date: 8/23/2021
  Time: 11:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        .content-site {
            margin-top: 3%;
        }

        .flex {
            display: flex;
            flex-wrap: wrap;
            padding-left: 10%;
        }

        .item {
            width: 500px;
            height: 300px;
        }

        .flex:after {
            content: '';
            width: 100%;
            order: 0;
        }

        .item.new-string,
        .item.new-string ~ .item {
            order: 1;
        }

        .make-size {
            height: 140px;
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
                   aria-haspopup="true" aria-expanded="false">Language</a>
                <div class="dropdown-menu" style="">
                    <a class="dropdown-item" href="/changeLanguage?language=ukr">Ukrainian</a>
                    <a class="dropdown-item" href="/changeLanguage?language=en">English</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link">Balance: <%=user.getBalance()%></a>
            </li>
            <li class="nav-item">
                <form action="/logout" method="get">
                    <button class="btn btn-link" type="submit">Logout</button>
                </form>
            </li>
        </ul>
    </div>
</nav>

<form class="form-signin" method="post" action="/donate">
    <input type="hidden" name="user_id" value='<%=user.getId()%>'>
    <label for="inputSum" class="sr-only">Email</label>
    <input type="number" id="inputSum" class="form-control" autofocus="" name="sum" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Donate</button>
</form>
</body>
</html>
