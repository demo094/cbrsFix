
<%@page
    language="java" contentType="text/html" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/static/css/app.css">
    <script src="webjars/jquery/1.11.1/jquery.js"></script>
    <script src="webjars/angularjs/1.5.8/angular.js"></script>
    <script src="webjars/angularjs/1.5.8/angular-resource.js"></script>
    <script src="webjars/angularjs/1.5.8/angular-route.js"></script>
    <script src="webjars/angularjs/1.5.8/angular-cookies.js"></script>
    <script src="http://angular-ui.github.io/ui-router/release/angular-ui-router.min.js"></script>
    <script src="resources/static/js/routerApp.js"></script>
    <script src="resources/static/js/controller.js"></script>
    <script src="resources/static/js/admincontroller.js"></script>
    <script src="resources/static/js/timer.js"></script>


    <title>Bike rental service</title>
</head>
<body style="margin-left:1%" ng-app="routerApp" class="no-js">
     Welcome to the city's bike rental service!
     <br><a href="#/login">Log in to the service</a>
     <br><a href="#/resetpassword">Forgot password?</a>
     <br>
     <br><a href="#/registration">Register</a>
     <br><a href="#/resendactivation">Resend activation link</a>

    <br>
    <div ui-view></div>
    <br>
        <div ng-show="error != null">
        <div ng-show="error.status != null">Error status {{error.status}}</div>
        <div ng-show="error.cause != null">{{error.cause}}</div>
         <br>{{error.message}}</div>
</body>

</html>