(function () {
    'use strict';

    angular
        .module('app.account.login')
        .controller('LoginController', LoginController);

    LoginController.inject = ['$uibModalInstance','$http','LoginService','AlertService'];

    function LoginController($uibModalInstance,$http,LoginService,AlertService) {
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

            //Comprobar mediante peticion a API RESTful que son correctos los campos
            $http.get("/api/user/" + vm.email).then(
                function (response) { //success
                    var usuario = response.data;

                    if(usuario.password == vm.password){
                        //Exito
                        vm.loginError = false;

                        AlertService.addAlert('info','¡Bienvenid@ de vuelta ' + usuario.name + '!');
                        LoginService.login(usuario);

                        $uibModalInstance.dismiss('success');
                    } else{
                        //Password incorrecto
                        //console.log("Password introducida: " + vm.password + ", password valida: " + usuario.password);
                        vm.loginError = true;
                    }
                },
                function (response) { //error
                    vm.loginError = true;
                }
            );
        }
    }
})();