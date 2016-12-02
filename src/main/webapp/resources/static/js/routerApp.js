var routerApp = angular.module('routerApp', ['ui.router', 'ngCookies', 'timer', 'ui.bootstrap']);

routerApp.config(function($stateProvider, $urlRouterProvider, $cookiesProvider) {

    $urlRouterProvider.otherwise('/main');

    $stateProvider
        // MAIN PAGE STATES AND NESTED VIEWS OF USER'S PANEL ========================================
        .state('main', {
            url: '/main',
            templateUrl: 'resources/static/views/main.html',
            controller: 'mainPageController'
        })
        .state('main.login', {
            url: '/login',
            templateUrl: 'resources/static/views/login.html',
            controller: 'loginController'
        })
        .state('main.about',{
            url: '/about',
            templateUrl: 'resources/static/views/about.html'
        })
        .state('main.news',{
            url: '/news',
            templateUrl: 'resources/static/views/news.html'
        })
        .state('main.gallery',{
            url: '/gallery',
            templateUrl: 'resources/static/views/gallery.html'
        })
        .state('main.contact',{
            url: '/contact',
            templateUrl: 'resources/static/views/contact.html'
        })
        .state('main.resetpassword', {
            url: '/resetpassword',
            templateUrl: 'resources/static/views/resetpassword.html',
            controller: 'resetPassController'
        })
        .state('main.resetpasswordPage', {
            url: '/resetpasswordpage/:token',
            templateUrl: 'resources/static/views/resetpasswordPage.html',
            controller: 'resetPassPageController'
        })
        .state('main.registration', {
            url: '/registration',
            templateUrl: 'resources/static/views/registration.html',
            controller: 'registrationController'
        })
        .state('main.registrationConfirm', {
            url: '/registrationconfirm/:token',
            templateUrl: 'resources/static/views/registrationConfirm.html',
            controller: 'registrationConfirmController'
        })
        .state('main.resendactivation', {
            url: '/resendactivation',
            templateUrl: 'resources/static/views/resendactivation.html',
            controller: 'resendActTokenController'
        })
        .state('main.userpanel', {
            url: '/userpanel',
            templateUrl: 'resources/static/views/userpanel.html',
            controller: 'userpanelController'
        })
        .state('main.userpanel.stations', {
            url: '/stations',
            templateUrl: 'resources/static/views/stations.html',
            controller: 'stationsController'
        })
        .state('main.userpanel.stations.station', {
            url: '/station/:id',
            templateUrl: 'resources/static/views/station.html',
            controller: 'slotController'
        })
        .state('main.userpanel.payments', {
            url: '/payments',
            templateUrl: 'resources/static/views/payments.html',
            controller: 'paymentController'
        })


//        ADMIN PANEL STATES
        .state('main.adminPanel', {
            url: '/adminPanel',
            templateUrl: 'resources/static/views/admin/adminpanel.html',
            controller: 'adminPanelController'
        })
        .state('main.adminPanel.bikeadmin', {
            url: '/bikeadmin',
            templateUrl: 'resources/static/views/admin/bikeadmin.html',
            controller: 'bikeAdminController'
        })
        .state('main.adminPanel.bikeadmin.editbike', {
            url: '/editbike/:id',
            templateUrl: 'resources/static/views/admin/editbike.html',
            controller: 'editBikeController'
        })
        .state('main.adminPanel.stationadmin', {
            url: '/stationadmin',
            templateUrl: 'resources/static/views/admin/stationadmin.html',
            controller: 'stationAdminController'
        })
        .state('main.adminPanel.stationadmin.addstation', {
            url: '/addstation',
            templateUrl: 'resources/static/views/admin/addstation.html',
            controller: 'stationAdminController'
        })
        .state('main.adminPanel.stationadmin.editstation', {
            url: '/editstation/:id',
            templateUrl: 'resources/static/views/admin/editstation.html',
            controller: 'editStationController'
        })
        .state('main.adminPanel.priceintervaladmin', {
            url: '/priceintervaladmin',
            templateUrl: 'resources/static/views/admin/priceintervaladmin.html',
            controller: 'priceIntController'
        })
        .state('main.adminPanel.priceintervaladmin.addpriceinterval', {
            url: '/addpriceinterval',
            templateUrl: 'resources/static/views/admin/addpriceinterval.html',
            controller: 'priceIntController'
        })
        .state('main.adminPanel.priceintervaladmin.editpriceinterval', {
            url: '/editpriceinterval/:id',
            templateUrl: 'resources/static/views/admin/editpriceinterval.html',
            controller: 'editPriceIntController'
        })
        .state('main.adminPanel.statistics', {
            url: '/statistics',
            templateUrl: 'resources/static/views/admin/statistics.html',
            controller: 'statisticsController'
        });
});