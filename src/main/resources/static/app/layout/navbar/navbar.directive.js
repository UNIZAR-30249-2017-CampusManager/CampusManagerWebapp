(function() {
    'use strict';

    angular
        .module('app.layout.navbar')
        .directive('navbar', navbar);

    function navbar() {
        var directive = {
            bindToController: true,
            controller: NavbarController,
            controllerAs: 'vm',
            restrict: 'EA',
            scope: {
                'navline': '='
            },
            templateUrl: 'app/layout/navbar/navbar.html'
        };

        NavbarController.$inject = ['$scope','LoginService','AlertService'];

        function NavbarController($scope,LoginService,AlertService) {
            //console.log("Navbar controller called!");
            var vm = this;
            $scope.isCollapsed = true;

            vm.login = login;
            vm.logout = logout;
            vm.isLogged = isLogged;

            function login() {
                $scope.isCollapsed = true;
                LoginService.open();
            }

            function isLogged(){
                return LoginService.isLogged();
            }

            function logout(){
                $scope.isCollapsed = true;
                AlertService.addAlert('info','Sesión cerrada satisfactoriamente');
                LoginService.logout();
            }
        }

        return directive;
    }
})();
