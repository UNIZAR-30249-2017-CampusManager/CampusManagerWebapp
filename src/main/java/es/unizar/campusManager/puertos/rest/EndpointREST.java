package es.unizar.campusManager.puertos.rest;

import es.unizar.campusManager.aplicacion.ServicioIncidencia;
import es.unizar.campusManager.aplicacion.ServicioUsuario;
import es.unizar.campusManager.dominio.entidades.Incidencia;
import es.unizar.campusManager.infraestructura.DTO.*;
import es.unizar.campusManager.infraestructura.repository.AdminRepositoryImp;
import es.unizar.campusManager.infraestructura.repository.IncidenciaRepositoryImp;
import es.unizar.campusManager.infraestructura.repository.TrabajadorRepositoryImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class EndpointREST {

    //Logging
    private final static Logger logger = Logger.getLogger(EndpointREST.class.getName());
    private final TrabajadorRepositoryImp trabajadorRepositoryImp;
    private final AdminRepositoryImp adminRepositoryImp;
    private final IncidenciaRepositoryImp incidenciaRepositoryImp;

    @Autowired
    public EndpointREST(TrabajadorRepositoryImp trabajadorRepositoryImp, AdminRepositoryImp adminRepositoryImp,
                        IncidenciaRepositoryImp incidenciaRepositoryImp) {
        this.trabajadorRepositoryImp = trabajadorRepositoryImp;
        this.adminRepositoryImp = adminRepositoryImp;
        this.incidenciaRepositoryImp = incidenciaRepositoryImp;
    }

    @PostMapping(value = "/login")
    public ResponseEntity obtenerUsuario(@RequestBody UserDTO userDTO) {
        logger.info("Detectada peticion para obtener un administrador o trabajador con email " + userDTO.getEmail());

        ServicioUsuario servicioUsuario = new ServicioUsuario(adminRepositoryImp, trabajadorRepositoryImp);

        if (servicioUsuario.comprobarUsuario(userDTO.getEmail(), userDTO.getPassword())) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/rol/{email:.*}", produces = "application/json")
    public @ResponseBody
    String obtenerRol(@PathVariable String email) {
        logger.info("Detectada peticion para obtener el rol del usuario con email " + email);

        ServicioUsuario servicioUsuario = new ServicioUsuario(adminRepositoryImp, trabajadorRepositoryImp);

        return "{\"rol\": \"" + servicioUsuario.obtenerRol(email) + "\"}";
    }

    @PutMapping(value = "/crearTrabajador")
    public ResponseEntity crearTrabajador(@RequestBody TrabajadorDTO trabajadorDTO) {
        logger.info("Detectada peticion para crear un usuario trabajador");

        ServicioUsuario servicioUsuario = new ServicioUsuario(adminRepositoryImp, trabajadorRepositoryImp);

        if (servicioUsuario.crearTrabajador(trabajadorDTO.getEmailTrabajador(), trabajadorDTO.getPasswordTrabajador())) {
            //Creacion correcta
            logger.info("Trabajador con email " + trabajadorDTO.getEmailTrabajador() + " creado satisfactoriamente");
            return new ResponseEntity(HttpStatus.OK);
        } else {
            //Creacion fallida
            logger.severe("Error al crear trabajador con email " + trabajadorDTO.getEmailTrabajador());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/crearAdministrador")
    public ResponseEntity crearAdministrador(@RequestBody AdministradorDTO administradorDTO) {
        logger.info("Detectada peticion para crear un usuario administrador");

        ServicioUsuario servicioUsuario = new ServicioUsuario(adminRepositoryImp, trabajadorRepositoryImp);

        if (servicioUsuario.crearAdministrador(administradorDTO.getEmailAdministrador(), administradorDTO.getPasswordAdministrador())) {
            //Creacion correcta
            logger.info("Administrador con email " + administradorDTO.getEmailAdministrador() + " creado satisfactoriamente");
            return new ResponseEntity(HttpStatus.OK);
        } else {
            //Creacion fallida
            logger.severe("Error al crear administrador con email " + administradorDTO.getEmailAdministrador());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/incidencias", produces = "application/json")
    public @ResponseBody
    List<IncidenciaDTO> obtenerTodasIncidencias() {
        logger.info("Detectada peticion para obtener todas las incidencias del sistema");

        ServicioIncidencia servicioIncidencia = new ServicioIncidencia(incidenciaRepositoryImp);

        ArrayList<Incidencia> incidencias = (ArrayList<Incidencia>) servicioIncidencia.obtenerTodasIncidencias();
        ArrayList<IncidenciaDTO> incidenciaDTOS = new ArrayList<>();

        for (Incidencia incidencia : incidencias) {
            incidenciaDTOS.add(new IncidenciaDTO(incidencia.getId(), incidencia.getNombre(), incidencia.getDescripcion(),
                    incidencia.getEspacio().getNombreEspacio(), incidencia.getEspacio().getUbicacion().getNombreEdificio(),
                    incidencia.getEstado(), incidencia.getEmailTrabajador(), incidencia.getFecha(), incidencia.getGrupo()));
        }

        return incidenciaDTOS;
    }

    @GetMapping(value = "/incidencias/{email:.*}", produces = "application/json")
    public @ResponseBody
    List<IncidenciaDTO> obtenerIncidenciasTrabajador(@PathVariable String email) {
        logger.info("Detectada peticion para obtener las incidencias del trabajador con email " + email);

        ServicioIncidencia servicioIncidencia = new ServicioIncidencia(incidenciaRepositoryImp);

        ArrayList<Incidencia> incidencias = (ArrayList<Incidencia>) servicioIncidencia.obtenerIncidenciasTrabajador(email);
        ArrayList<IncidenciaDTO> incidenciaDTOS = new ArrayList<>();

        for (Incidencia incidencia : incidencias) {
            incidenciaDTOS.add(new IncidenciaDTO(incidencia.getId(), incidencia.getNombre(), incidencia.getDescripcion(),
                    incidencia.getEspacio().getNombreEspacio(), incidencia.getEspacio().getUbicacion().getNombreEdificio(),
                    incidencia.getEstado(), incidencia.getEmailTrabajador(), incidencia.getFecha(), incidencia.getGrupo()));
        }

        return incidenciaDTOS;
    }

    @PutMapping(value = "/incidencias")
    public ResponseEntity crearNuevaIncidencia(@RequestBody NuevaIncidenciaDTO nuevaIncidenciaDTO) {
        logger.info("Detectada peticion para crear una nueva incidencia");

        ServicioIncidencia servicioIncidencia = new ServicioIncidencia(incidenciaRepositoryImp);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = formatter.format(date);

        if (servicioIncidencia.crearIncidencia(nuevaIncidenciaDTO.getNombre(), nuevaIncidenciaDTO.getDescripcion(),
                formattedDate, nuevaIncidenciaDTO.getIdUtc(), nuevaIncidenciaDTO.getNombreEspacio(),
                nuevaIncidenciaDTO.getPlanta(), nuevaIncidenciaDTO.getNombreEdificio(),
                nuevaIncidenciaDTO.getX(), nuevaIncidenciaDTO.getY())) {
            //Creacion correcta
            logger.info("Incidencia con nombre " + nuevaIncidenciaDTO.getNombre() + " creada satisfactoriamente");
            return new ResponseEntity(HttpStatus.OK);
        } else {
            //Creacion fallida
            logger.severe("Error al crear la incidencia con nombre " + nuevaIncidenciaDTO.getNombre());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/incidencias")
    public ResponseEntity asignarIncidencia(@RequestBody AsignarIncidenciasDTO asignarIncidenciasDTO) {
        logger.info("Detectada peticion para asignar una incidencia a un trabajador");

        ServicioIncidencia servicioIncidencia = new ServicioIncidencia(incidenciaRepositoryImp);

        ArrayList<Incidencia> incidencias = new ArrayList<>();

        for (String id : asignarIncidenciasDTO.getIdsIncidencias()) {
            incidencias.add(servicioIncidencia.obtenerIncidenciaId(id));
        }

        if (servicioIncidencia.asignarIncidencias(asignarIncidenciasDTO.getEmailTrabajador(), incidencias, trabajadorRepositoryImp)) {
            //Asignacion correcta
            logger.info("Incidencias asignadas al trabajador " + asignarIncidenciasDTO.getEmailTrabajador() + " correctamente");
            return new ResponseEntity(HttpStatus.OK);
        } else {
            //Asignacion incorrecta
            logger.info("Error al asignar incidencias " + asignarIncidenciasDTO.getIdsIncidencias().toString() +
                    " al trabajador " + asignarIncidenciasDTO.getEmailTrabajador());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/incidencias/{grupoIncidencia:.*}")
    public ResponseEntity cambiarEstadoIncidencia(@PathVariable Integer grupoIncidencia,
                                                  @RequestBody NuevoEstadoIncidenciaDTO nuevoEstadoIncidenciaDTO){
        logger.info("Peticion para cambiar el estado de las incidencias del grupo " + grupoIncidencia + " a " + nuevoEstadoIncidenciaDTO.getEstado());

        ServicioIncidencia servicioIncidencia = new ServicioIncidencia(incidenciaRepositoryImp);

        if(servicioIncidencia.cambiarEstado(grupoIncidencia,nuevoEstadoIncidenciaDTO.getEstado())){
            //Cambio de estado satisfactorio
            logger.info("Incidencias actualizadas a " + nuevoEstadoIncidenciaDTO.getEstado() + " correctamente");
            return new ResponseEntity(HttpStatus.OK);
        } else {
            //Cambio de estado erroneo
            logger.severe("Ha habido algun problema al actualizar las incidencias del grupo " + grupoIncidencia + " al estado " + nuevoEstadoIncidenciaDTO.getEstado());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
