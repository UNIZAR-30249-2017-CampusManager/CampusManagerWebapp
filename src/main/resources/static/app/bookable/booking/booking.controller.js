(function () {
    'use strict';

    angular
        .module('app.bookable.booking')
        .controller('BookingController', BookingController);

    BookingController.inject = ['$scope', '$http', 'AlertService'];

    function BookingController($scope, $http, AlertService) {
        var vm = this;

        vm.edificios = [];
        vm.espacios = [];
        vm.horas = ['08:00','09:00','10:00','11:00','12:00','13:00',
        '14:00','15:00','16:00','17:00','18:00','19:00','20:00'];
        vm.edificioSeleccionado = null;
        vm.espacioSeleccionado = null;

        vm.format = 'dd/MM/yyyy';


        vm.popup = {
          opened: false
        };

        $http.get("/edificios").then(
            function (response) { //success
                var objetoEdificio = response.data;

                for(var i = 0; i < objetoEdificio.length; i++){
                    vm.edificios.push(
                        {nombre: objetoEdificio[i].nombre}
                    );
                }
            },
            function (response) { //error
                AlertService.addAlert('danger','Error al obtener los edificios del sistema');
            }
        );

        $scope.$watch('vm.edificioSeleccionado', function() {
            if(vm.edificioSeleccionado !== null && vm.edificioSeleccionado !== undefined){
                $http.get("/espacios/" + vm.edificioSeleccionado.nombre).then(
                    function (response) { //success
                        var objetoEspacio = response.data;

                        for(var i = 0; i < objetoEspacio.length; i++){
                            vm.espacios.push(
                                objetoEspacio[i]
                            );
                        }
                    },
                    function (response) { //error
                        AlertService.addAlert('danger','Error al obtener los espacios del sistema');
                    }
                );
            }
        });

        vm.crearError = false;
        vm.create = create;
        vm.toggleCrear = toggleCrear;

        function toggleCrear() {
            vm.crearError = false;
        }



        function create(event) {
            event.preventDefault();

            if(vm.espacioSeleccionado === null || vm.espacioSeleccionado === undefined){

            } else {

                var data = {
                    emailProfesor: vm.email,
                    idEspacioReservable: vm.espacioSeleccionado.id,
                    fecha: convertDate(vm.fecha),
                    hora: vm.hora
                };


                $http.put("/reservas", data).then(
                    function (response) { //success
                        AlertService.addAlert('success','La reserva se ha realizado satisfactoriamente');
                    },
                    function (response) { //error

                        AlertService.addAlert('danger', 'El espacio no esta disponible para esa hora o el correo no se corresponde con el de un profesor');
                    }
                );
            }
        }

        vm.open = function() {
            vm.popup.opened = true;
        }


        function convertDate(inputFormat) {
          function pad(s) { return (s < 10) ? '0' + s : s; }
          var d = new Date(inputFormat);
          return [pad(d.getDate()), pad(d.getMonth()+1), d.getFullYear()].join('/');
        }


    }
})();