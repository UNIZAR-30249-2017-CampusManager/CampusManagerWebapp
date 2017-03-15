(function() {
    'use strict';

    angular
        .module('app.buildings.ada')
        .controller('AdaController', AdaController);

    function AdaController($scope) {
        console.log("ADA CONTROLLER CALLED!");
        var vm = this;
        vm.home = {
            title: 'Vista general',
            subtitle: 'Campus Escuela de Ingeniería y Arquitectura'
        };
        angular.extend($scope, {
            ada: {
                lat: 41.683679,
                lng: -0.888513,
                zoom: 10
            },
            kappaIcon: {
                iconUrl: 'http://res.cloudinary.com/urbandictionary/image/upload/a_exif,c_fit,h_200,w_200/v1395991705/gjn81wvxqsq6yzcwubok.png',
                iconSize:     [38, 50], // size of the icon
                iconAnchor:   [22, 50], // point of the icon which will correspond to marker's location
                popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
            },
            events: {},
            layers: {
                baselayers: {
                    wms: {
                        name: 'Geoserver',
                        type: 'wms',
                        visible: true,
                        url: 'http://88.1.118.1:8080/geoserver/labis/wms',
                        layerParams: {
                            layers: 'labis:adaP00',
                            format: 'image/png',
                            transparent: true
                        },
                        layerOptions: {
                            attribution: "",
                            minZoom: 18,
                            maxZoom: 23
                        }
                    }
                }
            },
            markers: {}
        });

        $scope.$on('leafletDirectiveMap.click', function(event, args){
            $scope.addStaticMarkers();
            var leafEvent = args.leafletEvent;

            $scope.markers.push({
                lat: leafEvent.latlng.lat,
                lng: leafEvent.latlng.lng,
                message: "OY",
                focus: true,
                draggable: false,
                icon: $scope.kappaIcon
            });
        });
    }
})();
