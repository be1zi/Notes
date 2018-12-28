<%@ page import="notes.Helper.Enum.AddNoteEnum" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="noteForm" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Notes</title>
</head>
<body>


<h1>Dodaj nową notatkę</h1>

<div class="Add" >


    <noteForm:form modelAttribute="note" action="save" method="post">


        <div class="addNoteTitle" >
            <label class="label">Tytuł</label>
            <noteForm:input path="title" type="text" class="titleInput" placeholder="Title" required="" maxlength="50"/>
            <br/>
        </div>

        <div class="addNoteTitle" >
            <label class="label">Opis</label>
            <noteForm:textarea path="desc" type="text" class="descInput" placeholder="Description" required="" rows="10"/>
            <br/>
        </div>

        <div class="button">
            <button type="submit" class="btn btn-block btn-lg text-center btn-warning text-white">Zapisz</button>
        </div>

    </noteForm:form>

    <div class="alert">
        <% if(session.getAttribute("alert") != null &&
                !session.getAttribute("alert").toString().isEmpty()) { %>

        <div class="alert alert-error alert-danger" role="alert" id="loginAlert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">×</button>

            <% if (session.getAttribute("alert") == AddNoteEnum.Success) { %>
                <h4 class="alert-heading">Notatka została dodana poprawnie</h4>
            <% } else if (session.getAttribute("alert") == AddNoteEnum.TitleExist) { %>
                 <h4 class="alert-heading">Błąd</h4>
                 <p class="mb-0">Niestety notatka o podanym <b>tytule</b> została już utworzona dla twojego konta. Proszę wybrać inny i spróbować ponownie. </p>
            <% } else if (session.getAttribute("alert") == AddNoteEnum.EmptyTitle) { %>
                <h4 class="alert-heading">Błąd</h4>
                 <p class="mb-0">Tytuł nie może być pusty</p>
            <% } else if (session.getAttribute("alert") == AddNoteEnum.Failure) { %>
                <h4 class="alert-heading">Błąd</h4>
                <p class="mb-0">Coś poszło nie tak. Proszę spróbowac ponownie później. </p>
            <% } %>
        </div>

        <% } %>
    </div>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

</div>

</body>
</html>