var user;
var main;

routerApp.controller('userpanelController', function($scope, $http, $state, $interval, $cookies){
    $scope.headingTitle = "User panel";
    var isBeingRented;

    $http.get('api/userpanel')
        .then(function(response){
            $scope.userPanelTO = response.data;
            $scope.serverTimeMillis = response.data.serverTime;
        }, function(response){
            $scope.error = response.data;
        });

    var updateInterval = $interval(function(){
    $http.get('api/rent/status').then(function(response){
        isBeingRented = response.data.beingRented;
        $scope.isBeingRented = isBeingRented;
        $scope.serverTimeMillis = response.data.rentalBeginTime;
        if(!isBeingRented){
            $interval.cancel(updateInterval);
            $scope.userPanelTO.bikeId = null;
            $scope.serverTimeMillis = null;
            }
        }, function(response){
            $interval.cancel(updateInterval);
            $scope.userPanelTO.bikeId = null;
            $scope.serverTimeMillis = null;
        });
    }, 5000);

});

routerApp.controller('logoutController', function($scope, $state, $cookies){
    $scope.logout = function(){
            $cookies.remove('accessToken');
            $state.go('main');
        };
});

routerApp.controller('mainPageController', function($scope, $http, $cookies, $state){
    main = $state;
});

var modalInstance;

routerApp.controller('stationsController', function($scope, $http, $state, $uibModal){
       var stations = [];
       $scope.stationMarkers = [];
       $scope.headingTitle = "Available stations";

       $scope.map =
//       null;
       new google.maps.Map(document.getElementById("stationMap"), {
           zoom: 12,
           center: new google.maps.LatLng(51.2454209,22.5727518),
           mapTypeId: 'terrain'
       });

       if($scope.map != null){
       var addStationMarker = function(station){
           var marker = new google.maps.Marker({
               map: $scope.map,
               position: new google.maps.LatLng(station.latitude, station.longitude),
               title: station.stationName,
               icon: 'resources/static/img/1479327321_biker.png'
           });

           marker.content = station.stationId;
           google.maps.event.addListener(marker, 'click', function(){
               modalInstance = $uibModal.open({
                   ariaLabelledBy: 'modal-title',
                   ariaDescribedBy: 'modal-body',
                   templateUrl: 'resources/static/views/stationModal.html',
                   controller: 'modalSlotController',
                   resolve: {
                       data: station.stationId
                   }
               });
//               if($state.current.name == 'userpanel.stations'){
//                   $state.go('.station', {id: marker.content});
//               } else {
//                   $state.go('^.station', {id: marker.content});
//               }
           });

           google.maps.event.addDomListener(window, "resize", function() {
               var center = $scope.map.getCenter();
               google.maps.event.trigger($scope.map, "resize");
               $scope.map.setCenter(center);
           });

           $scope.stationMarkers.push(marker);
        };

       $scope.openStationWindow = function(e, selectedMarker){
            e.preventDefault();
            google.maps.event.trigger(selectedMarker, 'click');
       }

       }

        $http.get('api/stations').then(function(response){
               stations = response.data;
               $scope.stationTOs = stations;
               if($scope.map != null){
               for(var i = 0; i < stations.length; i++){
                   addStationMarker(stations[i]);
               }
             }
        }, function(response){
             $scope.error = response.data;
        });
   });

routerApp.controller('modalSlotController', function($scope, $http, data){
    var id = data;
    $scope.headingTitle = "List of slots on the station";
    $scope.close = function(){
        modalInstance.dismiss('cancel');
    };

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
                modalInstance.close();
                $state.reload();
            }, function(response){
                $scope.error = response.data;
            });
       };

       $scope.returnBike = function(){
                   var url = 'api/station/slot/'+ $scope.item.slotId +'/return';
                   $http.post(url)
                   .then(function(response){
                        document.getElementById("server_response").innerHTML = response.data.rentStatus;
                        modalInstance.close();
                        $state.reload();
                       }, function(response){
                        $scope.error = response.data;
                   });
       };
   });

var loginController = routerApp.controller('loginController', function($scope, $http, $state, $location){
    $scope.login = function(){
        $http.post('api/login', $scope.credentials).then(function(response){
                $state.go('main.userpanel');
                main.reload();
//                $state.go('main.userpanel');
//                $location.path('/userpanel');
        }, function(response){
            $scope.loginError = response.data;
        });
    };
});


routerApp.controller('resetPassController', function($scope, $http, $window){
    $scope.sendPasswordMail = function(){
            $http.post('api/resetPassEmail', $scope.form).then(function(response){
                document.getElementById("response").innerHTML = response.data.message;
                $window.location.href = "index";
            }, function(response){
                $scope.error = response.data;
            });
    };
});

routerApp.controller('resetPassPageController', function($scope, $http, $window, $stateParams){
    $scope.resetPassword = function(){
        $http.post('api/resetPassData/' + $stateParams.token, $scope.newPassword).then(function(response){
            $scope.resetResponse = response.data;
        }, function(response){
            $scope.resetError = response.data;
        });
    }
});

routerApp.controller('registrationController', function($scope, $http, $window){
    $scope.registration = function(){
            $http.post('api/register', $scope.register).then(function(response){
                document.getElementById("response").innerHTML = response.data.message;

            }, function(response){
                $scope.error = response.data;
            });
    };
});

routerApp.controller('registrationConfirmController', function($scope, $http, $stateParams){
    $http.get('api/registrationConfirm/' + $stateParams.token)
    .then(function(response){
        $scope.confirmed = response.data.message;
    }, function(response){
        $scope.error = response.data;
    });
});

routerApp.controller('resendActTokenController', function($scope, $http, $window, $timeout){
       $scope.resend = function(){
               $http.post('api/resendActToken', $scope.resendtoken).then(function(response){
                   document.getElementById("response").innerHTML = response.data.message;
                   $timeout(function(){
                       $window.location.href = "index";
                   }, 5000);
               }, function(response){
                   $scope.error = response.data;
               });
       };
   });

routerApp.controller('paymentController', function($scope, $http, $state, $timeout){
    var userId;
    $http.get('api/payment').then(function(response){
        userId = response.data.userId;
    });

    $scope.paymentSubmit = function(){
        if($scope.payment == null){
            $scope.paymentError = "You have to fill the form!";
        }
        $scope.payment.idUser = userId;
        $http.post('api/paymentData', $scope.payment).then(function(response){
            $scope.paymentResponse = response.data.message;
            $scope.paymentForm.$setPristine();
            $scope.payment = {};
            $timeout(function(){
                $state.reload();
            }, 3000);
        }, function(response){
            if(response.data.status != null){
                $scope.paymentError = response.data.message;
            }
            $scope.fieldErrors = response.data.fieldErrors;
        });
    };

});


