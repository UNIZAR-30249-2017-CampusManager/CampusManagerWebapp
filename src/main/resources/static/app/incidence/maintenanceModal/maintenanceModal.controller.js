(function () {
    'use strict';

    angular
        .module('app.incidence.maintenanceModal')
        .controller('MaintenanceModalController', MaintenanceModalController);

    MaintenanceModalController.inject = ['$state', '$uibModalInstance','$http','AlertService'];

    function MaintenanceModalController($state, $uibModalInstance, $http, AlertService, param, MaintenanceModalService) {
        var vm = this;

        vm.crearError = false;
        vm.cancel = cancel;
        vm.create = create;
        vm.toggleCrear = toggleCrear;

        function toggleCrear() {
            vm.crearError = false;
        }

        function cancel() {
            vm = {
                emailTrabajador: null
            };
            vm.crearError = false;
            $uibModalInstance.dismiss('cancel');
        }

        function create(event) {
            event.preventDefault();

            var idsIncidencias = [];
            for (var i = 0; i < param.idArray.length; i++) {
                idsIncidencias.push(param.idArray[i].idIncidencia);
            }

            var data = {
                emailTrabajador: vm.workerEmail,
                idsIncidencias: idsIncidencias
            };

            $http.post("/incidencias", data).then(function (response) {
                vm.crearError = false;

                vm.workerEmail = null;

                $uibModalInstance.dismiss('success');
                $state.reload();
                AlertService.addAlert('success', '¡Las solicitudes de mantenimiento han sido asignadas a ' +
                    data.emailTrabajador + '!');
            }, function (response) {
                vm.crearError = true;
            });
        }
    }
})();