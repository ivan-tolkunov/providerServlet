<%@ page import="ua.ivan.provider.model.User" %>
<%@ page import="ua.ivan.provider.model.Role" %>
<%@ page import="ua.ivan.provider.model.SitePackage" %>
<%@ page import="java.util.List" %>
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
    <title>Cabinet</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        .nav-my {
            padding-top: 10px;
            padding-left: 20px;
            padding-right: 20px;
        }

        .content-site {
            margin-top: 3%;
        }
    </style>
</head>
<body>
<%User user = (User) request.getSession().getAttribute("user");%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" href="/main"><fmt:message key="main.page" /></a>
                </li>
                <% if (user.getRole().equals(Role.ADMIN)) { %>
                <li class="nav-item">
                    <a class="nav-link active" href="/admin"><fmt:message key="admin.panel" /></a>
                </li>
                <% } %>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                       aria-haspopup="true" aria-expanded="false"><fmt:message key="cabinet.sort" /></a>
                    <div class="dropdown-menu" style="">
                        <form action="/user/sortPackages" method="get">
                            <button class="btn btn-link" type="submit" name="method" value="A-Z"><fmt:message key="cabinet.sort.az" /></button>
                            <button class="btn btn-link" type="submit" name="method" value="Z-A"><fmt:message key="cabinet.sort.za" /></button>
                            <button class="btn btn-link" type="submit" name="method" value="price"><fmt:message key="cabinet.sort.price" /></button>
                        </form>
                    </div>
                </li>
            </ul>
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
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                   aria-haspopup="true" aria-expanded="false"><fmt:message key="label.balance" /> <%=request.getSession().getAttribute("userBalance")%></a>
                <div class="dropdown-menu" style="">
                    <form action="/donate" method="post">
                        <input type="hidden" name="user_id" value='<%=user.getId()%>'>
                        <button class="btn btn-link" type="submit" name="sum" value="200">200 <fmt:message key="label.grivna" /></button>
                        <button class="btn btn-link" type="submit" name="sum" value="500">500 <fmt:message key="label.grivna" /></button>
                        <button class="btn btn-link" type="submit" name="sum" value="1000">1000 <fmt:message key="label.grivna" /></button>
                    </form>
                </div>
            </li>
            <li class="nav-item">
                <form action="/logout" method="get">
                    <button class="btn btn-link" type="submit"><fmt:message key="label.logout" /></button>
                </form>
            </li>
        </ul>
    </div>
</nav>
<div class="content-site">
    <center><h1><fmt:message key="cabinet.site.title" /></h1></center>
    <br>
    <div class="d-flex justify-content-around">
        <%
            List<SitePackage> sitePackages =
                    (List<SitePackage>) request.getSession().getAttribute("userSubs");
            for (SitePackage p : sitePackages) {%>
        <div>
            <div class="card border-dark mb-3" style="max-width: 18rem;">
                <div class="card-header"><%=p.getType()%></div>
                <div class="card-body text-dark">
                    <h5 class="card-title"><%=p.getName()%></h5>
                    <p class="card-text"><%=p.getDescription()%></p>
                    <form action="/user/unsub" method="post">
                        <input type="hidden" name="userId" value='<%=user.getId()%>'/>
                        <input type="hidden" name="packageId" value="<%=p.getId()%>"/>
                        <button type="submit" class="btn btn-danger"><fmt:message key="cabinet.btn.unsub" /></button>
                    </form>
                </div>
            </div>
        </div>
        <%}%>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
        crossorigin="anonymous"></script>
</body>
</html>
