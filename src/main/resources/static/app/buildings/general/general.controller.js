(function() {
    'use strict';

    angular
        .module('app.buildings.general')
        .controller('GeneralController', GeneralController);

    GeneralController.$inject=['$scope','MapService','AlertService'];

    function GeneralController($scope,MapService, AlertService) {
        //console.log("Invocado controlador general");

        AlertService.addPermanentAlert();

        $scope.vista={
            nombre : 'Vista general'
        };
        $scope.edificio={
            nombre : 'Campus Escuela de Ingeniería y Arquitectura'
        };
        $scope.basicLayer = {
            xyz: {
                name: 'OpenStreetMap',
                url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
                type: 'xyz',
                layerOptions: {
                    attribution: "",
                    minZoom: 16,
                    maxZoom: 19
                }
            }
        };
        angular.extend($scope, {
            cps: {
                lat: 41.683499,
                lng: -0.886272,
                zoom: 17
            },
            events: {},
            layers: {
                baselayers: {
                    xyz: $scope.basicLayer['xyz']
                },
                overlays: {
                    eina: MapService.crearCapa('EINA','labis:todosP00')
                }
            },
            markers: {}
        });

        $scope.addStaticMarkers = function() {
            $scope.markers = [];

            $scope.markers.push({
                lat: 41.683679,
                lng: -0.888513,
                message: "Edificio Ada Byron",
                focus: false,
                draggable: false
            });
            $scope.markers.push({
                lat: 41.683591,
                lng: -0.887376,
                message: "Edificio Torres Quevedo",
                focus: false,
                draggable: false
            });
            $scope.markers.push({
                lat: 41.683422,
                lng: -0.884468,
                message: "Edificio Betancourt",
                focus: false,
                draggable: false
            });
        };

        $scope.addStaticMarkers();

        $scope.$on('leafletDirectiveMap.click', function(event, args){
            $scope.addStaticMarkers();
            var leafEvent = args.leafletEvent;

            $scope.markers.push({
                lat: leafEvent.latlng.lat,
                lng: leafEvent.latlng.lng,
                message: "¡Estás aquí!",
                focus: true,
                draggable: false
            });
        });
    }
})();
