<%@ page import="notes.Helper.Enum.AddEnum" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="noteForm" uri="http://www.springframework.org/tags/form" %>

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

<div class="container-fluid p-3" style="margin-left: 20px">
    <div class="row p-2">
        <h1 class="mb-4 text-center text-secondary" style="padding-bottom: 20px">Dodaj nową notatkę</h1>
    </div>

    <div class="row p-2">
        <div class="col-md-4">

        <noteForm:form modelAttribute="note" action="/note/save" method="post">

            <div class="form-group" >
                <label class="text-secondary">Tytuł</label>
                <noteForm:input path="title" type="text" class="form-control" placeholder="Title" required="" maxlength="50"/>
                <br/>
            </div>

            <div class="form-group" >
                <label class="text-secondary">Opis</label>
                <noteForm:textarea path="desc" type="text" class="form-control" placeholder="Description" required="" rows="10"/>
                <br/>
            </div>

            <div class="button">
                <button type="submit" class="btn btn-block btn-lg text-center btn-warning text-white">Zapisz</button>
            </div>

        </noteForm:form>

        <div class="alert">
            <% if(session.getAttribute("noteAlert") != null &&
                    !session.getAttribute("noteAlert").toString().isEmpty() &&
                    session.getAttribute("noteAlert") != AddEnum.Default) { %>

                <% if (session.getAttribute("noteAlert") == AddEnum.Success) { %>
                    <div class="alert alert-error alert-success" role="alert" id="loginAlert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">×</button>
                         <h4 class="alert-heading">Notatka została dodana poprawnie</h4>
                <% } else if (session.getAttribute("noteAlert") == AddEnum.Exist) { %>
                    <div class="alert alert-error alert-danger" role="alert" id="loginAlert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">×</button>
                         <h4 class="alert-heading">Błąd</h4>
                         <p class="mb-0">Niestety notatka o podanym <b>tytule</b> została już utworzona dla twojego konta. Proszę wybrać inny i spróbować ponownie. </p>
                <% } else if (session.getAttribute("noteAlert") == AddEnum.EmptyField) { %>
                    <div class="alert alert-error alert-danger" role="alert" id="loginAlert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">×</button>
                        <h4 class="alert-heading">Błąd</h4>
                         <p class="mb-0">Tytuł nie może być pusty</p>
                <% } else if (session.getAttribute("noteAlert") == AddEnum.Failure) { %>
                        <div class="alert alert-error alert-danger" role="alert" id="loginAlert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">×</button>
                            <h4 class="alert-heading">Błąd</h4>
                            <p class="mb-0">Coś poszło nie tak. Proszę spróbowac ponownie później. </p>
                <% } %>
            </div>

            <% } %>
        </div>
                        <div class="row p-2" style="margin-top: 40px;">
                            <a class="btn navbar-btn ml-2 text-white btn-warning" href="/note/back" data-toggle=""> Powrót </a>
                        </div>

                        <div class="row p-2">
                            <a class="btn navbar-btn ml-2 text-white btn-danger" href="/user/logout" data-toggle=""> Wyloguj </a>
                        </div>
        </div>
</div>
</div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

</body>
</html>