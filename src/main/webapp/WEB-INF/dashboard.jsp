<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="userForm" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
          type="text/css">
    <link rel="stylesheet" href="../resource/home.css">
    <link rel="stylesheet" href="../resource/main.css">

    <title>Notes</title>
</head>
<body>

<div class="container-fluid p-3">
    <div class="row p-2">
        <h1 class="mb-4 text-center text-secondary" style="padding-bottom: 20px">Dashboard</h1>
    </div>

    <div class="row p-2">
        <a class="btn navbar-btn ml-2 text-white btn-warning" href="/note/list" data-toggle=""> Lista notatek </a>
    </div>

    <div class="row p-2">
        <a class="btn navbar-btn ml-2 text-white btn-warning" href="/note/add" data-toggle=""> Dodaj nową notatkę </a>
    </div>

    <div class="row p-2">
        <a class="btn navbar-btn ml-2 text-white btn-warning" href="/censure/" data-toggle=""> Cenzura </a>
    </div>

    <div class="row p-2">
            <a class="btn navbar-btn ml-2 text-white btn-danger" href="/user/logout" data-toggle=""> Wyloguj </a>
    </div>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

</body>
</html>