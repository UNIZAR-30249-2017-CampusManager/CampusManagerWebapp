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
                url: 'https://ec2-54-71-10-146.us-west-2.compute.amazonaws.com:8443/geoserver/labis/wms',
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
