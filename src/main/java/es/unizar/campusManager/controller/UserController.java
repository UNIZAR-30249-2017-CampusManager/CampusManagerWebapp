package es.unizar.campusManager.controller;

import es.unizar.campusManager.model.CampusUser;
import es.unizar.campusManager.model.repository.UserRepository;
import es.unizar.campusManager.model.service.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;



@RestController
public class UserController {

    @Autowired
    private UserRepository userRepo;



    /**
     * Obtiene un usuario del formulario html e intenta registrarlo
     * @param user usuario a registrar (En JSON)
     * @return codigo 200 junto con los datos del usuario si el registro es correcto
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody CampusUser user){

        System.out.println("Detectada peticion para crear el usuario " + user.getEmail());

        if(userRepo.findByEmail(user.getEmail())==null){
            Password pw = new Password();

            //ciframos la password del usuario
            try {

                CampusUser newUser = new CampusUser(user.getEmail(), pw.generatePassword(user.getPassword()),
                        user.getName(), user.getSurname(), user.getRole());
                userRepo.save(newUser);
                return new ResponseEntity<>(newUser,HttpStatus.OK);

            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }



    /**
     * Devulve la informacion de todos los usuarios
     * @param
     * @return codigo 200 junto con los datos de los usuarios si el registro es correcto
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(){
        System.out.println("Detectada peticion para obtener los datos de los usuarios");

        return new ResponseEntity<>(userRepo.findAll(),HttpStatus.OK);
    }


    /**
     * Devuelve la información de un usuario
     * @param email usuario a devolver
     * @return codigo 200 si el registro es correcto
     */
    @RequestMapping(value = "/user/{email:.*}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable String email){
        System.out.println("Detectada peticion para obtener los datos del usuario " + email);

        CampusUser user = userRepo.findByEmail(email);

        return new ResponseEntity<>(user,HttpStatus.OK);

    }



    /**
     * Borra un usuario de la base de datos
     * @param email  email del usuario a borrar
     * @return codigo 200 si la eliminación es correcta
     */
    @RequestMapping(value = "/user/{email:.*}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable String email){

        System.out.println("Detectada peticion para borrar los datos del usuario " + email);
        try{
            userRepo.delete(email);
            return new ResponseEntity<>("{\"message\":\"User deleted\"}",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Usuario no encontrado",HttpStatus.BAD_REQUEST);
        }

    }


    /**
     * Obtiene los datos del usuario al cual modificar sus datos personales
     * y lo cambia en la base de datos
     * @param user  datos a cambiar (En JSON)
     * @return codigo 200, junto con los nuevos datos del usuario si la modificacion es correcta
     */
    @RequestMapping(value = "/user/{email:.*}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody CampusUser user){

        System.out.println("Detectada peticion para modificar datos del usuario " + user.getEmail());
        CampusUser newUser = userRepo.findByEmail(user.getEmail());

        if(newUser!=null){
            Password pw = new Password();
            try {
                //String password = user.getPassword();
                if (!user.getPassword().equals("") && !pw.isPasswordValid(user.getPassword(), newUser.getPassword())) {
                    // La contraseña ha cambiado
                    newUser.setPassword(pw.generatePassword(user.getPassword()));
                }

                newUser.setName(user.getName());
                newUser.setSurname(user.getSurname());
                userRepo.save(newUser);
                return new ResponseEntity<>(newUser,HttpStatus.OK);
            } catch (Exception e) {
                //e.printStackTrace();
                System.err.println("Error al generar password cifrada del usuario " + user.getPassword());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
