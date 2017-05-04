(function () {
    'use strict';

    angular
        .module('app.buildings.modInfo')
        .controller('ModInfoEdificioController', ModInfoEdificioController);

    ModInfoEdificioController.inject = ['$scope', 'AlertService', '$http', '$state'];

    function ModInfoEdificioController ($scope, $http, AlertService, $state) {
        var vm = this;

        vm.edificios = [];
        vm.edificioSeleccionado = null;
        vm.mesesSeleccionados = [];

        $http.get("/edificios").then(
            function (response) { //success
                var objetoEdificio = response.data;

                for(var i = 0; i < objetoEdificio.length; i++){
                    vm.edificios.push({
                        nombre: objetoEdificio[i].nombre,
                        horaApertura: objetoEdificio[i].horaApertura,
                        horaCierre: objetoEdificio[i].horaCierre,
                        menuCafeteria: objetoEdificio[i].menuCafeteria,
                        mesesCerrado: objetoEdificio[i].mesesCerrado
                    });
                }
            },
            function (response) { //error
                AlertService.addAlert('danger','Error al obtener los edificios del sistema');
            }
        );

        $scope.$watch('vm.edificioSeleccionado', function () {
            vm.enero = false;
            vm.febrero = false;
            vm.marzo = false;
            vm.abril = false;
            vm.mayo = false;
            vm.junio = false;
            vm.julio = false;
            vm.agosto = false;
            vm.septiembre = false;
            vm.octubre = false;
            vm.noviembre = false;
            vm.diciembre = false;
            if (vm.edificioSeleccionado === null || vm.edificioSeleccionado === undefined) {
                vm.horaApertura = null;
                vm.horaCierre = null;
                vm.menuCafeteria = null;
                vm.mesesSeleccionados = null;
            } else {
                vm.horaApertura = vm.edificioSeleccionado.horaApertura;
                vm.horaCierre = vm.edificioSeleccionado.horaCierre;
                vm.menuCafeteria = vm.edificioSeleccionado.menuCafeteria;
                vm.mesesSeleccionados = vm.edificioSeleccionado.mesesCerrado;

                for (var i = 0; i < vm.edificioSeleccionado.mesesCerrado.length; i++) {
                    switch (vm.edificioSeleccionado.mesesCerrado[i]) {
                        case 'Enero':
                            vm.enero = true;
                            break;
                        case 'Febrero':
                            vm.febrero = true;
                            break;
                        case 'Marzo':
                            vm.marzo = true;
                            break;
                        case 'Abril':
                            vm.abril = true;
                            break;
                        case 'Mayo':
                            vm.mayo = true;
                            break;
                        case 'Junio':
                            vm.junio = true;
                            break;
                        case 'Julio':
                            vm.julio = true;
                            break;
                        case 'Agosto':
                            vm.agosto = true;
                            break;
                        case 'Septiembre':
                            vm.septiembre = true;
                            break;
                        case 'Octubre':
                            vm.octubre = true;
                            break;
                        case 'Noviembre':
                            vm.noviembre = true;
                            break;
                        case 'Diciembre':
                            vm.diciembre = true;
                            break;
                        default:
                            console.log('elol');
                    }
                }
            }
        });

        vm.crearError = false;
        vm.modificar = modificar;
        vm.toggleCrear = toggleCrear;

        function toggleCrear() {
            vm.crearError = false;
        }

        function modificar(event) {
            event.preventDefault();

            if(vm.edificioSeleccionado === null || vm.edificioSeleccionado === undefined){

            } else {
                var data = {
                    horaApertura: vm.horaApertura,
                    horaCierre: vm.horaCierre,
                    menuCafeteria: vm.menuCafeteria,
                    mesesCerrado: vm.mesesSeleccionados
                };

                $http.post("/edificios/" + vm.edificioSeleccionado.nombre, data).then(
                    function (response) { //success
                        vm.crearError = false;

                        vm.horaApertura = null;
                        vm.horaCierre = null;
                        vm.menuCafeteria = null;
                        vm.mesesSeleccionados = null;

                        AlertService.addAlert('success', '¡La información del edificio ' + vm.edificioSeleccionado.nombre
                            + ' ha sido modificada con éxito!');

                        vm.edificioSeleccionado = null;
                        $state.go('home');
                    },
                    function (response) { //error
                        vm.crearError = true;
                    }
                );
            }
        }

        vm.toggleSelection = function(mes){
            switch (mes) {
                case 'enero':
                    if (vm.enero) {
                        vm.mesesSeleccionados.splice(vm.mesesSeleccionados.indexOf('Enero'), 1);
                        vm.enero = false;
                    } else {
                        vm.mesesSeleccionados.push('Enero');
                        vm.enero = true;
                    }
                    break;
                case 'febrero':
                    if (vm.febrero) {
                        vm.mesesSeleccionados.splice(vm.mesesSeleccionados.indexOf('Febrero'), 1);
                        vm.febrero = false;
                    } else {
                        vm.mesesSeleccionados.push('Febrero');
                        vm.febrero = true;
                    }
                    break;
                case 'marzo':
                    if (vm.marzo) {
                        vm.mesesSeleccionados.splice(vm.mesesSeleccionados.indexOf('Marzo'), 1);
                        vm.marzo = false;
                    } else {
                        vm.mesesSeleccionados.push('Marzo');
                        vm.marzo = true;
                    }
                    break;
                case 'abril':
                    if (vm.abril) {
                        vm.mesesSeleccionados.splice(vm.mesesSeleccionados.indexOf('Abril'), 1);
                        vm.abril = false;
                    } else {
                        vm.mesesSeleccionados.push('Abril');
                        vm.abril = true;
                    }
                    break;
                case 'mayo':
                    if (vm.mayo) {
                        vm.mesesSeleccionados.splice(vm.mesesSeleccionados.indexOf('Mayo'), 1);
                        vm.mayo = false;
                    } else {
                        vm.mesesSeleccionados.push('Mayo');
                        vm.mayo = true;
                    }
                    break;
                case 'junio':
                    if (vm.junio) {
                        vm.mesesSeleccionados.splice(vm.mesesSeleccionados.indexOf('Junio'), 1);
                        vm.junio = false;
                    } else {
                        vm.mesesSeleccionados.push('Junio');
                        vm.junio = true;
                    }
                    break;
                case 'julio':
                    if (vm.julio) {
                        vm.mesesSeleccionados.splice(vm.mesesSeleccionados.indexOf('Julio'), 1);
                        vm.julio = false;
                    } else {
                        vm.mesesSeleccionados.push('Julio');
                        vm.julio = true;
                    }
                    break;
                case 'agosto':
                    if (vm.agosto) {
                        vm.mesesSeleccionados.splice(vm.mesesSeleccionados.indexOf('Agosto'), 1);
                        vm.agosto = false;
                    } else {
                        vm.mesesSeleccionados.push('Agosto');
                        vm.agosto = true;
                    }
                    break;
                case 'septiembre':
                    if (vm.septiembre) {
                        vm.mesesSeleccionados.splice(vm.mesesSeleccionados.indexOf('Septiembre'), 1);
                        vm.septiembre = false;
                    } else {
                        vm.mesesSeleccionados.push('Septiembre');
                        vm.septiembre = true;
                    }
                    break;
                case 'octubre':
                    if (vm.octubre) {
                        vm.mesesSeleccionados.splice(vm.mesesSeleccionados.indexOf('Octubre'), 1);
                        vm.octubre = false;
                    } else {
                        vm.mesesSeleccionados.push('Octubre');
                        vm.octubre = true;
                    }
                    break;
                case 'noviembre':
                    if (vm.noviembre) {
                        vm.mesesSeleccionados.splice(vm.mesesSeleccionados.indexOf('Noviembre'), 1);
                        vm.noviembre = false;
                    } else {
                        vm.mesesSeleccionados.push('Noviembre');
                        vm.noviembre = true;
                    }
                    break;
                case 'diciembre':
                    if (vm.diciembre) {
                        vm.mesesSeleccionados.splice(vm.mesesSeleccionados.indexOf('Diciembre'), 1);
                        vm.diciembre = false;
                    } else {
                        vm.mesesSeleccionados.push('Diciembre');
                        vm.diciembre = true;
                    }
                    break;
                default:
                    console.log('elol');
            }
        }
    }
})();
