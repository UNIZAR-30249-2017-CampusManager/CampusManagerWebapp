(function () {
    'use strict';

    angular
        .module('app.bookable.create')
        .controller('CreateBookableController', CreateBookableController);

    CreateBookableController.inject = ['$scope', '$http', 'AlertService'];

    function CreateBookableController($scope, $http, AlertService) {
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
                vm.espacios = [];
                vm.espacioSeleccionado = null;
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

            } else {

                $http.put("/espacios/" +  vm.espacioSeleccionado.id).then(
                    function (response) { //success
                        AlertService.addAlert('success','El espacio se ha marcado como reservable');
                    },
                    function (response) { //error
                        AlertService.addAlert('danger', 'El espacio no ha podido marcarse como reservable');
                    }
                );
            }
        }

    }
})();