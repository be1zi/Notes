<%@ page import="notes.Helper.Enum.LoginEnum" %>
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

    <div class="py-5 bg-primary">
        <div class="container">
            <div class="row bg-primary">
                <div class="col-md-3"> </div>
                <div class="col-md-6">
                    <div class="card text-white p-5 bg-primary border border-success">
                        <div class="card-body">
                            <h1 class="mb-4 text-center text-secondary" style="padding-bottom: 20px">Home</h1>

                            <userForm:form modelAttribute="user" action="/user/check" method="post">
                                <% if(session.getAttribute("userStatus") == LoginEnum.LoginNotFound) { %>

                                    <div class="alert alert-error alert-danger" role="alert" id="loginAlert">
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">×</button>
                                        <h4 class="alert-heading">Nie udało się zalogować</h4>
                                        <p class="mb-0">Niestety konto o podanym <b>loginie</b> nie istnieje. Proszę wybrać inny i spróbować ponownie. </p>
                                    </div>
                                    <% } %>

                                <div class="form-group">
                                    <label class="text-secondary">Login</label>
                                    <userForm:input id="loginInput" path="login" type="text" class="form-control" placeholder="Login" required=""/>
                                </div>

                                <% if(session.getAttribute("userStatus") == LoginEnum.WrongPassword){%>

                                    <div class="alert alert-error alert-danger" role="alert" id="emailAlert">
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">×</button>
                                        <h4 class="alert-heading">Nie udało się zalogować</h4>
                                        <p class="mb-0">Niestety podane<b> hasło</b> nie pasuje do loginu. Proszę wybrać inne lub użyć opcji przypominania haseł.&nbsp;</p>
                                    </div>

                                <%} %>
                                <div class="form-group">
                                    <label class="text-secondary">Hasło</label>
                                    <userForm:input id="passwordInput" path="password" type="password" class="form-control" placeholder="Hasło" required=""/>
                                </div>

                                <div class="loginButton text-center" style="margin-top: 40px;">
                                    <button type="submit" class="btn btn-block btn-lg text-center btn-warning text-white">Zaloguj</button>
                                </div>

                                <div class="loginButton text-center" style="margin-top: 20px;">
                                    <button type="button" class="btn btn-block btn-lg text-center btn-warning text-white" onclick="register()">Zarejestruj</button>
                                </div>


                                <div class="alert alert-error alert-warning collapse" role="alert" id="registerAlert" style="margin-top: 20px">
                                    <%--<button type="button" class="close" data-dismiss="alert" aria-label="Close">×</button>--%>
                                    <h4 class="alert-heading" id="registerTopAlert">Nie udało się zarejestrowac</h4>
                                    <p class="mb-0" id="registerBottomAlert">Niestety konto o podanym <b>loginie</b> nie istnieje. Proszę wybrać inny i spróbować ponownie. </p>
                                </div>

                            </userForm:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>

<script>

    function register() {

        var request = $.ajax({
            url: '/user/register',
            type: 'POST',
            data: JSON.stringify({
                login: $('#loginInput').val(),
                password: $('#passwordInput').val()
            }),
            contentType: 'application/json; charset=utf-8'
        });

        request.done(function (data) {

            if (data.enumValue.localeCompare("Success") == 0) {
                $('#registerAlert').show();
                $('#registerTopAlert').text("Udało się zarejestrować.");
                $('#registerBottomAlert').text("Możesz się teraz zalogować.");
            } else if (data.enumValue.localeCompare("Failure") == 0) {
                $('#registerAlert').show();
                $('#registerTopAlert').text("Nie udało się zarejestrować.");
                $('#registerBottomAlert').text("Coś poszło nie tak. Proszę spróbować ponownie później");
            } else if (data.enumValue.localeCompare("EmptyField") == 0) {
                $('#registerAlert').show();
                $('#registerTopAlert').text("Nie udało się zarejestrować.");
                $('#registerBottomAlert').text("Wszystkie pola muszą zostać poprawnie wypełnione");
            } else if (data.enumValue.localeCompare("Exist") == 0) {
                $('#registerAlert').show();
                $('#registerTopAlert').text("Nie udało się zarejestrować.");
                $('#registerBottomAlert').text("Użytkownik o podanej nazwie już istnieje");
            }
        });

        request.fail(function () {
            $('#registerAlert').hide();

            if (data.enumValue.localeCompare("Failure") == 0) {
                $('#registerAlert').show();
                $('#registerTopAlert').text("Nie udało się zarejestrować.");
            }
        });
    }

    $(document).ready(function () {

        $('#loginInput').on('click', function () {
            $('#registerAlert').hide();
        });

        $('#passwordInput').on('click', function () {
            $('#registerAlert').hide();
        });

    });


</script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</body>
</html>