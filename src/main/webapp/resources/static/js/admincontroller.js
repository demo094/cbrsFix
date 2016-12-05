routerApp.controller('adminPanelController', function($scope, $http, $window, $cookies){
    if(mainScope.isLoggedIn){
        mainScope.isLoggedIn = true;
    }
});

routerApp.controller('bikeAdminController', function($scope, $http, $window, $state){
    $scope.header = "Bikes currently in the database.";

    $http.get('api/adminbikelist').then(function(response){
        $scope.bikeTOs = response.data;
        mainScope.isLoggedIn = true;
    }, function(response){
        $scope.error = response.data;
    });

    $scope.deleteBike = function(id){
        $http.delete('api/bike/' + id).then(function(response){
            $scope.bikeDeleted = response.data;
            $state.reload();
        }, function(response){
            $scope.error = response.data;
        });
    }

    $scope.addBike = function(){
        $http.put('api/bike').then(function(response){
            $state.reload();
        }, function(response){
            $scope.error = response.data;
        });
    }

});

routerApp.controller('editBikeController', function($scope, $http, $stateParams, $state){
    $scope.header = "Edit bike panel";
    var bikeEditionData;
    $http.get('api/bike/' + $stateParams.id).then(function(response){
        mainScope.isLoggedIn = true;
        bikeEditionData = response.data;
        $scope.editBikeTO = bikeEditionData;
        $scope.editbike = {id: bikeEditionData.slotTO.bikeId, type: '', slotId: bikeEditionData.slotTO.slotId};
    }, function(response){
        $scope.editBikeError = response.data;
    });

    $scope.saveChanges = function(){
        if($scope.editbike.type == null){
            $scope.editbike.type = 'ordinary';
        }

        if($scope.editbike.slotId == null){
            $scope.editbike.slotId = bikeEditionData.slotTO.slotId;
        }

        $scope.editbike.slotId = parseInt($scope.editbike.slotId);
        $scope.editbike.oldSlotId = bikeEditionData.slotTO.slotId;
        $http.post('api/bike', $scope.editbike).then(function(response){
            $state.reload();
        }, function(response){
            $scope.editBikeError = response.data;
        });
    };
});

routerApp.controller('stationAdminController', function($scope, $http, $state){
    $scope.header = "Currently operating stations.";
    $scope.addStationHeader = "Add station panel";

    $http.get('api/adminstationlist').then(function(response){
            mainScope.isLoggedIn = true;
            $scope.stationTOs = response.data;
        }, function(response){
            $scope.error = response.data;
        });

    $scope.addStation = function(){
        $http.put('api/station', $scope.addstation).then(function(response){
                    $scope.serverResponse = response.status;
                    $state.reload();
                }, function(response){
                    if(response.data.fieldErrors != null){
                        $scope.fieldErrors = response.data.fieldErrors;
                    }
                    $scope.addStationError = response.data;
                });
    }

    $scope.deleteStation = function(id){
        $http.delete('api/station/' + id).then(function(response){
            $scope.serverResponse = response.status;
            $state.reload();
        }, function(response){
            $scope.error = response.data;
        });
    }
});

routerApp.controller('editStationController', function($scope, $http, $stateParams, $state){
    $scope.header = "Edit station panel";
    var stationTO;
    $http.get('api/adminstation/' + $stateParams.id).then(function(response){
            mainScope.isLoggedIn = true;
            stationTO = response.data;
            $scope.stationTO = stationTO;
            $scope.editstation = {name: stationTO.stationName, address: stationTO.stationAddress,
                                    city: stationTO.stationCity, latitude: stationTO.latitude, longitude: stationTO.longitude};
            }, function(response){
                $scope.editStationError = response.data;
    });
    $scope.saveChanges = function(){
    $scope.editstation.id = $stateParams.id;
    if($scope.editstation.name == null){
        $scope.editstation.name = $scope.stationTO.stationName;
    }
    if($scope.editstation.address == null){
        $scope.editstation.address = $scope.stationTO.stationAddress;
    }
    if($scope.editstation.city == null){
        $scope.editstation.city = $scope.stationTO.stationCity;
    }
    if($scope.editstation.latitude == null){
        $scope.editstation.latitude = $scope.stationTO.latitude;
    }
    if($scope.editstation.longitude == null){
        $scope.editstation.longitude = $scope.stationTO.longitude;
    }

        $http.post('api/station', $scope.editstation).then(function(response){
            $scope.serverResponse = response.status;
            $state.reload();
        }, function(response){
            if(response.data.fieldErrors != null){
                $scope.fieldErrors = response.data.fieldErrors;
            }
            $scope.editStationError = response.data;
        });
    }

    $scope.addSlot = function(){
        $http.put('api/slot/' + $scope.stationTO.stationId).then(function(response){
            $scope.serverResponse = response.data;
            $state.reload();
        }, function(response){
            $scope.editStationError = response.data;
        });
    }

    $scope.deleteSlot = function(slotId){
            $http.delete('api/station/' + $stateParams.id + '/slot/' + slotId).then(function(response){
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
        mainScope.isLoggedIn = true;
        $scope.priceIntervalTOs = response.data;
    }, function(response){
        $scope.error = response.data;
    });

    $scope.addPriceInterval = function(){
        $http.put('api/priceinterval', $scope.addPriceInt).then(function(response){
            $scope.serverResponse = response.status;
            $state.reload();

        }, function(response){
            $scope.addPriceError = response.data;
        });
    }

    $scope.deletePriceInterval = function(id){
        $http.delete('api/priceinterval/' + id).then(function(response){
            $scope.serverResponse = response.status;
            $state.reload();
        }, function(response){
            $scope.error = response.data;
        });
    }
});

routerApp.controller('editPriceIntController', function($scope, $http, $state, $stateParams){
    var priceIntervalTO;
    $scope.editPriceIntervalHeader = "Price interval edit panel";
    $http.get('api/editpriceinterval/' + $stateParams.id).then(function(response){
        priceIntervalTO = response.data;
        $scope.editPriceInt = {end: priceIntervalTO.end, price: priceIntervalTO.price};
        $scope.priceIntervalTO = priceIntervalTO;
    }, function(response){
            $scope.updatePriceError = response.data;
    });

    $scope.updatePriceInterval = function(){
        if($scope.editPriceInt.end == null){
            $scope.editPriceInt.end = $scope.priceIntervalTO.end;
        }
        if($scope.editPriceInt.price == null){
            $scope.editPriceInt.price = $scope.priceIntervalTO.price;
        }
        $scope.editPriceInt.id = $stateParams.id;
        $http.post('api/priceInterval', $scope.editPriceInt).then(function(response){
            $scope.serverResponse = response.status;
            $state.reload();
        }, function(response){
            if(response.data.fieldErrors != null){
                $scope.fieldErrors = response.data.fieldErrors;
            }else {
                $scope.updatePriceError = response.data;
            }
        });
    };
});
