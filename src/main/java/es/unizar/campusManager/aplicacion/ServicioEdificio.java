package es.unizar.campusManager.aplicacion;

import es.unizar.campusManager.dominio.entidades.Edificio;
import es.unizar.campusManager.dominio.objetosValor.MenuCafeteria;
import es.unizar.campusManager.dominio.repository.EdificioRepository;

import java.util.List;
import java.util.logging.Logger;

public class ServicioEdificio {

    //Logging
    private final static Logger logger = Logger.getLogger(ServicioEdificio.class.getName());
    private EdificioRepository edificioRepository;

    public ServicioEdificio(EdificioRepository edificioRepository){
        this.edificioRepository = edificioRepository;
    }

    public Edificio obtenerEdificioPorNombre(String nombre){
        logger.info("Obteniendo el edificio con nombre " + nombre);
        return edificioRepository.findByName(nombre);
    }

    public boolean modificarInformacionEdificio(String nombreEdificio, String nuevoHorarioApertura,
                                                String nuevoHorarioCierre, List<String> nuevosMesesCerrados,
                                                MenuCafeteria nuevoMenuCafeteria){
        logger.info("Actualizando informacion del edificio con nombre " + nombreEdificio);

        Edificio edificio = obtenerEdificioPorNombre(nombreEdificio);

        if(edificio == null){
            logger.severe("El edificio con nombre " + nombreEdificio + " no existe, abortando");
            return false;
        } else {
            edificio.setHoraApertura(nuevoHorarioApertura);
            edificio.setHoraCierre(nuevoHorarioCierre);
            edificio.setMesesCerrado(nuevosMesesCerrados);
            edificio.setMenuCafeteria(nuevoMenuCafeteria);

            edificioRepository.update(edificio);

            logger.info("Edificio " + nombreEdificio + " actualizado satisfactoriamente");

            return true;
        }

    }


}
