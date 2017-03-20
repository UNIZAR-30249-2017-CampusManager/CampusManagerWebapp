(function () {
    'use strict';

    angular
        .module('app.account.login')
        .controller('LoginController', LoginController);

    LoginController.inject = ['$uibModalInstance'];
    function LoginController($uibModalInstance) {
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
        }
    }
})();