(function () {
    'use strict';

    angular
        .module('app.buildings.ada')
        .controller('AdaController', AdaController);

    function AdaController($scope, leafletData, $compile, MapService) {
        //console.log("Invocado controlador del Ada");
        var currentFloor = 0;

        //Actualizando titulos del html
        $scope.vista = {
            nombre: 'Edificio Ada Byron'
        };
        $scope.edificio = {
            nombre: 'Planta ' + currentFloor
        };

        //Arrays de capas
        $scope.definedLayers = {
            wmsSotano: MapService.crearCapa('Sótano', 'labis:adaS01'),
            wms0: MapService.crearCapa('Planta 0', 'labis:adaP00'),
            wms1: MapService.crearCapa('Planta 1', 'labis:adaP01'),
            wms2: MapService.crearCapa('Planta 2', 'labis:adaP02'),
            wms3: MapService.crearCapa('Planta 3', 'labis:adaP03'),
            wms4: MapService.crearCapa('Planta 4', 'labis:adaP04')
        };

        angular.extend($scope, {
            ada: {
                lat: 41.683579,
                lng: -0.888713,
                zoom: 19
            },
            events: {},
            layers: {
                baselayers: {
                    wms0: $scope.definedLayers['wms0']
                }
            },
            markers: [],
            controls: {
                bajarNivel: {
                    type: 'bajarPlanta'
                },
                subirNivel: {
                    type: 'subirPlanta'
                }
            }
        });

        leafletData.getMap().then(function () {
            var element = document.getElementById('botonSubir');
            var element2 = document.getElementById('botonBajar');
            $compile(element)($scope);
            $compile(element2)($scope);
        });

        $scope.$on('leafletDirectiveMap.click', function (event, args) {
            var leafEvent = args.leafletEvent;

            var latitude = leafEvent.latlng.lat;
            var longitude = leafEvent.latlng.lng;

            $scope.markers = [];
            $scope.markers.push({
                lat: latitude,
                lng: longitude,
                message: "¡Estás aquí! </br> Lat: " + latitude.toString() + "</br> Long: " + longitude.toString(),
                focus: true,
                draggable: false
            });
        });

        $scope.subirPlanta = function () {
            if (currentFloor == 4) {
                //console.log("Llegado al limite superior del Ada Byron");
            } else {
                //console.log("Subiendo planta del Ada Byron (Planta actual: " + parseInt(currentFloor+1) + ")");

                var baselayers = $scope.layers.baselayers;

                if(currentFloor == -1){
                    delete baselayers['wmsSotano'];
                } else delete baselayers['wms' + currentFloor];

                currentFloor++;
                baselayers['wms' + currentFloor] = $scope.definedLayers['wms' + currentFloor];
                $scope.edificio = {
                    nombre: 'Planta ' + currentFloor
                };
            }
        };

        $scope.bajarPlanta = function () {
            if (currentFloor == -1) {
                //console.log("Llegado al limite inferior del Ada Byron");
            } else {
                //console.log("Bajando planta del Ada Byron (Planta actual: " + parseInt(currentFloor-1) + ")");

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
