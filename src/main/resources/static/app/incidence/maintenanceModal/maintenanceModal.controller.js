(function () {
    'use strict';

    angular
        .module('app.incidence.maintenanceModal')
        .controller('MaintenanceModalController', MaintenanceModalController);

    MaintenanceModalController.inject = ['$uibModalInstance','$http','AlertService'];

    function MaintenanceModalController($uibModalInstance, $http, AlertService, param, MaintenanceModalService) {
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
                worker: null
            };
            vm.crearError = false;
            $uibModalInstance.dismiss('cancel');
        }

        function create(event) {
            event.preventDefault();

            var incidenciasArray = param.idArray;
            var requestId = incidenciasArray[0].idIncidencia;
            for (var i = 0; i < incidenciasArray.length; i++) {
                (function () {
                    var data = {
                        status: "ASSIGNED",
                        workerEmail: vm.workerEmail,
                        requestId: requestId
                    };

                    var idIncidencia = incidenciasArray[i].idIncidencia;
                    var aux = MaintenanceModalService.incidencias.indexOf(incidenciasArray[i]);

                    $http.put("/api/incidencia/" + idIncidencia, data).then(function (response) {
                        vm.crearError = false;

                        MaintenanceModalService.incidencias[aux].estadoIncidencia = "ASSIGNED";
                        MaintenanceModalService.incidencias[aux].requestId = requestId;

                        vm.status = null;
                        vm.workerEmail = null;
                        vm.requestId = null;

                        $uibModalInstance.dismiss('success');
                        AlertService.addAlert('success', '¡Las solicitudes de mantenimiento han sido asignadas a ' + data.workerEmail + '!');
                    }, function (response) {
                        vm.crearError = true;
                    });
                })();
            }
        }
    }
})();