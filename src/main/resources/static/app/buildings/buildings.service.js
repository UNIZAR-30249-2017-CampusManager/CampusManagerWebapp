(function() {
    'use strict';

    angular
        .module('app')
        .service('MapService', MapService);

    function MapService () {

        this.crearCapa = function (nombreLeyenda,nombreCapa){
            return {
                name: nombreLeyenda,
                type: 'wms',
                visible: true,
                url: 'http://ec2-54-70-192-195.us-west-2.compute.amazonaws.com:8080/geoserver/labis/wms',
                tiled: true,
                layerParams: {
                    layers: nombreCapa,
                    format: 'image/png',
                    transparent: true
                },
                layerOptions: {
                    attribution: "",
                    minZoom: 16,
                    maxZoom: 23
                }
            };
        }
    }
})();
