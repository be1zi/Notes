<%@ page import="notes.Helper.Enum.LoginEnum" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="userForm" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Notes</title>
</head>
<body>


<h1>Home</h1>

<div class="loginForm" >

    <userForm:form modelAttribute="user" action="check" method="post">


        <% if(session.getAttribute("userStatus") == LoginEnum.LoginNotFound) { %>

        <div class="alert alert-error alert-danger" role="alert" id="loginAlert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">×</button>
            <h4 class="alert-heading">Nie udało się zalogować</h4>
            <p class="mb-0">Niestety konto o podanym <b>loginie</b> nie istnieje. Proszę wybrać inny i spróbować ponownie. </p>
        </div>

        <% } %>

        <div class="login">
            <label class="loginLabel">Login</label>
            <userForm:input path="login" type="text" class="loginInput" placeholder="Login" required=""/>
        </div>

        <% if(session.getAttribute("userStatus") == LoginEnum.WrongPassword){%>

        <div class="alert alert-danger" role="alert" id="emailAlert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">×</button>
            <h4 class="alert-heading">Nie udało się zalogować</h4>
            <p class="mb-0">Niestety podane<b> hasło</b> nie pasuje do loginu. Proszę wybrać inne lub użyć opcji przypominania haseł.&nbsp;</p>
        </div>

        <%} %>

        <div class="password">
            <label class="passwordLabel">Hasło</label>
            <userForm:input path="password" type="password" class="passwordInput" placeholder="Password" required=""/>
        </div>

        <div class="button">
            <button type="submit" class="btn btn-block btn-lg text-center btn-warning text-white">Zaloguj</button>
        </div>

    </userForm:form>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

</div>

</body>
</html>