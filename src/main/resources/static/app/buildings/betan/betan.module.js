(function(){
    'use strict';

    angular
        .module('app.buildings.betan',[
            'leaflet-directive',
            'ui.router'
        ])
        .config(function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise('/');

            $stateProvider
                .state('betan', {
                    url: '/betan',
                    templateUrl: 'app/buildings/betan/betan.html',
                    controller: 'BetanController as vm'

                });

        });
})();