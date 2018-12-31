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

<h1>Cenzura</h1>

<div class="dashboard" >

    <div class="addSection">

        <censureForm:form modelAttribute="censure" action="/censure/save" method="post">

            <div class="addCensureText" >
                <label class="label">Tekst</label>
                <censureForm:input id="censureText" path="text" type="text" class="textInput" placeholder="Tekst" required="" maxlength="50"/>
                <label class="alert" id="alert" hidden="true"> Pole nie może zawierać cyfr </label>
                <br/>
            </div>

            <div class="addNoteTitle" >
                <label class="label">Opis</label>
                <censureForm:input path="pattern" type="text" class="patternInput" placeholder="Wzór" required=""/>
                <br/>
            </div>

            <div class="button">
                <button type="submit" id="submitButton" class="btn btn-block btn-lg text-center btn-warning text-white">Zapisz</button>
            </div>

        </censureForm:form>

        <div class="alert">
            <% if(session.getAttribute("alert") != null &&
                    !session.getAttribute("alert").toString().isEmpty()) { %>

            <div class="alert alert-error alert-danger" role="alert" id="loginAlert">
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

    <div class="data">

        <c:choose>
            <c:when test="${empty censures}">
                <div class="container">
                    <H3>Nie masz żadnych ocenzurowanych zwrotów</H3>
                </div>
            </c:when>
        </c:choose>

        <div class="notes">

            <c:forEach items="${censures}" var="item">
                <div class="row">
                        <%--<div class="col-md-4">--%>
                        <%--<a href="#">--%>
                        <%--</a>--%>
                    <label>
                        <c:out value="${item.getText()}"/>
                    </label>

                     <label>
                         <c:out value="${item.getPattern()}"/>
                      </label>

                </div>
            </c:forEach>
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

</div>

</body>
</html>