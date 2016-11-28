routerApp.controller('adminPanelController', function($scope, $http, $window, $cookies){
    $scope.logout = function(){
        $cookies.remove('accessToken');
        $window.location.href = 'index';
    }
});

routerApp.controller('bikeAdminController', function($scope, $http, $window, $state){
    $scope.header = "Bikes currently in the database.";

    $http.get('api/adminbikelist').then(function(response){
        $scope.bikeTOs = response.data;
    }, function(response){
        $scope.error = response.data;
    });

    $scope.deleteBike = function(id){
        $http.delete('api/deletebike/' + id).then(function(response){
            $scope.bikeDeleted = response.data;
            $state.reload();
        }, function(response){
            $scope.error = response.data;
        });
    }

    $scope.addBike = function(){
        $http.put('api/addbike').then(function(response){
            $state.reload();
        }, function(response){
            $scope.error = response.data;
        });
    }

});

routerApp.controller('editBikeController', function($scope, $http, $stateParams, $state){
    $scope.header = "Edit bike panel";
    var id = $stateParams.id;
    var bikeEditionData;
    $http.get('api/bike/' + id).then(function(response){
        bikeEditionData = response.data;
        $scope.editBikeTO = bikeEditionData;
    }, function(response){
        $scope.editBikeError = response.data;
    });
    $scope.saveChanges = function(){
        $scope.editbike.id = bikeEditionData.slotTO.bikeId;
        $http.post('api/updatebike', $scope.editbike).then(function(response){
            $scope.serverResponse = response.status;
            $state.reload();
        }, function(response){
            $scope.editBikeError = response.data;
        });
    }
});

routerApp.controller('stationAdminController', function($scope, $http, $state){
    $scope.header = "Currently operating stations.";
    $scope.addStationHeader = "Add station panel";

    $http.get('api/adminstationlist').then(function(response){
            $scope.stationTOs = response.data;
        }, function(response){
            $scope.error = response.data;
        });

    $scope.addStation = function(){
        $http.put('api/addstation', $scope.addstation).then(function(response){
                    $scope.serverResponse = response.status;
                    $state.reload();
                }, function(response){
                    $scope.addStationError = response.data;
                });
    }

    $scope.deleteStation = function(id){
        $http.delete('api/deletestation/' + id).then(function(response){
            $scope.serverResponse = response.status;
            $state.reload();
        }, function(response){
            $scope.error = response.data;
        });
    }
});

routerApp.controller('editStationController', function($scope, $http, $stateParams, $state){
    $scope.header = "Edit station panel";
    $http.get('api/adminstation/' + $stateParams.id).then(function(response){
            $scope.stationTO = response.data;
            }, function(response){
                $scope.editStationError = response.data;
    });
    $scope.editstation = {};
    $scope.saveChanges = function(){
    $scope.editstation.id = $stateParams.id;
    if($scope.editstation.name == null){
        $scope.editstation.name = $scope.stationTO.stationName;
    }
    if($scope.editstation.type == null){
        $scope.editstation.type = $scope.stationTO.stationType;
    }
    if($scope.editstation.address == null){
        $scope.editstation.address = $scope.stationTO.stationAddress;
    }
    if($scope.editstation.city == null){
        $scope.editstation.city = $scope.stationTO.stationCity;
    }

        $http.post('api/updatestation', $scope.editstation).then(function(response){
            $scope.serverResponse = response.status;
            $state.reload();
        }, function(response){
            $scope.editStationError = response.data;
        });
    }

    $scope.addSlot = function(){
        $http.put('api/addslot/' + $scope.stationTO.stationId).then(function(response){
            $scope.serverResponse = response.data;
            $state.reload();
        }, function(response){
            $scope.editStationError = response.data;
        });
    }
});

routerApp.controller('priceIntController', function($scope, $http, $state, $stateParams){
    $scope.header = "Price interval management";
    $scope.addStationHeader = "Add price interval";
    $http.get('api/adminpriceintervals').then(function(response){
        $scope.priceIntervalTOs = response.data;
    }, function(response){
        $scope.error = response.data;
    });

    $scope.addPriceInterval = function(){
        $http.put('api/addpriceinterval', $scope.addPriceInt).then(function(response){
            $scope.serverResponse = response.status;
            $state.reload();

        }, function(response){
            $scope.addPriceError = response.data;
        });
    }

    $scope.deletePriceInterval = function(id){
        $http.delete('api/deletepriceinterval/' + id).then(function(response){
            $scope.serverResponse = response.status;
            $state.reload();
        }, function(response){
            $scope.error = response.data;
        });
    }
});

routerApp.controller('editPriceIntController', function($scope, $http, $state, $stateParams){
    $scope.editPriceIntervalHeader = "Price interval edit panel";
    $http.get('api/editpriceinterval/' + $stateParams.id).then(function(response){
        $scope.priceIntervalTO = response.data;
    }, function(response){
        $scope.updatePriceError = response.data;
    });
    $scope.editPriceInt = {};
    $scope.updatePriceInterval = function(){
        if($scope.editPriceInt.end == null){
            $scope.editPriceInt.end = $scope.priceIntervalTO.end;
        }
        if($scope.editPriceInt.price == null){
            $scope.editPriceInt.price = $scope.priceIntervalTO.price;
        }
        $scope.editPriceInt.intervalId = $stateParams.id;
        $http.post('api/updatePriceInterval', $scope.editPriceInt).then(function(response){
            $scope.serverResponse = response.status;
            $state.reload();
        }, function(response){
            $scope.updatePriceError = response.data;
        });
    };
});
