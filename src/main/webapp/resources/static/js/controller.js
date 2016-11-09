routerApp.controller('userpanelController', function($scope, $http, $cookies, $window, $interval){
    $scope.headingTitle = "User panel";
//    var storedBikeId;
    $http.get('api/userpanel')
        .then(function(response){
            $scope.userPanelTO = response.data;
//            storedBikeId = response.data.bikeId;
            $scope.serverTimeMillis = response.data.serverTime;
        }, function(response){
            $scope.error = response.data;
        });

//    if(storedBikeId != null){
//        $interval(function(){
//            $http.get('api/rentalBeginTime').then(function(response){
//                $scope.serverTimeMillis = response.data.rentalBeginTime;
//            }, function(response){
//                $scope.serverTimeMillis = null;
//            });
//        }, 5000);
//    }
//    else {
//        $interval.cancel();
//    }

    $scope.logout = function(){
        $cookies.remove('accessToken');
        $window.location.href = "index";
    };
});


routerApp.controller('stationsController', function($scope, $http){
       $scope.headingTitle = "List of available stations";
       $http.get('api/stations')
           .then(function(response){
               $scope.stationTOs = response.data;
           }, function(response){
                $scope.error = response.data;
           });
   });

routerApp.controller('slotController', function($scope, $http, $stateParams){
    var id = $stateParams.id;
    $scope.headingTitle = "List of slots on the station";
    $http.get('api/station/' + id)
        .then(function(response){
            $scope.stationTO = response.data;
        }, function(response){
            $scope.error = response.data;
        });
    $http.get('api/userbike/')
        .then(function(response){
            $scope.userBikeTO = response.data;
        }, function(response){
            $scope.error = response.data;
        });
});

routerApp.controller('rentalController', function($scope, $http, $state){
       $scope.rentBike = function(){
            var url = 'api/station/slot/'+ $scope.item.slotId +'/rent';
            $http.post(url)
            .then(function(response){
                document.getElementById("server_response").innerHTML = response.data.rentStatus;
                $state.reload();
            }, function(response){
                $scope.rentalError = response.data;
            });
       };

       $scope.returnBike = function(){
                   var url = 'api/station/slot/'+ $scope.item.slotId +'/return';
                   $http.post(url)
                   .then(function(response){
                        document.getElementById("server_response").innerHTML = response.data.rentStatus;

                        $state.reload();
                       }, function(response){
                        $scope.rentalError = response.data;
                   });
              };
   });

routerApp.controller('loginController', function($scope, $http, $location){
    $scope.login = function(){
        $http.post('login', $scope.credentials).then(function(response){
            $location.path('userpanel');
        }, function(response){
            $scope.loginError = response.data;
        });
    };
});


routerApp.controller('resetPassController', function($scope, $http, $window){
    $scope.sendPasswordMail = function(){
            $http.post('resetPassEmail', $scope.form).then(function(response){
                document.getElementById("response").innerHTML = response.data.status;
                $window.location.href = "index";
            }, function(response){
                $scope.error = response.data;
            });
    };
});

routerApp.controller('resetPassPageController', function($scope, $http){
    $scope.resetPassword = function(){
        $http.post('resetPassData', $scope.newPassword).then(function(response){
            $scope.resetResponse = response.data;
        }, function(response){
            $scope.resetError = response.data;
        });
    }
});

routerApp.controller('registrationController', function($scope, $http, $window, $timeout){
    $scope.registration = function(){
            $http.post('register', $scope.register).then(function(response){
                document.getElementById("response").innerHTML = response.data.status;
                $timeout(function(){
                    $window.location.href = "index";
                }, 5000);
            }, function(response){
                $scope.error = response.data;
            });
    };
});

routerApp.controller('registrationConfirmController', function($scope, $http){
    $http.get('registrationConfirm?token').then(function(response){
        $scope.confirmed.status = response.data.status;
    });
});

routerApp.controller('resendActTokenController', function($scope, $http, $window, $timeout){
       $scope.resend = function(){
               $http.post('resendActToken', $scope.resendtoken).then(function(response){
                   document.getElementById("response").innerHTML = response.data.status;
                   $timeout(function(){
                       $window.location.href = "index";
                   }, 5000);
               }, function(response){
                   $scope.error = response.data;
               });
       };
   });

routerApp.controller('paymentController', function($scope, $http, $state){
    var userId;
    $http.get('api/payment').then(function(response){
        userId = response.data.userId;
    });

    $scope.paymentSubmit = function(){
        $scope.payment.idUser = userId;
        $http.post('payment', $scope.payment).then(function(response){
            document.getElementById("response").innerHTML = response.data.status;
            $state.reload();
        }, function(response){
            $scope.error = response.data;
        });
    };

});


