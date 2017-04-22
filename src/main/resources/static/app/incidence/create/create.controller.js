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
                name: vm.name,
                place: vm.place,
                description: vm.description,
                building: vm.building
            };

            $http.post("/api/incidencia", data).then(
                function (response) { //success
                    //console.log("Respuesta: " + response);
                    vm.crearError = false;

                    vm.name = null;
                    vm.place = null;
                    vm.description = null;
                    vm.building = null;

                    AlertService.addAlert('success', '¡La incidencia en ' + data.place + ' ha sido registrada con éxito!')
                },
                function (response) { //error
                    vm.crearError = true;
                }
            );
        }
    }
})();