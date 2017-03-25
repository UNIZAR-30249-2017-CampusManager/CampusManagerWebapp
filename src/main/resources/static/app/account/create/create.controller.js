(function () {
    'use strict';

    angular
        .module('app.account.create')
        .controller('CreateController', CreateController);

    CreateController.inject = ['$scope','$http','AlertService'];

    function CreateController($scope,$http,AlertService) {
        var vm = this;

        var availableOptions = [
            {value: 'ADMIN', name: 'Administrador'},
            {value: 'WORKER', name: 'Trabajador'},
            {value: 'PROFFESOR', name: 'Profesor'}
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

            if($scope.selected.value == undefined){
                //console.log("Selecciona ROL loco");
            } else{
                //console.log("Email: " + vm.email + " , Password: " + vm.password + " , rol: " + $scope.selected.value.value);

                var data = {
                    name: vm.name,
                    surname: vm.surname,
                    email: vm.email,
                    password: vm.password,
                    role: $scope.selected.value.value
                };

                $http.post("/api/user", data).then(
                    function (response) { //success
                        //console.log("Respuesta: " + response);
                        vm.crearError = false;

                        AlertService.addAlert('success','¡El usuario ' + data.email + ' ha sido registrado con éxito!')
                    },
                    function (response) { //error
                        vm.crearError = true;
                    }
                );
            }
        }
    }
})();