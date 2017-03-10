(function(){
    'use strict';

    angular
        .module('app.buildings.ada',[
            'leaflet-directive',
            'ui.router'
        ])
        .config(function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise('/');

            $stateProvider
                .state('ada', {
                    url: '/ada',
                    templateUrl: 'app/buildings/ada/ada.html',
                    controller: 'AdaController as vm'

                });

        });
})();