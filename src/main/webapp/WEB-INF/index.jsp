
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
    <script src="webjars/angularjs/1.5.8/angular-animate.js"></script>
    <script src="webjars/angularjs/1.5.8/angular-touch.js"></script>
    <script src="webjars/momentjs/2.15.1/min/moment.min.js"></script>
    <script src="webjars/momentjs/2.15.1/min/locales.min.js"></script>
    <script src="webjars/humanize-duration/3.7.1/humanize-duration.js"></script>
    <script src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <script src="http://angular-ui.github.io/ui-router/release/angular-ui-router.min.js"></script>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC2FSxC8WmlIEegJ3-DOKWc_f3CNoAhxzo"></script>
    <script src="resources/static/js/ui-bootstrap-tpls-2.2.0.min.js"></script>
    <script src="resources/static/js/routerApp.js"></script>
    <script src="resources/static/js/controller.js"></script>
    <script src="resources/static/js/admincontroller.js"></script>
    <script src="resources/static/js/angularTimer.js"></script>

    <title>Bike rental service</title>
</head>
<body ng-app="routerApp" class="no-js">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mainNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>

                <a class="navbar-brand" href="#/">City bike rental service</a>

            </div>

            <div class="collapse navbar-collapse" id="mainNavbar">
            <ul class="nav navbar-nav">
                <li><a href="#/about">About</a></li>
                <li><a href="#/news">News</a></li>
                <li><a href="#/gallery">Gallery</a></li>
                <li><a href="#/contact">Contact</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="#/registration"><span class="glyphicon glyphicon-user"></span> Register</a></li>
                <li><a href="#/login"><span class="glyphicon glyphicon-log-in"></span> Log in</a></li>
            </ul>
            </div>
        </div>
    </nav>

    <div class="container-fluid">
    <div class="row content">
        <div id="nav_menu_left" class="panel panel-default col-sm-2 sidenav">
            <div class="panel-heading">
                <h4>Support menu</h4>
            </div>
            <div class="panel-body">
                <a href="#/resetpassword">Forgot password?</a>
                <br><a href="#/resendactivation">Resend activation link</a>
                <br><a href="#/adminmail">Send a mail to administration</a>
            </div>
        </div>
            <div class="panel panel-default col-sm-8 text-left" ui-view></div>

            <div ng-controller="mainPageController" class="panel panel-default col-sm-2 sidenav">
                <div class=panel-heading>
                    <h4>Useful links</h4>
                </div>
                <div class="panel-body">
                    <a ng-show="user != null" href="#/userpanel">
                    Userpanel
                    </a>
                    <span ng-show="user == null">{{info}}</span>
                </div>
            </div>
        </div>
    </div>
    <br>
        <div class="panel panel-danger" ng-show="error != null">
        <div ng-show="error.status != null">Error status {{error.status}}</div>
        <div class="panel-heading" ng-show="error.cause != null">{{error.cause}}</div>
         <div class="panel-body">{{error.message}}</div></div>
</body>

</html>