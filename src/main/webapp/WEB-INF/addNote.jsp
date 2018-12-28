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
            <noteForm:input path="title" type="text" class="titleInput" placeholder="Title" required=""/>
            <br/>
        </div>

        <div class="addNoteTitle" >
            <label class="label">Opis</label>
            <noteForm:input path="desc" type="text" class="descInput" placeholder="Description" required=""/>
            <br/>
        </div>

        <div class="button">
            <button type="submit" class="btn btn-block btn-lg text-center btn-warning text-white">Zapisz</button>
        </div>

    </noteForm:form>


    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

</div>

</body>
</html>