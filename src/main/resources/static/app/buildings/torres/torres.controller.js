(function() {
    'use strict';

    angular
        .module('app.buildings.torres')
        .controller('TorresController', TorresController);

    TorresController.inject = ['$scope','leafletData','$compile','MapService','ModalService', 'AlertService'];

    function TorresController($scope,leafletData,$compile,MapService,ModalService, AlertService) {
        //console.log("Invocado controlador del Torres");
        var vm = this;
        var currentFloor = 0;

        AlertService.addPermanentAlert();

        vm.report = report;

        function report(ubicacion){
            ModalService.open(ubicacion, "Torres Quevedo");
        }

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

        $scope.$on('leafletDirectiveMap.click', function (event, args) {
            var leafEvent = args.leafletEvent;

            var latitude = leafEvent.latlng.lat;
            var longitude = leafEvent.latlng.lng;

            //Lat: latitude.toString(), Long: longitude.toString()

            $scope.markers = [];

            if (currentFloor == - 1) {
                var wmsFloor = 'wmsSotano';
            } else {
                var wmsFloor = 'wms'+currentFloor;
            }

            var ubicacion = MapService.obtenerInfo($scope.layers.baselayers[wmsFloor].layerParams.layers,
                latitude, longitude);

            ubicacion.then(function (result) {
                //Si no ha encontrado ubicacion, no le dejamos crear incidencias

                if(result == undefined){
                    $scope.markers.push({
                        lat: latitude,
                        lng: longitude,
                        message: "¡Estás aquí!",
                        focus: true,
                        draggable: false
                    });
                } else{
                    $scope.markers.push({
                        lat: latitude,
                        lng: longitude,
                        message: "<div style='text-align: center;'>" + result + ":</br> ¿Tienes alguna incidencia que reportar? </br> "
                        + "<a href=\"\" ng-click=\"vm.report('" + result + "')\">"
                        + "<span>Reportar</span>"
                        + "</a></div>",
                        getMessageScope: function() { return $scope; },
                        focus: true,
                        draggable: false
                    });
                }
            });
        });

        $scope.subirPlanta = function(){
            if(currentFloor == 3){
                //console.log("Llegado al limite superior del Torres");
            } else{
                //console.log("Subiendo planta del Torres (Planta actual: " + parseInt(currentFloor+1) + ")");
                $scope.markers = [];

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
                $scope.markers = [];

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
