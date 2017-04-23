(function () {
    'use strict';

    angular
        .module('app.account.login')
        .controller('LoginController', LoginController);

    LoginController.inject = ['$uibModalInstance','$http','$state', 'LoginService','AlertService'];

    function LoginController($uibModalInstance,$http,$state,LoginService,AlertService) {
        //console.log("Login controller called!");
        var vm = this;

        vm.authenticationError = false;
        vm.password = null;
        vm.email = null;
        vm.cancel = cancel;
        vm.login = login;
        vm.toggleLogin = toggleLogin;

        function toggleLogin(){
            vm.loginError = false;
        }

        function cancel() {
            vm = {
                email: null,
                password: null
            };
            vm.authenticationError = false;
            $uibModalInstance.dismiss('cancel');
        }

        function login(event) {
            event.preventDefault();
            // console.log(vm.username);
            // console.log(vm.password);

            var data ={
                email: vm.email,
                password: vm.password
            };

            //Comprobar mediante peticion a API RESTful que son correctos los campos
            $http.post("/login", data).then(
                function (response) { //success
                    vm.loginError = false;

                    AlertService.addAlert('info','¡Bienvenid@ de vuelta ' + data.email + '!');
                    LoginService.login(data);

                    $uibModalInstance.dismiss('success');
                    $state.go('home',{ reload: true, inherit: true, notify: true });
                },
                function (response) { //error
                    vm.loginError = true;
                }
            );
        }
    }
})();