(function () {
    'use strict';

    angular
        .module('app.incidence.list')
        .controller('ListIncidenceController', ListIncidenceController);

    ListIncidenceController.inject = ['$scope', '$http', '$filter', 'NgTableParams', 'LoginService', 'AlertService'];

    function ListIncidenceController($scope, $http, NgTableParams, LoginService, AlertService, MaintenanceModalService) {
        var vm = this;
        var usuario = LoginService.currentLoggedUser();

        vm.usuario = usuario.email;
        vm.role = usuario.role;
        vm.incidencias = [];
        $scope.inciSeleccionadas = [];

        if(usuario.role == 'ADMIN'){
            //Obtenemos listado de incidencias
            $http.get("/api/incidencia").then(
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
                            fechaIncidencia: fecha, idIncidencia: id
                        });
                    }

                    vm.tabla = new NgTableParams({count: 5}, {dataset: vm.incidencias, counts: [5, 10, 20]});
                },
                function (response) { //error
                    AlertService.addAlert('danger', 'Error al obtener el listado de incidencias del sistema para ' +
                        'el usuario ' + usuario.email);
                }
            );
        } else if(usuario.role == 'WORKER'){
            //Obtenemos listado de incidencias
            $http.get("/api/incidencia/worker/" + usuario.email).then(
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

        MaintenanceModalService.incidencias = vm.incidencias;

        $scope.toggleSelection = function(row){
            if (row.isRowSelected) {
                $scope.inciSeleccionadas.splice($scope.inciSeleccionadas.indexOf(row), 1);
                row.isRowSelected = false;
            } else {
                $scope.inciSeleccionadas.push(row);
                row.isRowSelected = true;
            }
        }

        $scope.crearIncidencia = function() {
            if ($scope.inciSeleccionadas.length > 0) {
                MaintenanceModalService.open($scope.inciSeleccionadas);
            } else {
                AlertService.addAlert('danger', 'Error al crear solicitud de mantenimiento: seleccione al menos una incidencia');
            }
        }

        $scope.$watch(function (){
           return MaintenanceModalService.incidencias;
        }, function (value) {
            vm.incidencias = value;
        });
    }
})();