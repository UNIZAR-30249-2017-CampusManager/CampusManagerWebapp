(function () {
    'use strict';

    angular
        .module('app.account.login')
        .factory('LoginService', LoginService);

    LoginService.$inject = ['$uibModal', '$http', '$rootScope', '$localStorage','$sessionStorage'];

    function LoginService($uibModal, $http, $rootScope, $localStorage, $sessionStorage) {
        var vm = this;
        //Objeto para guardar lo relacionado con la sesion
        vm.session = $localStorage;

        var service = {
            open: open,
            login: login,
            logout: logout,
            isLogged: isLogged,
            currentLoggedUser: currentLoggedUser,
            currentRole: currentRole
        };

        var modalInstance = null;
        var resetModal = function () {
            modalInstance = null;
        };

        return service;

        function open() {
            if (modalInstance !== null) return;
            modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/account/login/login.html',
                controller: 'LoginController',
                controllerAs: 'vm'
            });
            modalInstance.result.then(
                resetModal,
                resetModal
            );
        }

        function login(usuario){
            vm.session.logged = {
                usuario: usuario
            };
            currentRole();
        }

        function logout() {
            delete $localStorage.logged;
        }

        function isLogged(){
            return vm.session.logged !== undefined;
        }

        function currentLoggedUser(){
            if(isLogged()){
                return vm.session.logged.usuario;
            } else return undefined;
        }

        function currentRole(){
            if(isLogged()){
                $http.get("/rol/" + currentLoggedUser().email).then(
                    function (response) { //success
                        $rootScope.rol = response.data.rol;
                    },
                    function (response) { //error
                        return '';
                    }
                );
            }
        }
    }
})();
