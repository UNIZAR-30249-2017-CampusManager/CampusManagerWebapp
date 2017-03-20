(function () {
    'use strict';

    angular
        .module('app.account.login')
        .controller('LoginController', LoginController);

    LoginController.inject = ['$uibModalInstance'];

    function LoginController($uibModalInstance,LoginService) {
        console.log("Login controller called!");
        var vm = this;

        vm.authenticationError = false;
        vm.password = null;
        vm.username = null;
        vm.cancel = cancel;
        vm.login = login;

        function cancel() {
            vm.credentials = {
                username: null,
                password: null
            };
            vm.authenticationError = false;
            $uibModalInstance.dismiss('cancel');
        }

        function login(event) {
            event.preventDefault();
            console.log(vm.username);
            console.log(vm.password);

            //Comprobar mediante peticion a API RESTful que son correctos los campos
            //..........

            LoginService.login();

            $uibModalInstance.dismiss('success');
        }
    }
})();