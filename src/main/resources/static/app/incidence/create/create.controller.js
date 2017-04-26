(function () {
    'use strict';

    angular
        .module('app.incidence.create')
        .controller('CreateIncidenceController', CreateIncidenceController);

    CreateIncidenceController.inject = ['$scope', '$http', 'AlertService'];

    function CreateIncidenceController($scope, $http, AlertService) {
        var vm = this;

        vm.crearError = false;
        vm.create = create;
        vm.toggleCrear = toggleCrear;

        function toggleCrear() {
            vm.crearError = false;
        }

        function create(event) {
            event.preventDefault();

            var data = {
                nombre: vm.name,
                descripcion: vm.description,
                nombreEspacio: vm.place,
                planta: vm.floor,
                nombreEdificio: vm.building
            };

            $http.put("/incidencias", data).then(
                function (response) { //success
                    //console.log("Respuesta: " + response);
                    vm.crearError = false;

                    vm.name = null;
                    vm.place = null;
                    vm.description = null;
                    vm.building = null;
                    vm.floor = null;

                    AlertService.addAlert('success', '¡La incidencia en ' + data.nombreEspacio + ' ha sido registrada con éxito!')
                },
                function (response) { //error
                    vm.crearError = true;
                }
            );
        }
    }
})();