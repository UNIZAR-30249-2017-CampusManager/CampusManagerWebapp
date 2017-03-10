(function() {
    'use strict';

    angular
        .module('app.buildings.general')
        .controller('GeneralController', GeneralController);

    function GeneralController($scope) {
        console.log("GENERAL CONTROLLER CALLED!");
        var vm = this;
        vm.home = {
            title: 'Vista general',
            subtitle: 'Campus Escuela de Ingeniería y Arquitectura'
        };
        angular.extend($scope, {
            cps: {
                lat: 41.683499,
                lng: -0.886272,
                zoom: 18
            },
            kappaIcon: {
                iconUrl: 'http://res.cloudinary.com/urbandictionary/image/upload/a_exif,c_fit,h_200,w_200/v1395991705/gjn81wvxqsq6yzcwubok.png',
                iconSize:     [38, 50], // size of the icon
                iconAnchor:   [22, 50], // point of the icon which will correspond to marker's location
                popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
            },
            events: {},
            markers: {}
        });

        $scope.addStaticMarkers = function() {
            $scope.markers = new Array();

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
                message: "EY",
                focus: true,
                draggable: false,
                icon: $scope.kappaIcon
            });
        });
    }
})();
