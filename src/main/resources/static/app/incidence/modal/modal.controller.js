(function () {
    'use strict';

    angular
        .module('app.incidence.modal')
        .controller('ModalController', ModalController);

    ModalController.inject = ['$uibModalInstance','$http','AlertService'];

    function ModalController($uibModalInstance,$http,AlertService,param) {
        //console.log("Modal incidence controller called!: " + param.ubicacion);
        var vm = this;

        vm.place = param.ubicacion;
        vm.building = param.building;
        vm.crearError = false;
        vm.cancel = cancel;
        vm.create = create;
        vm.toggleCrear = toggleCrear;

        function toggleCrear() {
            vm.crearError = false;
        }

        function cancel() {
            vm = {
                name: null,
                description: null
            };
            vm.crearError = false;
            $uibModalInstance.dismiss('cancel');
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

                    AlertService.addAlert('success', '¡La incidencia en ' + data.place + ' ' + data.building + ' ha sido registrada con éxito!');

                    $uibModalInstance.dismiss('success');
                },
                function (response) { //error
                    vm.crearError = true;
                }
            );
        }
    }
})();