package es.unizar.campusManager.puertos.rest;

import es.unizar.campusManager.aplicacion.ServicioUsuario;
import es.unizar.campusManager.infraestructura.DTO.AdministradorDTO;
import es.unizar.campusManager.infraestructura.DTO.UserDTO;
import es.unizar.campusManager.infraestructura.repository.AdminRepositoryImp;
import es.unizar.campusManager.infraestructura.DTO.TrabajadorDTO;
import es.unizar.campusManager.infraestructura.repository.TrabajadorRepositoryImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
public class EndpointREST {

    //Logging
    private final static Logger logger = Logger.getLogger(EndpointREST.class.getName());
    private final TrabajadorRepositoryImp trabajadorRepositoryImp;
    private final AdminRepositoryImp adminRepositoryImp;

    @Autowired
    public EndpointREST(TrabajadorRepositoryImp trabajadorRepositoryImp, AdminRepositoryImp adminRepositoryImp) {
        this.trabajadorRepositoryImp = trabajadorRepositoryImp;
        this.adminRepositoryImp = adminRepositoryImp;
    }

    @PostMapping(value = "/login")
    public ResponseEntity obtenerUsuario(@RequestBody UserDTO userDTO){
        logger.info("Detectada peticion para obtener un administrador o trabajador con email " + userDTO.getEmail());

        ServicioUsuario servicioUsuario = new ServicioUsuario(adminRepositoryImp,trabajadorRepositoryImp);

        if(servicioUsuario.comprobarUsuario(userDTO.getEmail(), userDTO.getPassword())){
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/rol/{email:.*}", produces = "application/json")
    public @ResponseBody String obtenerRol(@PathVariable String email){
        logger.info("Detectada peticion para obtener el rol del usuario con email " + email);

        ServicioUsuario servicioUsuario = new ServicioUsuario(adminRepositoryImp,trabajadorRepositoryImp);

        return "{\"rol\": \"" + servicioUsuario.obtenerRol(email) + "\"}";
    }

    @PutMapping(value = "/crearTrabajador")
    public ResponseEntity crearTrabajador(@RequestBody TrabajadorDTO trabajadorDTO) {
        logger.info("Detectada peticion para crear un usuario trabajador");

        ServicioUsuario servicioUsuario = new ServicioUsuario(adminRepositoryImp,trabajadorRepositoryImp);

        if(servicioUsuario.crearTrabajador(trabajadorDTO.getEmailTrabajador(),trabajadorDTO.getPasswordTrabajador())){
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

        ServicioUsuario servicioUsuario = new ServicioUsuario(adminRepositoryImp,trabajadorRepositoryImp);

        if(servicioUsuario.crearAdministrador(administradorDTO.getEmailAdministrador(),administradorDTO.getPasswordAdministrador())){
            //Creacion correcta
            logger.info("Administrador con email " + administradorDTO.getEmailAdministrador() + " creado satisfactoriamente");
            return new ResponseEntity(HttpStatus.OK);
        } else {
            //Creacion fallida
            logger.severe("Error al crear administrador con email " + administradorDTO.getEmailAdministrador());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping(value = "/incidencias")
//    public @ResponseBody List<Incidencia> obtenerTodasIncidencias(){
//
//    }

}
