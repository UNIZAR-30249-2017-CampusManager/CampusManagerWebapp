(function () {
    'use strict';

    angular
        .module('app.buildings.ada')
        .controller('AdaController', AdaController);

    AdaController.inject=['$scope','leafletData','$compile','MapService','ModalService','AlertService','$http'];

    function AdaController($scope, leafletData, $compile, MapService, ModalService, AlertService, $http) {
        //console.log("Invocado controlador del Ada");
        var vm = this;
        var currentFloor = 0;

        AlertService.addPermanentAlert();

        vm.report = report;

        function report(){
            ModalService.open(vm.datos);
        }

        //Actualizando titulos del html
        $scope.vista = {
            nombre: 'Edificio Ada Byron'
        };
        $scope.edificio = {
            nombre: 'Planta ' + currentFloor
        };

        $scope.infoEdificio = {
                horaApertura: '',
                horaCierre: '',
                mesesCerrado: '',
                menuCafeteria: ''
        }

        $scope.getInfo = function (){

                    $http.get("/edificios/Ada Byron").then(
                                function (response) { //success
                                    var objetoEdificio = response.data;

                                    $scope.infoEdificio.horaApertura = objetoEdificio.horaApertura;
                                    $scope.infoEdificio.horaCierre = objetoEdificio.horaCierre;

                                    var array = objetoEdificio.mesesCerrado;
                                    var texto = '';
                                    var len = array.length;

                                    for (var i = 0;i < len; i++){
                                        if(i < len - 2){
                                            texto += array[i] + ', ';
                                        }else if(i < len - 1){
                                            texto += array[i] +  ' y ';
                                        }else{
                                            texto += array[i];
                                        }
                                    }
                                    $scope.infoEdificio.mesesCerrado = texto;

                                    $scope.infoEdificio.menuCafeteria = objetoEdificio.menuCafeteria;

                                },
                                function (response) { //error
                                    AlertService.addAlert('danger', 'Error al obtener la información del edificio;');
                                }
                            );
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

            //Lat: latitude.toString(), Long: longitude.toString()

            $scope.markers = [];

            if (currentFloor === - 1) {
                var wmsFloor = 'wmsSotano';
            } else {
                var wmsFloor = 'wms'+currentFloor;
            }

            var ubicacion = MapService.obtenerInfo($scope.layers.baselayers[wmsFloor].layerParams.layers,
                latitude, longitude, 'Ada Byron', currentFloor);

            ubicacion.then(function (result) {
                //Si no ha encontrado ubicacion, no le dejamos crear incidencias

                if(result === undefined){
                    $scope.markers.push({
                        lat: latitude,
                        lng: longitude,
                        message: "¡Estás aquí!",
                        focus: true,
                        draggable: false
                    });
                } else{

                    vm.datos = {
                        nombreEdificio: 'Ada Byron',
                        idEspacio: result.idEspacio,
                        nombre: result.nombre
                    };

                    $scope.markers.push({
                        lat: latitude,
                        lng: longitude,
                        message: "<div style='text-align: center;'>" + result.nombre + ":</br> ¿Tienes alguna incidencia que reportar? </br> "
                        + "<a href=\"\" ng-click=\"vm.report()\">"
                        + "<span>Reportar</span>"
                        + "</a></div>",
                        getMessageScope: function() { return $scope; },
                        focus: true,
                        draggable: false
                    });
                }
            });
        });

        $scope.subirPlanta = function () {
            if (currentFloor == 4) {
                //console.log("Llegado al limite superior del Ada Byron");
            } else {
                //console.log("Subiendo planta del Ada Byron (Planta actual: " + parseInt(currentFloor+1) + ")");
                $scope.markers = [];

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
