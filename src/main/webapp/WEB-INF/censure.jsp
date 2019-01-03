<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="censureForm" uri="http://www.springframework.org/tags/form" %>
<%@ page import="notes.Helper.Enum.AddEnum" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <div class="col-md-4">
            <h1 class="mb-6 text-center text-secondary" style="padding-bottom: 20px">Cenzura</h1>
        </div>
        <div class="col-md-2"></div>
        <div class="col-md-4">
            <h1 class="mb-6 text-center text-secondary" style="padding-bottom: 20px">Ocenzurowane zwroty</h1>
        </div>
    </div>

    <div class="row p-2">

        <div class="col-md-4">

        <censureForm:form modelAttribute="censure" action="/censure/save" method="post">

            <div class="form-group" >
                <label class="text-secondary">Tekst</label>
                <censureForm:input id="censureText" path="text" type="text" class="form-control" placeholder="Tekst" required="" maxlength="50"/>
                <label class="alert" id="alert" hidden="true"> Pole nie może zawierać cyfr </label>
                <br/>
            </div>

            <div class="form-group" >
                <label class="text-secondary">Opis</label>
                <censureForm:input path="pattern" type="text" class="form-control" placeholder="Wzór" required=""/>
                <br/>
            </div>

            <div class="loginButton text-center" style="margin-top: 20px;">
                <button type="submit" id="submitButton" class="btn btn-block btn-lg text-center btn-warning text-white">Zapisz</button>
            </div>

            <div class="row p-2" style="margin-top: 40px;">
                <a class="btn navbar-btn ml-2 text-white btn-warning" href="/censure/back" data-toggle=""> Powrót </a>
            </div>

            <div class="row p-2">
                <a class="btn navbar-btn ml-2 text-white btn-danger" href="/user/logout" data-toggle=""> Wyloguj </a>
            </div>

        </censureForm:form>

        <div class="alert">
            <% if(session.getAttribute("alert") != null &&
                    !session.getAttribute("alert").toString().isEmpty()) { %>

            <div class="alert alert-error alert-success" role="alert" id="loginAlert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">×</button>

                <% if (session.getAttribute("alert") == AddEnum.Success) { %>
                <h4 class="alert-heading">Cenzura została dodana poprawnie</h4>
                <% } else if (session.getAttribute("alert") == AddEnum.Exist) { %>
                <h4 class="alert-heading">Błąd</h4>
                <p class="mb-0">Niestety cenzura dla podanego <b>tekstu</b> już iistnieje. Proszę wybrać inny i spróbować ponownie. </p>
                <% } else if (session.getAttribute("alert") == AddEnum.EmptyField) { %>
                <h4 class="alert-heading">Błąd</h4>
                <p class="mb-0">Pola niie mogą być puste!</p>
                <% } else if (session.getAttribute("alert") == AddEnum.Failure) { %>
                <h4 class="alert-heading">Błąd</h4>
                <p class="mb-0">Coś poszło nie tak. Proszę spróbowac ponownie później. </p>
                <% } %>
            </div>

            <% } %>

        </div>
        </div>

        <div class="col-md-2"></div>

        <div class="col-md-4">
            <c:choose>
                <c:when test="${empty censures}">
                    <div class="row p-2">
                        <h3 class="mb-4 text-center" style="padding-bottom: 20px">Nie masz żadnych ocenzurowanych zwrotów</h3>
                    </div>
                </c:when>
            </c:choose>

            <c:forEach items="${censures}" var="item">
                <div class="row border border-success" style="margin-bottom: 5px">

                     <div class="col-md-6">
                        <label class="text-secondary">
                            <c:out value="${item.getText()}"/>
                        </label>
                    </div>
                    <div class="col-md-6">
                        <label class="text-secondary">
                            <c:out value="${item.getPattern()}"/>
                        </label>
                    </div>
                </div>
            </c:forEach>

        </div>
        <div class="col-md-2"></div>

    </div>
</div>


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>
        <script type="text/javascript">

            $(document).ready(function () {
                $('#censureText').on('keyup', function () {
                    if ($('#censureText').val() != null && !($('#censureText').val() == '')) {
                        if ($('#censureText').val().match(".*\\d+.*")) {
                            $('#censureText').css('border-color', 'red');
                            $('#submitButton')[0].disabled = true;
                            $('#alert').show();
                        } else {
                            $('#censureText').css('border-color', 'white');
                            $('#submitButton')[0].disabled = false;
                            $('#alert').hide();

                        }
                    } else {
                        $('#censureText').css('border-color', 'white');
                        $('#submitButton')[0].disabled = false;
                        $('#alert').hide();
                    }
                });
            });

        </script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</body>
</html>