(function () {
    'use strict';

    angular
        .module('app.incidence.create')
        .controller('CreateIncidenceController', CreateIncidenceController);

    CreateIncidenceController.inject = ['$scope', '$http', 'AlertService'];

    function CreateIncidenceController($scope, $http, AlertService) {
        var vm = this;

        vm.edificios = [];
        vm.espacios = [];
        vm.edificioSeleccionado = null;
        vm.espacioSeleccionado = null;

        $http.get("/edificios").then(
            function (response) { //success
                var objetoEdificio = response.data;

                for(var i = 0; i < objetoEdificio.length; i++){
                    vm.edificios.push(
                        {nombre: objetoEdificio[i].nombre}
                    );
                }
            },
            function (response) { //error
                AlertService.addAlert('danger','Error al obtener los edificios del sistema');
            }
        );

        $scope.$watch('vm.edificioSeleccionado', function() {
            if(vm.edificioSeleccionado !== null && vm.edificioSeleccionado !== undefined){
                $http.get("/espacios/" + vm.edificioSeleccionado.nombre).then(
                    function (response) { //success
                        var objetoEspacio = response.data;

                        for(var i = 0; i < objetoEspacio.length; i++){
                            vm.espacios.push(
                                objetoEspacio[i]
                            );
                        }
                    },
                    function (response) { //error
                        AlertService.addAlert('danger','Error al obtener los espacios del sistema');
                    }
                );
            }
        });

        vm.crearError = false;
        vm.create = create;
        vm.toggleCrear = toggleCrear;

        function toggleCrear() {
            vm.crearError = false;
        }

        function create(event) {
            event.preventDefault();

            if(vm.espacioSeleccionado === null || vm.espacioSeleccionado === undefined){
                //console.log("Selecciona ROL loco");
            } else {

                var data = {
                    nombre: vm.name,
                    descripcion: vm.description,
                    idEspacio: vm.espacioSeleccionado.informacionEspacio.idEspacio
                };

                //console.log('Creando incidencia: ' + data.nombre + ' ' + data.descripcion + ' ' + data.idEspacio);

                $http.put("/incidencias", data).then(
                    function (response) { //success
                        //console.log("Respuesta: " + response);
                        vm.crearError = false;

                        vm.name = null;
                        vm.place = null;
                        vm.description = null;
                        vm.edificioSeleccionado = null;

                        AlertService.addAlert('success', '¡La incidencia en ' + vm.espacioSeleccionado.informacionEspacio.nombreEspacio + ' ha sido registrada con éxito!');

                        vm.espacioSeleccionado = null;
                        vm.espacios= [];
                    },
                    function (response) { //error
                        vm.crearError = true;
                    }
                );
            }
        }
    }
})();