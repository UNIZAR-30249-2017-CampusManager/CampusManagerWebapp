package es.unizar.campusManager.aplicacion.servicios;

import es.unizar.campusManager.dominio.entidades.Administrador;
import es.unizar.campusManager.dominio.entidades.Trabajador;
import es.unizar.campusManager.dominio.repository.AdminRepository;
import es.unizar.campusManager.dominio.repository.TrabajadorRepository;

import java.util.logging.Logger;

public class ServicioUsuario {

    //Logging
    private final static Logger logger = Logger.getLogger(ServicioUsuario.class.getName());
    private AdminRepository adminRepository;
    private TrabajadorRepository trabajadorRepository;

    public ServicioUsuario(AdminRepository adminRepository, TrabajadorRepository trabajadorRepository) {
        this.adminRepository = adminRepository;
        this.trabajadorRepository = trabajadorRepository;
    }

    public boolean crearAdministrador(String email, String password, String adminEmail, String adminPassword){
        //Comprobamos antes de crear el trabajador que no hay ningun usuario ya sea admin o trabajador
        Administrador administrador = adminRepository.findByEmail(adminEmail);

        if(administrador != null && administrador.getPassword().equals(adminPassword)){
            if(obtenerRol(email).equals("")){
                if(this.adminRepository.findByEmail(email) == null){
                    // No existe trabajador, creo uno nuevo
                    logger.info("Creando nuevo administrador, no existe uno con email " + email);
                    return this.adminRepository.save(new Administrador(email,password));
                } else {
                    logger.severe("El administrador con email " + email + " ya existe");
                    return false;
                }
            } else {
                logger.severe("No se puede crear el administrador, ya existe un " + obtenerRol(email) + " con ese email");
                return false;
            }
        }else{
            logger.severe("Solo los administradores pueden crear usuarios");
            return false;
        }
    }

    public boolean crearTrabajador(String email, String password, String adminEmail, String adminPassword){
        //Comprobamos antes de crear el trabajador que no hay ningun usuario ya sea admin o trabajador
        Administrador administrador = adminRepository.findByEmail(adminEmail);
        if(administrador != null && administrador.getPassword().equals(adminPassword)){
            if(obtenerRol(email).equals("")){
                if(this.trabajadorRepository.findByEmail(email) == null){
                    // No existe trabajador, creo uno nuevo
                    logger.info("Creando nuevo trabajador, no existe uno con email " + email);
                    return this.trabajadorRepository.save(new Trabajador(email,password));
                } else {
                    logger.severe("El trabajador con email " + email + " ya existe");
                    return false;
                }
            } else {
                logger.severe("No se puede crear el trabajador, ya existe un " + obtenerRol(email) + " con ese email");
                return false;
            }
        }else{
            logger.severe("Solo los administradores pueden crear usuarios");
            return false;
        }
    }

    public boolean comprobarUsuario(String email, String password){
        logger.info("Comprobando si existe algun usuario con email " + email);

        Administrador administrador = adminRepository.findByEmail(email);
        if(administrador == null){
            Trabajador trabajador = trabajadorRepository.findByEmail(email);
            if(trabajador == null){
                logger.severe("No existe");
                return false;
            } else {
                logger.info("Existe, es trabajador, comprobando password");
                if(trabajador.getPassword().equals(password)){
                    logger.info("Password correcta");
                    return true;
                } else {
                    logger.severe("Password incorrecta");
                    return false;
                }
            }
        } else{
            logger.info("Existe, es admin, comprobando password");
            if(administrador.getPassword().equals(password)){
                logger.info("Password correcta");
                return true;
            } else {
                logger.severe("Password incorrecta");
                return false;
            }
        }
    }

    public String obtenerRol(String email){
        logger.info("Comprobando el rol del usuario con email " + email);
        if(adminRepository.findByEmail(email) == null){
            if(trabajadorRepository.findByEmail(email) == null){
                logger.severe("No existe el usuario");
                return "";
            } else {
                logger.info("Es un trabajador");
                return "trabajador";
            }
        } else {
            logger.info("Es un administrador");
            return "administrador";
        }
    }
}
