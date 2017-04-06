(function () {
    'use strict';

    angular
        .module('app.layout')
        .factory('AlertService', AlertService);

    function AlertService($rootScope) {
        var alertService;
        $rootScope.alerts = [];
        $rootScope.permanentAlerts = [];
        return alertService = {
            addAlert: function (type, msg) {
                $rootScope.alerts.push({
                    type: type,
                    msg: msg,
                    close: function () {
                        return alertService.closeAlert(this);
                    }
                });
            },
            addPermanentAlert: function () {
                if($rootScope.permanentAlerts.length === 0){
                    $rootScope.permanentAlerts.push({
                        type: 'warning',
                        close: function () {
                            return alertService.closePermanentAlert(this);
                        }
                    });
                }
            },
            closePermanentAlert: function(alert) {
                return this.closePermanentAlertIdx($rootScope.permanentAlerts.indexOf(alert));
            },
            closePermanentAlertIdx: function(index) {
                return $rootScope.permanentAlerts.splice(index, 1);
            },
            closeAlert: function(alert) {
                return this.closeAlertIdx($rootScope.alerts.indexOf(alert));
            },
            closeAlertIdx: function(index) {
                return $rootScope.alerts.splice(index, 1);
            }
        };
    }
})();
