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
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    /**
     * Devulve la informacion de todos los usuarios
     *
     * @param
     * @return codigo 200 junto con los datos de los usuarios si el registro es correcto
     */
    @GetMapping(value = "/user",produces = "application/json")
    public @ResponseBody List<CampusUser> getAll() {
        System.out.println("Detectada peticion para obtener los datos de los usuarios");

        return userRepo.findAll();
    }

    /**
     * Devuelve la información de un usuario
     *
     * @param email usuario a devolver
     * @return codigo 200 si el registro es correcto
     */
    @GetMapping(value = "/user/{email:.*}",produces = "application/json")
    public @ResponseBody String get(@PathVariable String email) {
        System.out.println("Detectada peticion para obtener los datos del usuario " + email);

        CampusUser user = userRepo.findByEmail(email);

        if(user != null){
            return user.toString();
        } else{
            System.err.println("Error al obtener el usuario cuyo email es " + email);
            return "{}";
        }
    }

    /**
     * Obtiene un usuario del formulario html e intenta registrarlo
     *
     * @param user usuario a registrar (En JSON)
     * @return codigo 200 junto con los datos del usuario si el registro es correcto
     */
    @PostMapping(value = "/user")
    public ResponseEntity<?> create(@RequestBody CampusUser user) {


        //Comprobacion de valores
        if (user.getEmail() == null || user.getEmail().equals("") |
                user.getPassword() == null || user.getPassword().equals("") |
                user.getName() == null || user.getName().equals("") |
                user.getSurname() == null || user.getSurname().equals("") |
                user.getRole() == null ||
                !(user.getRole().toUpperCase().equals("ADMIN") |
                        user.getRole().toUpperCase().equals("WORKER") |
                        user.getRole().toUpperCase().equals("PROFFESOR"))) {

            return new ResponseEntity<>("Datos incorrectos",HttpStatus.BAD_REQUEST);

        } else {
            System.out.println("Detectada peticion para crear el usuario " + user.getEmail());


            if (userRepo.findByEmail(user.getEmail()) == null) {

                CampusUser newUser = new CampusUser(user.getEmail(), user.getPassword(),
                        user.getName(), user.getSurname(), user.getRole());
                userRepo.save(newUser);

                return new ResponseEntity<>(HttpStatus.CREATED);

                //Descomentar cuando pasemos a utilizar Spring Security
//            Password pw = new Password();
//
//            //ciframos la password del usuario
//            try {
//
//                CampusUser newUser = new CampusUser(user.getEmail(), pw.generatePassword(user.getPassword()),
//                        user.getName(), user.getSurname(), user.getRole());
//                userRepo.save(newUser);
//                return new ResponseEntity<>(newUser, HttpStatus.OK);
//
//            } catch (Exception e) {
//                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }

    }

    /**
     * Borra un usuario de la base de datos
     *
     * @param email email del usuario a borrar
     * @return codigo 200 si la eliminación es correcta
     */
    @DeleteMapping(value = "/user/{email:.*}")
    public ResponseEntity<?> delete(@PathVariable String email) {

        System.out.println("Detectada peticion para borrar los datos del usuario " + email);
        try {
            userRepo.delete(email);
            return new ResponseEntity<>("{\"message\":\"User deleted\"}", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Obtiene los datos del usuario al cual modificar sus datos personales
     * y lo cambia en la base de datos
     *
     * @param user datos a cambiar (En JSON)
     * @return codigo 200, junto con los nuevos datos del usuario si la modificacion es correcta
     */
    @PutMapping(value = "/user/{email:.*}")
    public ResponseEntity<?> update(@RequestBody CampusUser user) {


        //Comprobacion de valores
        if (user.getPassword() == null ||
                user.getName() == null || user.getName().equals("") |
                user.getSurname() == null || user.getSurname().equals("")) {

            return new ResponseEntity<>("Datos incorrectos",HttpStatus.BAD_REQUEST);

        } else {

            System.out.println("Detectada peticion para modificar datos del usuario " + user.getEmail());

            CampusUser newUser = userRepo.findByEmail(user.getEmail());


            if (newUser != null) {
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
                    return new ResponseEntity<>(newUser, HttpStatus.OK);
                } catch (Exception e) {
                    //e.printStackTrace();
                    System.err.println("Error al generar password cifrada del usuario " + user.getPassword());
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }
}
