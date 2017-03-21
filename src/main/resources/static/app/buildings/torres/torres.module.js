(function(){
    'use strict';

    angular
        .module('app.buildings.torres',[
            'leaflet-directive',
            'ui.router'
        ])
        .config(function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise('/');

            $stateProvider
                .state('torres', {
                    url: '/torres',
                    templateUrl: 'app/buildings/torres/torres.html',
                    controller: 'TorresController as vm'

                });

        });
})();