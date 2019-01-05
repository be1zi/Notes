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

    <div class="row"  >

        <div class="col-md-6">
            <h1>Twoje notatki</h1>

            <div class="notesList" >
                <c:choose>
                    <c:when test="${empty notes}">
                        <div class="container">
                            <H3>Nie masz żadnych notatek</H3>
                        </div>
                    </c:when>
                </c:choose>

                <div class="notes">

                    <c:forEach items="${notes}" var="item">
                        <div class="row">
                            <div class="col-md-8">
                                <%--<a href="#">--%>
                                <%--</a>--%>
                                <button id="titleButton" class="titleButtonClass" type="button" onclick="showItem(${item.getId()})">
                                    <c:out value="${item.getTitle()}"/>
                                </button>
                            </div>

                            <div class="col-md-2">
                                <button type="button" onclick="editAction(${item.getId()})">Edytuj</button>
                            </div>

                            <div class="col-md-2">
                                <a href="/note/delete?id=${item.getId()}">Usuń</a>
                            </div>

                        </div>
                    </c:forEach>

                    <div id="noteContent">

                    </div>
                </div>
            </div>

        </div>
        <div class="col-md-4">
            <h1>Podgląd</h1>
            <div class="noteDetails">

                    <div class="noteTitle" >
                        <label class="label">Tytuł</label>
                        <input id="titleDetails" path="title" type="text" class="titleInput" placeholder="" required="" maxlength="50" disabled=true/>
                        <br/>
                    </div>

                    <div class="noteDate" >
                        <label class="label">Data</label>
                        <input id="dateDetails" path="title" type="date" class="titleInput" placeholder="" required="" maxlength="50" disabled=true/>
                        <br/>
                    </div>

                    <div class="noteDesc" >
                        <label class="label">Opis</label>
                        <textarea id="descDetails" path="desc" type="text" class="descInput" placeholder="" required="" rows="10" disabled = true></textarea>
                        <br/>
                    </div>

                <div class="col-md-6">
                    <div class="button">
                        <button id="saveButton" type="submit" class="btn btn-block btn-lg text-center btn-warning text-white" disabled = true onclick="edit()">Zapisz</button>
                    </div>
                </div>

                <div class="addAlertSuccess" hidden = true>
                    <H3>Dodano</H3>
                </div>

                <div class="addAlertFailure" hidden = true>
                    <H3>Nie dodano</H3>
                </div>

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
                console.log("Done");
                console.log(data.enumValue);

                if (data.enumValue.localeCompare("Invalid") == 0) {
                    console.log("Invalid session");
                    window.location = "/user/login";
                } else if (data.enumValue.localeCompare("Success") == 0){
                    $('#addAlertSuccess').show();
                    $('#addAlertFailure').hide();
                } else if (data.enumValu.localeCompare("Failure") == 0){
                    $('#addAlertSuccess').hide();
                    $('#addAlertFailure').show();
                }
            });

            request.fail(function () {
                console.log("Fail");

                $('#addAlertSuccess').hide();
                $('#addAlertFailure').hide();

             if (data.enumValue.localeCompare("Invalid") == 0) {
                 console.log("Invalid session");
                 window.location = "/user/login";
             } else if (data.enumValu.localeCompare("Failure") == 0) {
                 $('#addAlertSuccess').hide();
                 $('#addAlertFailure').show();
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

//        $('#titleButton').on('click', function () {
//            console.log("TitleButton");
//            $('#titleDetails')[0].disabled = true;
//            $('#dateDetails')[0].disabled = true;
//            $('#descDetails')[0].disabled = true;
//            $('#saveButton')[0].disabled = true;
//        });



    </script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</body>
</html>