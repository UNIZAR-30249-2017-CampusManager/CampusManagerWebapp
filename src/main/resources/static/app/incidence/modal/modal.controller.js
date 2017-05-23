(function () {
    'use strict';

    angular
        .module('app.incidence.modal')
        .controller('ModalController', ModalController);

    ModalController.inject = ['$uibModalInstance','$http','AlertService'];

    function ModalController($uibModalInstance,$http,AlertService,param) {
        //console.log("Modal incidence controller called!: " + param.ubicacion);
        var vm = this;

        vm.place = param.nombreEspacio;
        vm.building = param.nombreEdificio;
        vm.crearError = false;
        vm.cancel = cancel;
        vm.create = create;
        vm.toggleCrear = toggleCrear;

        vm.popup = {
            opened: false
        };

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
                nombre: vm.name,
                descripcion: vm.description,
                idEspacio: param.idEspacio,
                fechaLimite: convertDate(vm.fechaLimite)
            };

            $http.put("/incidencias", data).then(
                function (response) { //success
                    //console.log("Respuesta: " + response);
                    vm.crearError = false;

                    vm.name = null;
                    vm.place = null;
                    vm.description = null;
                    vm.building = null;
                    vm.fechaLimite = null;

                    AlertService.addAlert('success', '¡La incidencia en ' + param.nombreEspacio + ' ' + param.nombreEdificio + ' ha sido registrada con éxito!');

                    $uibModalInstance.dismiss('success');
                },
                function (response) { //error
                    vm.crearError = true;
                }
            );
        }

        vm.open = function() {
            vm.popup.opened = true;
        };


        function convertDate(inputFormat) {
            function pad(s) { return (s < 10) ? '0' + s : s; }
            var d = new Date(inputFormat);
            return [pad(d.getDate()), pad(d.getMonth()+1), d.getFullYear()].join('/');
        }
    }
})();