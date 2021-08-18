<%--
  Created by IntelliJ IDEA.
  User: memlo
  Date: 8/18/2021
  Time: 12:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <meta charset="UTF-8">
        <title></title>
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
               aria-expanded="false">Language</a>
            <div class="dropdown-menu" style="">
                <a class="dropdown-item" href="/changeLanguage?language=ukr">Ukrainian</a>
                <a class="dropdown-item" href="/changeLanguage?language=en">English</a>
            </div>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/register">Registration</a>
        </li>
    </ul>
</nav>
<form class="form-signin" method="post" action="/">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>

    <label for="inputEmail" class="sr-only">Email</label>
    <input type="email" id="inputEmail" class="form-control" autofocus="" name="email" required>
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" class="form-control" name="password" required>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
        crossorigin="anonymous"></script>
</body>
</html>
