package es.unizar.campusManager.aplicacion.servicios;

import es.unizar.campusManager.dominio.entidades.Espacio;
import es.unizar.campusManager.dominio.repository.EspacioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ServicioEspacio {

    //Logging
    private final static Logger logger = Logger.getLogger(ServicioEspacio.class.getName());
    private EspacioRepository espacioRepository;

    public ServicioEspacio(EspacioRepository espacioRepository){
        this.espacioRepository = espacioRepository;
    }

    public List<Espacio> obtenerEspaciosEdificio(String nombreEdificio){
        logger.info("Obteniendo todos los espacios del edificio " + nombreEdificio);

        List<Espacio> todos = espacioRepository.findAll();
        List<Espacio> result = new ArrayList<>();

        for(Espacio espacio : todos){
            if(espacio.getInformacionEspacio().getEdifico().equals(nombreEdificio)){
                result.add(espacio);
            }
        }

        return result;
    }

    public Espacio obtenerEspacioById(String id) {
        logger.info("Obteniendo espacio by Id");

        return espacioRepository.findById(id);
    }

    public boolean hacerReservable(Espacio espacio) {
        logger.info("Haciendo reservable espacio.");

        if (espacio.isReservable()) {
            logger.severe("El espacio con nombre " + espacio.getInformacionEspacio().getNombreEspacio() +
                    " ya es reservable, abortando.");
            return false;
        } else {
            espacio.setReservable(true);
            espacioRepository.update(espacio);

            logger.info("Espacio " + espacio.getInformacionEspacio().getNombreEspacio() + " hecho reservable " +
                    "correctamemte.");
            return true;
        }
    }
}
