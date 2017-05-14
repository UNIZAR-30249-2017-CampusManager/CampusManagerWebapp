package es.unizar.campusManager.aplicacion.servicios;

import es.unizar.campusManager.dominio.entidades.Espacio;
import es.unizar.campusManager.dominio.entidades.Reserva;
import es.unizar.campusManager.dominio.repository.EspacioRepository;
import es.unizar.campusManager.dominio.repository.ReservaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class ServicioReservas {

    //Logging
    private final static Logger logger = Logger.getLogger(ServicioReservas.class.getName());
    private EspacioRepository espacioRepository;
    private ReservaRepository reservaRepository;

    public ServicioReservas(EspacioRepository espacioRepository, ReservaRepository reservaRepository){
        this.espacioRepository = espacioRepository;
        this.reservaRepository = reservaRepository;
    }

    public List<Espacio> obtenerEspacios(){
        logger.info("Obteniendo todos los espacios reservables");
        return espacioRepository.findAll();
    }

    public List<String> obtenerHorasLibres(String idEspacioReservable, String fecha){
        logger.info("Obteniendo las horas libres para el dia " + fecha + " del espacio con id " + idEspacioReservable);

        Espacio espacio = espacioRepository.findById(idEspacioReservable);

        if(espacio == null){
            logger.severe("El espacio con id " + idEspacioReservable + " no existe, abortando");
            return null;
        } else {
            logger.info("Obteniendo horas libres en la fecha especificada para el espacio " + espacio.getInformacionEspacio().getNombreEspacio());

            ArrayList<String> todasHoras = poblarHoras();

            List<Reserva> reservasFecha = reservaRepository.findByDate(fecha);

            limpiarHoras(todasHoras,idEspacioReservable, (ArrayList<Reserva>) reservasFecha);

            logger.info("Las horas libres para reservar el espacio " + espacio.getInformacionEspacio().getNombreEspacio() + " en la fecha " + fecha + " son: " + todasHoras.toString());

            return todasHoras;
        }
    }

    public boolean crearReserva(String emailProfesor, String fecha, String hora, String idEspacioReservable){
        logger.info("Creando reserva en el espacio " + idEspacioReservable + " en la fecha " + fecha + " y hora " +  hora);

        String[] correoAux = emailProfesor.split("@");

        if(correoAux[1].equals("unizar.es")){
            if(correoAux[0].matches(".*\\d+.*")){
                //Correo es de unizar pero tiene numero, no es profesor
                logger.severe("El email introducido no corresponde al de un profesor");
                return false;
            } else {
                //Correo valido
                boolean estaLibre = false;

                ArrayList<String> horasLibresFecha = (ArrayList<String>) obtenerHorasLibres(idEspacioReservable,fecha);

                if(horasLibresFecha == null){
                    logger.severe("No existe el espacio, abortando");
                    return false;
                } else {
                    for(String horaLibre : horasLibresFecha){
                        if(horaLibre.equals(hora)){
                            estaLibre = true;
                            break;
                        }
                    }
                    if(!estaLibre){
                        logger.severe("La hora especificada no esta libre en la fecha " + fecha + " para el espacio " + idEspacioReservable);
                        return false;
                    } else {
                        Espacio espacio = espacioRepository.findById(idEspacioReservable);

                        if(espacio == null || !espacio.isReservable()){
                            logger.severe("El espacio no es valido, abortando");
                            return false;
                        } else {
                            return reservaRepository.save(new Reserva(emailProfesor,fecha,hora,espacio));
                        }
                    }
                }
            }
        } else {
            //Correo no es de unizar
            logger.severe("El email introducido no es de la universidad");
            return false;
        }


    }

    private ArrayList<String> poblarHoras(){
        ArrayList<String> todasHoras = new ArrayList<>();
        todasHoras.add("08:00");
        todasHoras.add("09:00");
        todasHoras.add("10:00");
        todasHoras.add("11:00");
        todasHoras.add("12:00");
        todasHoras.add("13:00");
        todasHoras.add("14:00");
        todasHoras.add("15:00");
        todasHoras.add("16:00");
        todasHoras.add("17:00");
        todasHoras.add("18:00");
        todasHoras.add("19:00");
        todasHoras.add("20:00");

        return todasHoras;
    }

    private void limpiarHoras(ArrayList<String> horas, String idEspacio, ArrayList<Reserva> reservas){
        for(Reserva reserva : reservas){
            if(reserva.getEspacio().getId().equals(idEspacio)){
                //Encontrada reserva para el espacio, sabemos que la fecha es la misma

                for(int i = 0; i < horas.size(); i++){
                    String hora = horas.get(i);
                    if(hora != null && hora.equals(reserva.getHora())){
                        horas.set(i,null);
                    }
                }
            }
        }

        horas.removeIf(Objects::isNull);
    }
}
