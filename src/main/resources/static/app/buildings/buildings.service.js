(function() {
    'use strict';

    angular
        .module('app')
        .service('MapService', MapService);

    function MapService ($http) {

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

        this.obtenerInfo = function (nombreCapa, lat, long) {
            var url = "https://ec2-54-71-10-146.us-west-2.compute.amazonaws.com:8443/geoserver/labis/ows?service" +
                "=WFS&version=1.0.0&request=GetFeature&typeName=" + nombreCapa + "&outputFormat=application%2Fjson&CQL_FILTER=" +
                "CONTAINS(geom,%20Point(" + long + "%20" + lat + "))&propertyName=id_centro";

            return $http.get(url).then(function (response) {
                var ubicacion = response.data.features;

                if (ubicacion == undefined || ubicacion[0] == undefined) {
                    return undefined;
                } else {
                    ubicacion = ubicacion[0].properties.id_centro;
                    if (ubicacion == "RELLANO" || ubicacion == "PASILLO" || ubicacion == "BAÑOS" || ubicacion == "AULA") {
                        return undefined
                    } else {
                        return ubicacion;
                    }
                }
            }, function () {
                return undefined;
            });
        }
    }
})();
