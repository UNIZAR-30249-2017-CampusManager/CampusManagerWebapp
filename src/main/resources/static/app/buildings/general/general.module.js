(function () {
    'use strict';

    angular
        .module('app.buildings.general', [
            'leaflet-directive',
            'ui.router'
        ])
        .config(function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise('/');

            $stateProvider
                .state('home', {
                    url: '/',
                    templateUrl: 'app/buildings/general/general.html',
                    controller: 'GeneralController as vm'
                });

        });
})();