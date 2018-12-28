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
                <a href="#">
                    <c:out value="${item.getTitle()}"/>
                </a>
            </div>
        </c:forEach>


    </div>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

</div>

</body>
</html>