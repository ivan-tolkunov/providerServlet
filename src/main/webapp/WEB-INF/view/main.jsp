<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ua.ivan.provider.model.SitePackage" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.ivan.provider.model.User" %>
<%@ page import="ua.ivan.provider.model.Role" %><%--
  Created by IntelliJ IDEA.
  User: memlo
  Date: 8/18/2021
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
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
            content:'';
            width:100%;
            order:0;
        }

        .item.new-string,
        .item.new-string ~ .item {
            order:1;
        }

        .make-size {
            height: 140px;
        }
    </style>
</head>
<body>
<%User user = (User)request.getSession().getAttribute("user");%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" href="@{/auth/main}">Main page</a>
                </li>
                <% if (user.getRole().equals(Role.ADMIN)) { %>
                    <li class="nav-item">
                        <a class="nav-link active" href="/admin">Admin Panel</a>
                    </li>
                <% } %>
                <li class="nav-item">
                    <a class="nav-link active" href="/user"><%=user.getEmail()%></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                       aria-haspopup="true" aria-expanded="false">Sort by:</a>
                    <div class="dropdown-menu" style="">
                        <form action="#" action="@{/user/sortMain}" method="get">
                            <button class="btn btn-link" type="submit" name="method" value="'A-Z'" >Sort by
                                name(A-Z)
                            </button>
                            <button class="btn btn-link" type="submit" name="method" value="'Z-A'">Sort by
                                name(a-z)
                            </button>
                            <button class="btn btn-link" type="submit" name="method" value="'price'">Sort by price
                            </button>
                        </form>
                    </div>
                </li>
                <li class="nav-item">
                    <form action="#" action="@{/download}" method="post">
                        <button class="btn btn-light" type="submit">Download</button>
                    </form>
                </li>
            </ul>
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
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                   aria-haspopup="true" aria-expanded="false">Balance:1337</a>
                <div class="dropdown-menu" style="">
                    <form action="#" method="post">
                        <button class="btn btn-link" type="submit" name="sum" value="200">200 UAH
                        </button>
                        <button class="btn btn-link" type="submit" name="sum" value="500">500 UAH
                        </button>
                        <button class="btn btn-link" type="submit" name="sum" value="1000">1000 UAH
                        </button>
                    </form>
                </div>
            </li>
            <li class="nav-item">
                <form action="/logout"  method="get">
                    <button class="btn btn-link" type="submit">Logout</button>
                </form>
            </li>
        </ul>
    </div>
</nav>

<div class="content-site">
    <div class="flex">
        <%
            List<SitePackage> sitePackages =
                    (List<SitePackage>)request.getAttribute("sitePackages");
            for(SitePackage p: sitePackages){%>
        <div class="card border-dark mb-3 item" style="max-width: 18rem;">
            <div class="card-header"><%=p.getType()%></div>
            <div class="card-body text-dark">
                <h5 class="card-title"><%=p.getName()%></h5>
                <p class="card-text make-size"><%=p.getDescription()%></p>
                <form action="/user/add" method="post">
                    <input type="hidden" name="packageId" value='<%=p.getId()%>' />
                    <input type="hidden" name="userId" value='<%=user.getId()%>' />
                    <button type="submit" class="btn btn-primary" >Add</button>
                </form>
            </div>
        </div>
        <%}%>
        <div class="item new-string"></div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
        crossorigin="anonymous"></script>
</body>
</html>
