<%@ page import="java.util.List" %>
<%@ page import="ua.ivan.provider.model.User" %>
<%@ page import="ua.ivan.provider.model.Donate" %>
<%@ page import="ua.ivan.provider.model.Status" %><%--
  Created by IntelliJ IDEA.
  User: memlo
  Date: 8/20/2021
  Time: 12:35 PM
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
        .nav-my {
            padding-top: 10px;
            padding-left: 20px;
            padding-right: 20px;
        }

        .my-control-panel {
            margin-top: 3%;
            width: 50%;
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
                    <a class="nav-link active" href="/main">Main page</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="/user"><%=user.getEmail()%>
                    </a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                       aria-haspopup="true" aria-expanded="false">Admin action</a>
                    <div class="dropdown-menu" style="">
                        <form action="/register" method="get">
                            <button class="btn btn-link" type="submit">Register new user</button>
                        </form>
                        <form action="/admin/editPackagePage" method="get">
                            <button class="btn btn-link" type="submit">Edit packages</button>
                        </form>
                        <form action="/admin/addPackage" method="get">
                            <button class="btn btn-link" type="submit">Add new package</button>
                        </form>
                    </div>
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
                   aria-haspopup="true" aria-expanded="false">Balance: <%=user.getBalance()%></a>
                <div class="dropdown-menu" style="">
                    <form action="/donate" method="post">
                        <button class="btn btn-link" type="submit" name="sum" value="200">200 UAH</button>
                        <button class="btn btn-link" type="submit" name="sum" value="500">500 UAH</button>
                        <button class="btn btn-link" type="submit" name="sum" value="1000">1000 UAH</button>
                    </form>
                </div>
            </li>
            <li class="nav-item">
                <form action="/logout" method="get">
                    <button class="btn btn-link" type="submit">Logout</button>
                </form>
            </li>
        </ul>
    </div>
</nav>
<%
    List<User> users = (List<User>) request.getAttribute("listOfUsers");
    List<Donate> donates = (List<Donate>) request.getAttribute("listOfDonates");
%>
<center>
    <div class="my-control-panel">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th>Email</th>
                <th>Balance</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <% for (User u : users) {%>
            <tr>
                <td><%=u.getEmail()%>
                </td>
                <td><%=u.getBalance()%>
                </td>
                <td><%=u.getStatus()%>
                </td>
                <td>
                    <form action="/changeUserStatus" method="post">
                        <% if (u.getStatus().equals(Status.ACTIVE)) {%>
                            <input type="hidden" name="status" value='<%=Status.BANNED%>'>
                            <button type="submit" class="btn btn-outline-danger" value='<%=u.getId()%>' name="user_id">Ban</button>
                        <%} else {%>
                            <input type="hidden" name="status" value='<%=Status.ACTIVE%>'>
                            <button type="submit" class="btn btn-outline-success" value='<%=u.getId()%>' name="user_id">Unban</button>
                        <%}%>
                    </form>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</center>

<center>
    <div class="my-control-panel">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th>Email</th>
                <th>Sum</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <% for (Donate d : donates) {%>
            <tr>
                <td><%=d.getUser().getEmail()%></td>
                <td><%=d.getSum()%></td>
                <td>
                    <div>
                        <form action="/donateAction" method="post">
                            <input type="hidden" value='<%=d.getUser().getId()%>' name="id_user"/>
                            <input type="hidden" value='<%=d.getId()%>' name="id_donate"/>
                            <button type="submit" class="btn btn-success" value='<%=d.getSum()%>' name="sum">Сonfirm</button>
                        </form>
                    </div>
                </td>
                <td>
                    <form action="/donateAction" method="post">
                        <button type="submit" class="btn btn-outline-danger" value='<%=d.getId()%>' name="id_donate">Reject</button>
                    </form>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</center>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
        crossorigin="anonymous"></script>
</body>
</html>
