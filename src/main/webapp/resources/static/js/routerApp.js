var routerApp = angular.module('routerApp', ['ui.router', 'ngCookies', 'timer']);

routerApp.config(function($stateProvider, $urlRouterProvider, $cookiesProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider
        // MAIN PAGE STATES AND NESTED VIEWS OF USER'S PANEL ========================================
        .state('login', {
            url: '/login',
            templateUrl: 'resources/static/views/login.html',
            controller: 'loginController'
        })
        .state('about',{
            url: '/about',
            templateUrl: 'resources/static/views/about.html'
        })
        .state('news',{
            url: '/news',
            templateUrl: 'resources/static/views/news.html'
        })
        .state('gallery',{
            url: '/gallery',
            templateUrl: 'resources/static/views/gallery.html'
        })
        .state('contact',{
            url: '/contact',
            templateUrl: 'resources/static/views/contact.html'
        })
        .state('resetpassword', {
            url: '/resetpassword',
            templateUrl: 'resources/static/views/resetpassword.html',
            controller: 'resetPassController'
        })
        .state('resetpasswordPage', {
                    url: '/resetpasswordpage:token',
                    templateUrl: 'resources/static/views/resetpasswordPage.html',
                    controller: 'resetPassPageController'
                })
        .state('registration', {
            url: '/registration',
            templateUrl: 'resources/static/views/registration.html',
            controller: 'registrationController'
        })
        .state('registrationConfirm', {
                    url: '/registrationConfirm?token',
                    templateUrl: 'resources/static/views/registrationConfirm.html',
                    controller: 'registrationConfirmController'
                })
        .state('resendactivation', {
            url: '/resendactivation',
            templateUrl: 'resources/static/views/resendactivation.html',
            controller: 'resendActTokenController'
        })
        .state('userpanel', {
            url: '/userpanel',
            templateUrl: 'resources/static/views/userpanel.html',
            controller: 'userpanelController'
        })
        .state('userpanel.stations', {
            url: '/stations',
            templateUrl: 'resources/static/views/stations.html',
            controller: 'stationsController'
        })
        .state('userpanel.stations.station', {
            url: '/station/:id',
            templateUrl: 'resources/static/views/station.html',
            controller: 'slotController'
        })
        .state('userpanel.payments', {
            url: '/payments',
            templateUrl: 'resources/static/views/payments.html',
            controller: 'paymentController'
        })


//        ADMIN PANEL STATES
        .state('adminPanel', {
            url: '/adminPanel',
            templateUrl: 'resources/static/views/admin/adminpanel.html',
            controller: 'adminPanelController'
        })
        .state('adminPanel.bikeadmin', {
            url: '/bikeadmin',
            templateUrl: 'resources/static/views/admin/bikeadmin.html',
            controller: 'bikeAdminController'
        })
        .state('adminPanel.bikeadmin.editbike', {
            url: '/editbike/:id',
            templateUrl: 'resources/static/views/admin/editbike.html',
            controller: 'editBikeController'
        })
        .state('adminPanel.stationadmin', {
            url: '/stationadmin',
            templateUrl: 'resources/static/views/admin/stationadmin.html',
            controller: 'stationAdminController'
        })
        .state('adminPanel.stationadmin.addstation', {
            url: '/addstation',
            templateUrl: 'resources/static/views/admin/addstation.html',
            controller: 'stationAdminController'
        })
        .state('adminPanel.stationadmin.editstation', {
            url: '/editstation/:id',
            templateUrl: 'resources/static/views/admin/editstation.html',
            controller: 'editStationController'
        })
        .state('adminPanel.priceintervaladmin', {
            url: '/priceintervaladmin',
            templateUrl: 'resources/static/views/admin/priceintervaladmin.html',
            controller: 'priceIntController'
        })
        .state('adminPanel.priceintervaladmin.addpriceinterval', {
            url: '/addpriceinterval',
            templateUrl: 'resources/static/views/admin/addpriceinterval.html',
            controller: 'priceIntController'
        })
        .state('adminPanel.priceintervaladmin.editpriceinterval', {
            url: '/editpriceinterval/:id',
            templateUrl: 'resources/static/views/admin/editpriceinterval.html',
            controller: 'editPriceIntController'
        })
        .state('adminPanel.statistics', {
            url: '/statistics',
            templateUrl: 'resources/static/views/admin/statistics.html',
            controller: 'statisticsController'
        });
});