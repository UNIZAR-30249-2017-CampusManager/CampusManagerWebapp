(function () {
    'use strict';

    angular
        .module('app.incidence.maintenanceModal')
        .factory('MaintenanceModalService', MaintenanceModalService);

    MaintenanceModalService.$inject = ['$uibModal'];

    function MaintenanceModalService($uibModal) {
        var vm = this;

        var service = {
            open: open
        };

        var modalInstance = null;
        var resetModal = function () {
            modalInstance = null;
        };

        return service;

        function open(idArray) {
            if (modalInstance !== null) return;
            modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/incidence/maintenanceModal/maintenanceModal.html',
                controller: 'MaintenanceModalController',
                controllerAs: 'vm',
                resolve:{
                    param: function () {
                        return {'idArray': idArray};
                    }
                }
            });
            modalInstance.result.then(
                resetModal,
                resetModal
            );
        }
    }
})();
