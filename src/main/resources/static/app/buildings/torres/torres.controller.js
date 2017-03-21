(function() {
    'use strict';

    angular
        .module('app.buildings.torres')
        .controller('TorresController', TorresController);

    function TorresController($scope,leafletData,$compile,MapService) {
        //console.log("Invocado controlador del Torres");
        var currentFloor = 0;

        //Actualizando titulos del html
        $scope.vista={
            nombre : 'Edificio Torres Quevedo'
        };
        $scope.edificio={
            nombre : 'Planta ' + currentFloor
        };

        //Arrays de capas
        $scope.definedLayers = {
            wmsSotano: MapService.crearCapa('Sótano', 'labis:torresS01'),
            wms0 : MapService.crearCapa('Planta 0','labis:torresP00'),
            wms1 : MapService.crearCapa('Planta 1','labis:torresP01'),
            wms2 : MapService.crearCapa('Planta 2','labis:torresP02'),
            wms3 : MapService.crearCapa('Planta 3','labis:torresP03')
        };

        angular.extend($scope, {
            torres: {
                lat: 41.683591,
                lng: -0.887376,
                zoom: 18
            },
            events: {},
            layers: {
                baselayers: {
                    wms0: $scope.definedLayers['wms0']
                }
            },
            markers: [],
            controls:{
                bajarNivel:{
                    type: 'bajarPlanta'
                },
                subirNivel:{
                    type: 'subirPlanta'
                }
            }
        });

        leafletData.getMap().then(function() {
            var element = document.getElementById('botonSubir');
            var element2 = document.getElementById('botonBajar');
            $compile(element)($scope);
            $compile(element2)($scope);
        });

        $scope.$on('leafletDirectiveMap.click', function(event, args){
            var leafEvent = args.leafletEvent;

            var latitude = leafEvent.latlng.lat;
            var longitude = leafEvent.latlng.lng;

            $scope.markers=[];
            $scope.markers.push({
                lat: latitude,
                lng: longitude,
                message: "¡Estás aquí! </br> Lat: " + latitude.toString() + "</br> Long: " + longitude.toString(),
                focus: true,
                draggable: false
            });
        });

        $scope.subirPlanta = function(){
            if(currentFloor == 3){
                //console.log("Llegado al limite superior del Torres");
            } else{
                //console.log("Subiendo planta del Torres (Planta actual: " + parseInt(currentFloor+1) + ")");

                var baselayers = $scope.layers.baselayers;

                if(currentFloor == -1){
                    delete baselayers['wmsSotano'];
                } else delete baselayers['wms' + currentFloor];

                currentFloor++;
                baselayers['wms' + currentFloor] = $scope.definedLayers['wms' + currentFloor];
                $scope.edificio={
                    nombre : 'Planta ' + currentFloor
                };
            }
        };

        $scope.bajarPlanta = function(){
            if(currentFloor == -1){
                //console.log("Llegado al limite inferior del Torres");
            } else{
                //console.log("Bajando planta del Torres (Planta actual: " + parseInt(currentFloor-1) + ")");

                var baselayers = $scope.layers.baselayers;
                delete baselayers['wms' + currentFloor];

                currentFloor--;

                if(currentFloor == -1){
                    baselayers['wmsSotano'] = $scope.definedLayers['wmsSotano'];
                    $scope.edificio = {
                        nombre: 'Sótano'
                    };
                } else{
                    baselayers['wms' + currentFloor] = $scope.definedLayers['wms' + currentFloor];
                    $scope.edificio = {
                        nombre: 'Planta ' + currentFloor
                    };
                }
            }
        };
    }
})();
