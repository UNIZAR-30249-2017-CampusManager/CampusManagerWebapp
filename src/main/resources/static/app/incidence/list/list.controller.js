(function () {
    'use strict';

    angular
        .module('app.incidence.list')
        .controller('ListIncidenceController', ListIncidenceController);

    ListIncidenceController.inject = ['$rootScope', '$scope', '$http', '$filter', 'NgTableParams', 'LoginService', 'AlertService'];

    function ListIncidenceController($rootScope, $scope, $http, NgTableParams, LoginService, AlertService, MaintenanceModalService) {
        var vm = this;
        var usuario = LoginService.currentLoggedUser();
        LoginService.currentRole();
        var rol = $rootScope.rol;

        vm.usuario = usuario.email;
        vm.role = rol;
        vm.incidencias = [];
        vm.inciSeleccionadas = [];

        if(rol === 'administrador'){
            //Obtenemos listado de incidencias
            $http.get("/incidencias").then(
                function (response) { //success
                    var objetoIncidencia = response.data;

                    for (var i = 0; i < objetoIncidencia.length; i++) {
                        var id = objetoIncidencia[i].id;
                        var name = objetoIncidencia[i].nombre;
                        var description = objetoIncidencia[i].descripcion;
                        var place = objetoIncidencia[i].nombreEspacio;
                        var building = objetoIncidencia[i].nombreEdificio;
                        var status = objetoIncidencia[i].estado;
                        var workerEmail = objetoIncidencia[i].emailTrabajador;
                        var fecha = objetoIncidencia[i].fecha;
                        var requestId = objetoIncidencia[i].grupo;

                        if (requestId === null) {
                            requestId = id;
                        }

                        vm.incidencias.push({
                            nombreIncidencia: name, lugarIncidencia: place, edificioIncidencia: building,
                            descripcionIncidencia: description, estadoIncidencia: status,
                            fechaIncidencia: fecha, idIncidencia: id, requestId: requestId
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
                        var name = objetoIncidencia[i].nombre;
                        var description = objetoIncidencia[i].descripcion;
                        var place = objetoIncidencia[i].nombreEspacio;
                        var building = objetoIncidencia[i].nombreEdificio;
                        var status = objetoIncidencia[i].estado;
                        var workerEmail = objetoIncidencia[i].emailTrabajador;
                        var fecha = objetoIncidencia[i].fecha;
                        var requestId = objetoIncidencia[i].grupo;

                        if (requestId === null) {
                            requestId = id;
                        }

                        vm.incidencias.push({
                            nombreIncidencia: name, lugarIncidencia: place, edificioIncidencia: building,
                            descripcionIncidencia: description, estadoIncidencia: status,
                            fechaIncidencia: fecha, requestId: requestId
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

        vm.toggleSelection = function(row){
            if (row.isRowSelected) {
                vm.inciSeleccionadas.splice(vm.inciSeleccionadas.indexOf(row), 1);
                row.isRowSelected = false;
            } else {
                vm.inciSeleccionadas.push(row);
                row.isRowSelected = true;
            }
        }

        vm.crearIncidencia = function() {
            if (vm.inciSeleccionadas.length > 0) {
                MaintenanceModalService.open(vm.inciSeleccionadas);
            } else {
                AlertService.addAlert('danger', 'Error al crear solicitud de mantenimiento: seleccione al menos una incidencia');
            }
        }
    }
})();