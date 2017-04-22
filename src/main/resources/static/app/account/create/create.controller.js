(function () {
    'use strict';

    angular
        .module('app.account.create')
        .controller('CreateController', CreateController);

    CreateController.inject = ['$scope','$http','AlertService', 'LoginService'];

    function CreateController($scope,$http,AlertService, LoginService) {
        var vm = this;

        var availableOptions = [
            {value: 'ADMIN', name: 'Administrador'},
            {value: 'WORKER', name: 'Trabajador'}
        ];

        vm.crearError = false;
        vm.availableOptions = availableOptions;
        vm.create = create;
        vm.toggleCrear = toggleCrear;

        $scope.selected = {};

        function toggleCrear(){
            vm.crearError = false;
        }

        function create(event) {
            event.preventDefault();

            if($scope.selected.value === undefined){
                //console.log("Selecciona ROL loco");
            } else{
                var user = LoginService.currentLoggedUser();

                var role= $scope.selected.value.value;

                if(role === 'WORKER'){
                    var dataTrabajador = {
                        emailTrabajador: vm.email,
                        passwordTrabajador: vm.password,
                        emailAdmin: user.email,
                        passwordAdmin: user.password
                    };

                    $http.put("/crearTrabajador", dataTrabajador).then(
                        function (response) { //success
                            vm.crearError = false;

                            AlertService.addAlert('success','¡El trabajador ' + dataTrabajador.emailTrabajador + ' ha sido creado con éxito!')
                        },
                        function (response) { //error
                            vm.crearError = true;
                        }
                    );
                } else {
                    var dataAdmin = {
                        emailAdministrador: vm.email,
                        passwordAdministrador: vm.password,
                        emailAdmin: user.email,
                        passwordAdmin: user.password
                    };

                    $http.put("/crearAdministrador", dataAdmin).then(
                        function (response) { //success
                            vm.crearError = false;

                            AlertService.addAlert('success','¡El administrador ' + dataAdmin.emailAdministrador + ' ha sido creado con éxito!')
                        },
                        function (response) { //error
                            vm.crearError = true;
                        }
                    );
                }
            }
        }
    }
})();