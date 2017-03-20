(function() {
    'use strict';

    angular
        .module('app.buildings.general')
        .controller('GeneralController', GeneralController);

    function GeneralController($scope,leafletData,MapService) {
        //console.log("Invocado controlador general");
        $scope.vista={
            nombre : 'Vista general'
        };
        $scope.edificio={
            nombre : 'Campus Escuela de Ingeniería y Arquitectura'
        };
        $scope.basicLayer = {
            xyz: {
                name: 'OpenStreetMap (XYZ)',
                url: 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
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
            markerIcon: {
                iconUrl: 'http://www.clker.com/cliparts/b/7/6/5/1308001441853739087google%20maps%20pin.svg',
                iconSize:     [38, 50], // size of the icon
                iconAnchor:   [15, 50], // point of the icon which will correspond to marker's location
                popupAnchor:  [0, -40] // point from which the popup should open relative to the iconAnchor
            },
            events: {},
            layers: {
                baselayers: {
                    xyz: $scope.basicLayer['xyz']
                },
                overlays: {
                    eina: MapService.crearCapa('EINA','labis:todos')
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
                draggable: false,
                icon: $scope.markerIcon
            });
        });

        // $scope.$on('$destroy', function () {
        //     leafletData.getMap().then(function(map) {
        //         map.remove();
        //     });
        // });
    }
})();
