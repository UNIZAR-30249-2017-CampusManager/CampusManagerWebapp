package es.unizar.campusManager.aplicacion;

import es.unizar.campusManager.dominio.entidades.Incidencia;
import es.unizar.campusManager.dominio.objetosValor.Coordenadas;
import es.unizar.campusManager.dominio.objetosValor.Espacio;
import es.unizar.campusManager.dominio.objetosValor.Ubicacion;
import es.unizar.campusManager.dominio.repository.IncidenciaRepository;

import java.util.List;
import java.util.logging.Logger;

public class ServicioIncidencia {

    //Logging
    private final static Logger logger = Logger.getLogger(ServicioIncidencia.class.getName());
    private IncidenciaRepository incidenciaRepository;

    public ServicioIncidencia(IncidenciaRepository incidenciaRepository){
        this.incidenciaRepository = incidenciaRepository;
    }

    public boolean crearIncidencia(String nombre, String descripcion, String fecha, String idUtc,
                                   String nombreEspacio, Integer plantaUbicacion,
                                   String nombreEdificio, String x, String y){

        //TODO: Poner sistema de coordenadas por defecto
        Coordenadas coordenadas = new Coordenadas(x,y,"");
        Ubicacion ubicacion = new Ubicacion(plantaUbicacion,nombreEdificio,coordenadas);
        Espacio espacio = new Espacio(idUtc,nombreEspacio,true,ubicacion);

        logger.info("Creando incidencia con nombre " + nombre + ", descripcion " + descripcion +
        ", en el espacio con nombre " + nombreEspacio + " ubicado en la planta " + plantaUbicacion +
        " del edificio " + nombreEdificio);

        return incidenciaRepository.crearIncidencia(nombre,descripcion,fecha,espacio);
    }

    public List<Incidencia> obtenerTodasIncidencias(){
        logger.info("Obteniendo todas las incidencias del sistema");
        return incidenciaRepository.obtenerTodasIncidencias();
    }

    public List<Incidencia> obtenerIncidenciasTrabajador(String emailTrabajador){
        logger.info("Obteniendo las incidencias asignadas al trabajador " + emailTrabajador);
        return incidenciaRepository.obtenerIncidenciasTrabajador(emailTrabajador);
    }
}
