(function () {
    'use strict';

    angular
        .module('app.incidence.list')
        .controller('ListIncidenceController', ListIncidenceController);

    ListIncidenceController.inject = ['$scope', '$http', '$filter', 'NgTableParams', 'LoginService', 'AlertService'];

    function ListIncidenceController($scope, $http, $filter, NgTableParams, LoginService, AlertService) {
        var vm = this;
        var usuario = LoginService.currentLoggedUser();
        var rol = LoginService.currentRole();

        vm.usuario =usuario.email;
        vm.incidencias = [];

        if(rol === 'administrador'){
            //Obtenemos listado de incidencias
            $http.get("/incidencias").then(
                function (response) { //success
                    var objetoIncidencia = response.data;

                    for (var i = 0; i < objetoIncidencia.length; i++) {
                        var id = objetoIncidencia[i].id;
                        var name = objetoIncidencia[i].name;
                        var description = objetoIncidencia[i].description;
                        var place = objetoIncidencia[i].place;
                        var building = objetoIncidencia[i].building;
                        var status = objetoIncidencia[i].status;
                        var workerEmail = objetoIncidencia[i].workerEmail;
                        var fecha = objetoIncidencia[i].fecha;

                        vm.incidencias.push({
                            nombreIncidencia: name, lugarIncidencia: place, edificioIncidencia: building,
                            descripcionIncidencia: description, estadoIncidencia: status,
                            fechaIncidencia: fecha
                        });
                    }

                    vm.tabla = new NgTableParams({count: 5}, {dataset: vm.incidencias, counts: [5, 10, 20]});
                },
                function (response) { //error
                    AlertService.addAlert('danger', 'Error al obtener el listado de incidencias del sistema para ' +
                        'el usuario ' + usuario.email);
                }
            );
        } else if(rol === 'trabajador'){
            //Obtenemos listado de incidencias
            $http.get("/incidencias/" + usuario.email).then(
                function (response) { //success
                    var objetoIncidencia = response.data;

                    for (var i = 0; i < objetoIncidencia.length; i++) {
                        var id = objetoIncidencia[i].id;
                        var name = objetoIncidencia[i].name;
                        var description = objetoIncidencia[i].description;
                        var place = objetoIncidencia[i].place;
                        var building = objetoIncidencia[i].building;
                        var status = objetoIncidencia[i].status;
                        var workerEmail = objetoIncidencia[i].workerEmail;
                        var fecha = objetoIncidencia[i].fecha;

                        vm.incidencias.push({
                            nombreIncidencia: name, lugarIncidencia: place, edificioIncidencia: building,
                            descripcionIncidencia: description, estadoIncidencia: status,
                            fechaIncidencia: fecha
                        });
                    }

                    vm.tabla = new NgTableParams({count: 5}, {dataset: vm.incidencias, counts: [5, 10, 20]});
                },
                function (response) { //error
                    AlertService.addAlert('danger', 'Error al obtener el listado de incidencias del sistema para ' +
                        'el usuario ' + usuario.email);
                }
            );
        }
    }
})();