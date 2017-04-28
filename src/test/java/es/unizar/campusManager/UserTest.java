package es.unizar.campusManager;

import es.unizar.campusManager.aplicacion.servicios.ServicioUsuario;
import es.unizar.campusManager.dominio.entidades.Administrador;
import es.unizar.campusManager.dominio.entidades.Trabajador;
import es.unizar.campusManager.dominio.repository.AdminRepository;
import es.unizar.campusManager.dominio.repository.TrabajadorRepository;
import es.unizar.campusManager.infraestructura.DTO.AdministradorDTO;
import es.unizar.campusManager.infraestructura.DTO.TrabajadorDTO;
import es.unizar.campusManager.infraestructura.DTO.UserDTO;
import es.unizar.campusManager.infraestructura.springData.AdminRepositorySpring;
import es.unizar.campusManager.infraestructura.springData.TrabajadorRepositorySpring;
import es.unizar.campusManager.puertos.rest.EndpointREST;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import org.json.*;


@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest
public class UserTest {


    @Autowired
    private AdminRepositorySpring adminRep;

    @Autowired
    private TrabajadorRepositorySpring trabRep;


    @Autowired
    private EndpointREST enpoint;


    @Before
    public void init() {
        Administrador admin = adminRep.findByEmail("pepe@gmail.com");

        if(admin!=null){
            adminRep.delete(admin);
        }
        Trabajador trab = trabRep.findByEmail("pepe@gmail.com");

        if(trab!=null){
            trabRep.delete(trab);
        }
        admin = adminRep.findByEmail("admin@gmail.com");

        if(admin!=null){
            adminRep.delete(admin);
        }

    }



    @Test
    public void createAdmin() throws Exception {

        Administrador admin = new Administrador("admin@gmail.com", "admin");

        adminRep.save(admin);



        AdministradorDTO adminDTO = new AdministradorDTO("pepe@gmail.com","pepe","admin@gmail.com", "admin");

        /**--------Crear un nuevo usuario------------*/


        //numero de usuarios iniciales

        long initLength = adminRep.count();



        //Crear un nuevo usuario correctamente
        ResponseEntity result = enpoint.crearAdministrador(adminDTO);
        assertEquals(result.getStatusCode(), HttpStatus.OK);



        //el numero de usuarios a aumentado en uno
        assertEquals(initLength + 1 ,adminRep.count());



        /**--------Crear un nuevo usuario que ya existe------------*/

        //Se envia peticion para registrar un usuario, pero como ya existe devuelve BAD_REQUEST
        result = enpoint.crearAdministrador(adminDTO);
        assertEquals(result.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);



        //el numero de usuarios no ha cambiado
        assertEquals(initLength + 1  ,adminRep.count());





        /**--------Crear un nuevo usuario con datos erroneos ------------*/




        //crear usuario sin nombre
        adminDTO = new AdministradorDTO("","pepe","admin@gmail.com", "admin");

        //Se envia peticion para registrar un usuario, pero devuelve BAD_REQUEST y mensaje
        result = enpoint.crearAdministrador(adminDTO);
        assertEquals(result.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);

        //el numero de usuarios no ha cambiado
        assertEquals(initLength + 1  ,adminRep.count());


    }


    @Test
    public void createTrabajador() throws Exception {

        Administrador admin = new Administrador("admin@gmail.com", "admin");

        adminRep.save(admin);


        TrabajadorDTO trabDTO = new TrabajadorDTO("pepe@gmail.com","pepe","admin@gmail.com", "admin");

        /**--------Crear un nuevo usuario------------*/


        //numero de usuarios iniciales

        long initLength = trabRep.count();



        //Crear un nuevo usuario correctamente
        ResponseEntity result = enpoint.crearTrabajador(trabDTO);
        assertEquals(result.getStatusCode(), HttpStatus.OK);



        //el numero de usuarios a aumentado en uno
        assertEquals(initLength + 1 ,trabRep.count());



        /**--------Crear un nuevo usuario que ya existe------------*/

        //Se envia peticion para registrar un usuario, pero como ya existe devuelve BAD_REQUEST
        result = enpoint.crearTrabajador(trabDTO);
        assertEquals(result.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);



        //el numero de usuarios no ha cambiado
        assertEquals(initLength + 1  ,trabRep.count());





        /**--------Crear un nuevo usuario con datos erroneos ------------*/




        //crear usuario sin nombre
        trabDTO = new TrabajadorDTO("","pepe","admin@gmail.com", "admin");

        //Se envia peticion para registrar un usuario, pero devuelve BAD_REQUEST y mensaje
        result = enpoint.crearTrabajador(trabDTO);
        assertEquals(result.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);

        //el numero de usuarios no ha cambiado
        assertEquals(initLength + 1  ,trabRep.count());


    }




    @Test
    public void login() throws Exception {


        /**--------Obtener un usuario del sistema ------------*/

        UserDTO userDTO = new UserDTO("pepe@gmail.com", "pepe");


        ResponseEntity resp = enpoint.obtenerUsuario(userDTO);

        assertEquals(resp.getStatusCode(),HttpStatus.BAD_REQUEST);



        Administrador admin = new Administrador("pepe@gmail.com", "pepe");

        adminRep.save(admin);



        resp = enpoint.obtenerUsuario(userDTO);

        assertEquals(resp.getStatusCode(),HttpStatus.OK);



    }



    @Test
    public void obtenerRol(){

        //assertEquals(resp.getStatusCode(),HttpStatus.BAD_REQUEST);

        Administrador admin = new Administrador("pepe@gmail.com", "pepe");

        adminRep.save(admin);


        String resp = enpoint.obtenerRol("pepe@gmail.com");
        assertEquals(resp,"{\"rol\": \"administrador\"}");


    }




}
