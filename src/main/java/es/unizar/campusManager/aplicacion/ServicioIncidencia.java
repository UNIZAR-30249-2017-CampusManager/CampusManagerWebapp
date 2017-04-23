package es.unizar.campusManager.aplicacion;

import es.unizar.campusManager.dominio.entidades.Incidencia;
import es.unizar.campusManager.dominio.objetosValor.Coordenadas;
import es.unizar.campusManager.dominio.objetosValor.Espacio;
import es.unizar.campusManager.dominio.objetosValor.Ubicacion;
import es.unizar.campusManager.dominio.repository.IncidenciaRepository;
import es.unizar.campusManager.dominio.repository.TrabajadorRepository;

import java.util.List;
import java.util.Random;
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
        Espacio espacio = new Espacio(idUtc,nombreEspacio,ubicacion);

        logger.info("Creando incidencia con nombre " + nombre + ", descripcion " + descripcion +
        ", en el espacio con nombre " + nombreEspacio + " ubicado en la planta " + plantaUbicacion +
        " del edificio " + nombreEdificio);

        return incidenciaRepository.save(new Incidencia(nombre,descripcion,fecha,espacio));
    }

    public List<Incidencia> obtenerTodasIncidencias(){
        logger.info("Obteniendo todas las incidencias del sistema");
        return incidenciaRepository.findAll();
    }

    public List<Incidencia> obtenerIncidenciasTrabajador(String emailTrabajador){
        logger.info("Obteniendo las incidencias asignadas al trabajador " + emailTrabajador);
        return incidenciaRepository.findByWorkerEmail(emailTrabajador);
    }

    public Incidencia obtenerIncidenciaId(String id){
        logger.info("Obteniendo la incidencia con id " + id);
        return incidenciaRepository.findById(id);
    }

    public boolean asignarIncidencias(String emailTrabajador, List<Incidencia> incidencias, TrabajadorRepository trabajadorRepository){
        logger.info("Asignando incidencias al trabajador " + emailTrabajador);

        if(trabajadorRepository.findByEmail(emailTrabajador) != null){

            boolean hayAlgunaYaAsignada = false;
            boolean hayAlgunaNull = false;

            //Incidencias puede ser null si los ids eran incorrectos

            //Comprobamos que todas las incidencias que nos pasan estan sin asignar
            for(Incidencia incidencia : incidencias){
                if(incidencia != null){
                    if(!incidencia.getEstado().equals("Sin asignar")) {
                        hayAlgunaYaAsignada = true;
                        break;
                    }
                } else {
                    hayAlgunaNull = true;
                    break;
                }
            }

            if(hayAlgunaYaAsignada || hayAlgunaNull){
                logger.severe("Al menos una incidencia ya esta asignada o es nula, abortando operacion");
                return false;
            } else {
                Random random = new Random();
                Integer numeroGrupoAleatorio = random.nextInt(1000000);

                //Asignamos a cada incidencia el trabajador y el grupo
                for(Incidencia incidencia : incidencias){
                    incidencia.setEmailTrabajador(emailTrabajador);
                    incidencia.setEstado("Asignada");
                    incidencia.setGrupo(numeroGrupoAleatorio);

                    incidenciaRepository.update(incidencia);
                }

                return true;
            }

        } else {
            logger.severe(emailTrabajador + " no representa el email de un trabajador");
            return false;
        }
    }

    public boolean cambiarEstado(Integer grupo, String nuevoEstado){
        logger.info("Cambiando estado de las incidencias del grupo " + grupo + " a " + nuevoEstado);

        if(!nuevoEstado.equals("En curso") && !nuevoEstado.equals("Invalido") && !nuevoEstado.equals("Finalizado")){
            logger.severe("Error, el nuevo estado " + nuevoEstado + " no es valido");
            return false;
        } else {
            List<Incidencia> incidencias = incidenciaRepository.findByGroup(grupo);

            boolean error = false;

            for(Incidencia incidencia : incidencias){
                if(incidencia != null){
                    incidencia.setEstado(nuevoEstado);
                    incidenciaRepository.update(incidencia);
                } else {
                    error = true;
                    break;
                }
            }

            if(error){
                logger.severe("Error, no se han encontrado incidencias en el grupo " + grupo);
                return false;
            } else {
                logger.info("Incidencias del grupo " + grupo + " actualizadas al estado " + nuevoEstado + " satisfactoriamente");
                return true;
            }
        }
    }
}
