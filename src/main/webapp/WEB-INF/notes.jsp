<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div class="col-md-5">
            <h1 class="mb-6 text-center text-secondary" style="padding-bottom: 20px">Twoje notatki</h1>
        </div>
        <div class="col-md-2"></div>
        <div class="col-md-5">
            <h1 class="mb-6 text-center text-secondary" style="padding-bottom: 20px">Podgląd</h1>
        </div>
    </div>


    <div class="row p-2"  >
        <div class="col-md-5">

                <c:choose>
                    <c:when test="${empty notes}">
                        <div class="container">
                            <H3>Nie masz żadnych notatek</H3>
                        </div>
                    </c:when>
                </c:choose>

                    <c:forEach items="${notes}" var="item">
                        <div class="row border border-success" style="margin-bottom: 5px">

                            <div class="col-md-6" style="align-self: center">
                                <p style="align-self: center"> ${item.getTitle()}</p>
                            </div>

                            <div class="col-md-2" style="align-self: center">
                                <button id="titleButton" class="titleButtonClass" type="button" onclick="editAction(${item.getId()})">Podgląd</button>
                            </div>

                            <div class="col-md-2" style="align-self: center">
                                <button type="button" onclick="editAction(${item.getId()})">Edytuj</button>
                            </div>

                            <div class="col-md-2" style="align-self: center">
                                <a href="/note/delete?id=${item.getId()}">Usuń</a>
                            </div>

                        </div>
                    </c:forEach>


            <div class="row p-2" style="margin-top: 40px;">
                <a class="btn navbar-btn ml-2 text-white btn-warning" href="/note/back" data-toggle=""> Powrót </a>
            </div>

            <div class="row p-2">
                <a class="btn navbar-btn ml-2 text-white btn-danger" href="/user/logout" data-toggle=""> Wyloguj </a>
            </div>

        </div>

        <div class="col-md-2"></div>

        <div class="col-md-5">

                <div class="form-group" >
                    <label class="text-secondary">Tytuł</label>
                    <input id="titleDetails" path="title" type="text" class="form-control" placeholder="" required="" maxlength="50" disabled=true/>
                </div>

                <div class="noteDate" >
                    <label class="label">Data</label>
                    <input id="dateDetails" path="title" type="date" class="titleInput" placeholder="" required="" maxlength="50" disabled=true/>
                </div>

                <div class="form-group" >
                    <label class="text-secondary">Opis</label>
                    <textarea id="descDetails" path="desc" type="text" class="form-group" placeholder="" required="" rows="10" disabled = true></textarea>
                </div>

                <div class="form-group">
                    <button id="saveButton" type="submit" class="btn btn-block btn-lg text-center btn-warning text-white" disabled = true onclick="edit()">Zapisz</button>
                </div>

                <div class="addAlertSuccess collapse" id="addedAlert">
                    <H3 id="testSuccess">Dodano</H3>
                </div>

                <div class="addAlertFailure collapse" id="errorAlert">
                    <H3 id="testError">Nie dodano</H3>
                </div>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>

    <script>

        var itemId = 0;

        function showItem(id) {

            var request = $.ajax({
                url: '/note/item',
                type: 'GET',
                data: {id: id},
                contentType: 'application/json; charset=utf-8'
            });

            request.done(function (data) {

                if (data == null ||
                    data == undefined ||
                    data.title == undefined) {
                    console.log("Invalid session");
                    window.location = "/user/login";
                } else {

                    $('#titleDetails').val(data.title);
                    $('#dateDetails').val(data.insertDateString);
                    $('#descDetails').val(data.desc);

                    itemId = data.id;
                }
            });

            request.fail(function () {
                console.log("Ajax Error");
                window.location = "/user/login";
            });
        };

        function edit() {

            var request = $.ajax({
               url:'/note/edit',
               type: 'POST',
               data: JSON.stringify({
                   id: itemId,
                   title: $('#titleDetails').val(),
                   desc: $('#descDetails').val(),
                   insertDateString: $('#dateDetails').val()
               }),
                contentType: 'application/json; charset=utf-8'
            });

            request.done(function (data) {

                if (data.enumValue.localeCompare("Invalid") == 0) {
                    window.location = "/user/login";
                } else if (data.enumValue.localeCompare("Success") == 0) {
                    $('#addedAlert').show();
                    $('#errorAlert').hide();
                    $('#testSuccess').text("Notatka została prawidłowo zapisana.");
                } else if (data.enumValue.localeCompare("Failure") == 0) {
                    $('#addedAlert').hide();
                    $('#errorAlert').show();
                    $('#testError').text("Coś poszło nie tak. Proszę spróbować ponownie później.");
                } else if (data.enumValue.localeCompare("WrongDate") == 0) {
                    $('#addedAlert').hide();
                    $('#errorAlert').show();
                    $('#testError').text("Niepoprawna data. Musi się ona zawierac między rokiem 1 stycznia 1980 a 1 stycznia 2042.");
                } else if (data.enumValue.localeCompare("Exist") == 0) {
                    $('#addedAlert').hide();
                    $('#errorAlert').show();
                    $('#testError').text("Notatka o podanym tytule już istniieje. Proszę wybrać inny.");
                }
            });

            request.fail(function () {
                $('#addedAlert').hide();
                $('#errorAlert').hide();
             if (data.enumValue.localeCompare("Invalid") == 0) {
                 window.location = "/user/login";
             } else if (data.enumValue.localeCompare("Failure") == 0) {
                 $('#addedAlert').hide();
                 $('#errorAlert').show();
                 $('#testError').text("Coś poszło nie tak. Proszę spróbować ponownie później.");
             }
            });
        }

        function editAction(id) {

            showItem(id);
            console.log("EditButton");

            $('#titleDetails')[0].disabled = false;
            $('#dateDetails')[0].disabled = false;
            $('#descDetails')[0].disabled = false;
            $('#saveButton')[0].disabled = false;
        };

        $('.titleButtonClass').on('click', function () {
            $('#titleDetails')[0].disabled = true;
            $('#dateDetails')[0].disabled = true;
            $('#descDetails')[0].disabled = true;
            $('#saveButton')[0].disabled = true;
        });

    </script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</body>
</html>