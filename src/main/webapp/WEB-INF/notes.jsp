<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Notes</title>
</head>
<body>


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
                <%--<div class="col-md-4">--%>
                    <%--<a href="#">--%>
                    <%--</a>--%>
                    <button type="button" onclick="showItem(${item.getId()})">
                        <c:out value="${item.getTitle()}"/>
                    </button>
                <%--</div>--%>

                <%--<div class="col-md-4">--%>
                    <a href="/note/edit?id=${item.getId()}">Edytuj</a>
                <%--</div>--%>

                <%--<div class="col-md-4">--%>
                    <a href="/note/delete?id=${item.getId()}">Usuń</a>
                <%--</div>--%>

            </div>
        </c:forEach>

        <div id="noteContent">

        </div>

    </div>


    <script>

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
                    document.getElementById("noteContent").innerHTML =
                        "<div>" +
                        "<label>" + data.title +"</label></br>"+
                        "<label>" + data.insertDate +"</label></br>"+
                        "<label>" + data.desc +"</label></br>"+
                        "</div>"
                }
            });

            request.fail(function () {
                console.log("Ajax Error");
                window.location = "/user/login";
            });
        };

    </script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
</div>

</body>
</html>