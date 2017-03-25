package es.unizar.campusManager;

import es.unizar.campusManager.controller.UserController;
import es.unizar.campusManager.model.CampusUser;
import es.unizar.campusManager.model.repository.UserRepository;
import es.unizar.campusManager.model.service.Password;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import org.json.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {


    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserController userCont;


    @Before
    public void init() {
        if (userRepo.exists("pepe@gmail.com"))
            userRepo.delete("pepe@gmail.com");
    }


    @Test
    public void getUsers() throws Exception {

        //Peticion a user que devuelve OK


        String content = userCont.getAll();
        JSONArray users = new JSONArray(content);


        //Peticion a user que devuelve OK
        content = userCont.getAll();
        JSONArray users2 = new JSONArray(content);


        //El numero de usuarios no ha cambiado
        assertEquals(users.length(),users2.length());

    }



    @Test
    public void createUser() throws Exception {


        CampusUser user = new CampusUser("pepe@gmail.com","pepe","pepe","pepe","admin");


        /**--------Crear un nuevo usuario------------*/


        //numero de usuarios iniciales

        long initLength = userRepo.count();



        //Crear un nuevo usuario correctamente
        ResponseEntity result = userCont.create(user);
        assertEquals(result.getStatusCode(), HttpStatus.CREATED);



        //el numero de usuarios a aumentado en uno
        assertEquals(initLength + 1 ,userRepo.count());



        /**--------Crear un nuevo usuario que ya existe------------*/

        //Se envia peticion para registrar un usuario, pero como ya existe devuelve BAD_REQUEST
        result = userCont.create(user);
        assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);



        //el numero de usuarios no ha cambiado
        assertEquals(initLength + 1  ,userRepo.count());





        /**--------Crear un nuevo usuario con datos erroneos ------------*/


        CampusUser user2 = new CampusUser("pepe@gmail.com","","","","");

        //crear usuario sin nombre
        user.setName("");
        //Se envia peticion para registrar un usuario, pero devuelve BAD_REQUEST y mensaje
        result = userCont.create(user);
        assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(result.getBody().toString(), "Datos incorrectos");

        //el numero de usuarios no ha cambiado
        assertEquals(initLength + 1  ,userRepo.count());


    }


    @Test
    public void getUser() throws Exception {


        /**--------Obtener un usuario del sistema ------------*/

        CampusUser user = new CampusUser("pepe@gmail.com", "pepe", "pepe", "pepe", "admin");

        userRepo.save(user);

        //Peticion a user
        String content = userCont.get("pepe@gmail.com");

        JSONObject userRet = new JSONObject(content);


        assertEquals(userRet.get("email"),user.getEmail());
        assertEquals(userRet.get("name"),user.getName());
        assertEquals(userRet.get("surname"),user.getSurname());
        assertEquals(userRet.get("role"),user.getRole().toUpperCase());




        /**--------Obtener un usuario que no existe ------------*/


        //Peticion a user que devuelve nada
        assertEquals("{}", userCont.get("dontexists@gmail.com"));


    }


    @Test
    public void deleteUser() throws Exception {


        /**--------Eliminar un usuario del sistema ------------*/

        CampusUser user = new CampusUser("pepe@gmail.com", "pepe", "pepe", "pepe", "admin");

        userRepo.save(user);

        //Peticion a user que devuelve OK
        ResponseEntity result = userCont.delete("pepe@gmail.com");
        assertEquals(result.getStatusCode(), HttpStatus.OK);

        /**--------Eliminar un usuario que no existe ------------*/


        //Peticion a user que devuelve BAD_REQUEST
        result = userCont.delete("dontexists@gmail.com");
        assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);

    }

    @Test
    public void updateUser() throws Exception {


        /**--------Modificar un usuario del sistema ------------*/

        Password pw = new Password();
        CampusUser user = new CampusUser("pepe@gmail.com", pw.generatePassword("pepe"), "pepe", "pepe", "admin");

        userRepo.save(user);


        user.setName("pepe2");
        user.setPassword("password");
        user.setSurname("perez");


        //Peticion a para modificar un usuario

        ResponseEntity result = userCont.update(user);
        assertEquals(result.getStatusCode(), HttpStatus.OK);



        CampusUser stored = userRepo.findByEmail("pepe@gmail.com");

        assertEquals(stored.getEmail(),user.getEmail());
        assertEquals(stored.getName(),user.getName());
        assertEquals(stored.getSurname(),user.getSurname());
        assertEquals(stored.getRole(),user.getRole().toUpperCase());




        /**--------Modificar un usuario que no existe del sistema ------------*/

        user.setEmail("dontExists@gmail.com");

        result = userCont.update(user);
        assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);




        /**--------Modificar un usuario Con datos erroneos ------------*/
        user.setName("");
        user.setPassword("");
        user.setSurname("");


        //Peticion a para modificar un usuario

        user.setEmail("pepe@gmail.com");

        result = userCont.update(user);
        assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);


        stored = userRepo.findByEmail("pepe@gmail.com");



        //Los datos no han cambiado
        assertEquals(stored.getName(),"pepe2");
        assertEquals(stored.getSurname(),"perez");

    }


}
