<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="userForm" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Notes</title>
</head>
<body>


<h1>Dashboard</h1>

<div class="dashboard" >

    <div class="action">
        <a class="btn navbar-btn ml-2 text-white btn-warning" href="/note/list" data-toggle=""><i class="fa d-inline fa-lg fa-unlock"></i> Lista notatek</a>
        <br/>
        <a class="btn navbar-btn ml-2 text-white btn-warning" href="/note/add" data-toggle=""><i class="fa d-inline fa-lg fa-unlock"></i>Dodaj nową notatkę</a>
        <br/>
    </div>

    <div class="logout">
        <a class="btn navbar-btn ml-2 text-white btn-warning" href="/user/logout" data-toggle=""><i class="fa d-inline fa-lg fa-unlock"></i>&nbsp; Wyloguj</a>
    </div>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

</div>

</body>
</html>