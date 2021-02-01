<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--@elvariable id="user" type="io.fulchr3356.SpringLogin.entities.User"--%>
<html>
<head>
    <title>Hello Spring MVC</title>
</head>

<body>
<h3>Login</h3>

<form:form method="POST"
           action="/login" modelAttribute="user">
    <table>
        <tr>
            <td><form:label path="username">Username</form:label></td>
            <td><form:input path="username"/></td>
        </tr>
        <tr>
            <td ><form:label path="password">
                Password</form:label></td>
            <td><form:input type="password" path="password"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
<form action="/register" method="GET">
<button type = "submit" >Register</button>
</form>
</body>
</html>