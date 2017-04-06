(function () {
    'use strict';

    angular
        .module('app', [
            'ngSanitize',
            'ui.router',
            'ngAnimate',
            'angular-loading-bar',
            //Ir agregando nuevos modulos aqui cuando se vayan creando
            'app.account',
            'app.layout',
            'app.incidence',
            'app.buildings'])
        .config(function ($stateProvider, $urlRouterProvider,$logProvider,$locationProvider) {
            $logProvider.debugEnabled(false);
            $locationProvider.html5Mode(true);

            $urlRouterProvider.otherwise('/');

            $stateProvider
                .state('home', {
                    url: '/',
                    templateUrl: 'app/buildings/general/general.html',
                    controller: 'GeneralController as vm'
                })
                .state('ada', {
                    url: '/ada',
                    templateUrl: 'app/buildings/ada/ada.html',
                    controller: 'AdaController as vm'
                })
                .state('betan', {
                    url: '/betan',
                    templateUrl: 'app/buildings/betan/betan.html',
                    controller: 'BetanController as vm'
                })
                .state('torres', {
                    url: '/torres',
                    templateUrl: 'app/buildings/torres/torres.html',
                    controller: 'TorresController as vm'
                })
                .state('crearUsuario', {
                    url: '/crearUsuario',
                    templateUrl: 'app/account/create/create.html',
                    controller: 'CreateController as vm'
                })
                .state('crearIncidencia', {
                    url: '/crearIncidencia',
                    templateUrl: 'app/incidence/create/create.html',
                    controller: 'CreateIncidenceController as vm'
                })
                .state('listarIncidencia', {
                    url: '/listarIncidencia',
                    templateUrl: 'app/incidence/list/list.html',
                    controller: 'ListIncidenceController as vm'
                });
        });

})();
