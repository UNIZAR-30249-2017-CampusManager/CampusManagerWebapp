(function () {
    'use strict';

    angular
        .module('app.incidence.modal')
        .factory('ModalService', ModalService);

    ModalService.$inject = ['$uibModal'];

    function ModalService($uibModal) {
        var vm = this;

        var service = {
            open: open
        };

        var modalInstance = null;
        var resetModal = function () {
            modalInstance = null;
        };

        return service;

        function open(ubicacion, building) {
            if (modalInstance !== null) return;
            modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/incidence/modal/modal.html',
                controller: 'ModalController',
                controllerAs: 'vm',
                resolve:{
                    param: function () {
                        return {'ubicacion':ubicacion,
                                'building' : building};
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
