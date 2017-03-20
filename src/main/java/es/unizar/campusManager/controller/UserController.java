package es.unizar.campusManager.controller;

import es.unizar.campusManager.model.CampusUser;
import es.unizar.campusManager.model.repository.UserRepository;
import es.unizar.campusManager.model.service.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepo;



    /**
     * Obtiene un usuario del formulario html e intenta registrarlo
     * @param email,password,name,surname,role usuario a registrar
     * @return codigo 200 si el registro es correcto
     */
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ResponseEntity<?> signIn(@RequestParam("email") String email, @RequestParam("password") String password,
                                    @RequestParam("name") String name, @RequestParam("surname") String surname,
                                    @RequestParam("role") String role, HttpServletRequest request){

        CampusUser admin = (CampusUser) request.getSession().getAttribute("user");

        if(admin != null && admin.isAdmin()){
            Password pw = new Password();

            //ciframos la password del usuario
            try {

                CampusUser user = new CampusUser(email, pw.generatePassword(password), name, surname, role);
                userRepo.save(user);
                return new ResponseEntity<String>(HttpStatus.OK);

            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (IllegalArgumentException e){
                return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
            }


        }else{
            return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
        }

    }


    /**
     * Autentica un usuario si existe e ingresa la password adecuada
     * @param email email con el que se quiere hacer login
     * @param password password con el que se quiere hacer login
     * @param request objeto request del usuario
     * @return codigo 200 si la autenticacion es correcta
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestParam("email") String email,
                                   @RequestParam("password") String password,
                                   HttpServletRequest request){
        System.out.println("Detectada peticion para que el usuario " + email + " haga login");

        Password pw = new Password();

        CampusUser user = userRepo.findByEmail(email);


        if(user != null){
            try {
                if(pw.isPasswordValid(password,user.getPassword())){
                    System.out.println("Password valida");
                    request.getSession().setAttribute("user", user);
                    return new ResponseEntity<String>(HttpStatus.OK);
                } else{
                    System.out.println("Password no valida");
                    return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
                }
            } catch (Exception e) {
                System.err.println("Error al comprobar la password del usuario " + user.getEmail());
                return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else{
            System.err.println("Error al obtener el usuario cuyo email es " + email);

            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

    }


    /**
     * Obtiene los datos del usuario al cual modificar sus datos personales
     * y lo cambia en la base de datos
     * @param password,name,surname  datos a cambiar
     * @return codigo 200 si la modificacion es correcta
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestParam("password") String password,
                                        @RequestParam("name") String name, @RequestParam("surname") String surname,
                                         HttpServletRequest request){
        CampusUser logueado = (CampusUser) request.getSession().getAttribute("user");
        if(logueado != null) {
            System.out.println("Detectada peticion para modificar datos del usuario " + logueado.getEmail());
            CampusUser user = userRepo.findByEmail(logueado.getEmail());

            if (user != null) {  //Obtenemos el usuario correctamente de la bbdd
                // Comprueba si ha cambiado la contraseña
                Password pw = new Password();
                try {
                    //String password = user.getPassword();
                    if (!password.equals("") && !pw.isPasswordValid(password, user.getPassword())) {
                        // La contraseña ha cambiado
                        user.setPassword(pw.generatePassword(user.getPassword()));
                    } else if (password.equals("")) {
                        // La contraseña no se quiere modificar
                        user.setPassword(password);
                    }

                    user.setName(name);
                    user.setSurname(surname);
                    userRepo.save(user);

                    //Actualizamos el usuario de la sesion con los nuevos datos
                    request.getSession().setAttribute("user", userRepo);

                    return new ResponseEntity<String>(HttpStatus.OK);
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                    System.err.println("Error al generar password cifrada del usuario " + user.getEmail());
                    return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                System.err.println("No se han podido obtener los datos del usuario " + logueado.getEmail());
                request.getSession().invalidate();
                return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
        }

    }

    /**
     * Tratamiento para cuando se pide hacer un logout
     * @param request request del usuario que hace la peticion
     * @return codigo 200 si se ha cerrado la sesion
     */
    @RequestMapping(value="/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        System.out.println("Me ha llegado peticion para logout");

        request.getSession().invalidate();

        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
