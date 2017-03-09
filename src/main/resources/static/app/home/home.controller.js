(function() {
    'use strict';

    angular
        .module('app.home')
        .config(['$routeProvider',
            function ($routeProvider) {
                $routeProvider.
                when('/', {
                    templateUrl: 'app/map/map.html',
                    controller: 'HomeController'
                }).
                when('/ada', {
                    templateUrl: 'app/account/login/login.html',
                    controller: 'AdaController'
                }).
                otherwise('/');
            }
        ]);

    HomeController.$inject = ['$scope'];

    function HomeController($scope) {
        var vm = this;
        $scope.vm.home = {
            title: 'Vista general',
            subtitle: 'Campus Escuela de Ingeniería y Arquitectura'
        };
    }

    function AdaController(){
        var vm = this;
        vm.home = {
            title: 'Vista específica',
            subtitle: 'Edificio Ada Byron'
        };
    }

})();
