package es.unizar.campusManager.controller;


import es.unizar.campusManager.model.CampusIncidence;
import es.unizar.campusManager.model.repository.IncidenceRepository;
import es.unizar.campusManager.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class IncidenceController {

    private final Logger log = Logger.getLogger(IncidenceController.class.getName());

    @Autowired
    private IncidenceRepository incidenceRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * Servicio REST que devuelve todas las incidencias de la base de datos
     */
    @GetMapping(value = "/incidencia",produces = "application/json")
    public @ResponseBody String getAllIncidencias() {
        log.info("Consultando todas las incidencias.");

        return incidenceRepository.findAll().toString();
    }

    /**
     * Servicio REST que devuelve la incidencia con id el especificado en el parámetro.
     */
    @GetMapping(value = "/incidencia/{id:.*}",produces = "application/json")
    public @ResponseBody String getIncidencia(@PathVariable String id) {
        log.info("Consultando incidencia.");

        return incidenceRepository.findById(Integer.parseInt(id)).toString();
    }

    /**
     * Servicio REST que devuelve todas las incidencias del worker especificado en el parámetro.
     */
    @GetMapping(value = "/incidencia/worker/{workerEmail:.*}",produces = "application/json")
    public @ResponseBody String getWorkerIncidencias(@PathVariable String workerEmail) {
        log.info("Consultando las incidencias del trabajador " + workerEmail +".");
        if (userRepository.findByEmail(workerEmail) == null) {
            log.info("Error, no existe el trabajador " + workerEmail +".");
            return "{}";
        }
        log.info("Devueltas las incidencias del trabajador " + workerEmail +".");
        return incidenceRepository.findByWorkerEmail(workerEmail).toString();
    }

    /**
     * Servicio REST que devuelve todas las incidencias con estado especificado en el parámetro.
     */
    @GetMapping(value = "/incidencia/estado/{status:.*}",produces = "application/json")
    public @ResponseBody String getStatusIncidencias(@PathVariable String status) {
        log.info("Consultando las incidencias con estado " + status +".");

        return incidenceRepository.findByStatus(status).toString();
    }

    /**
     * Añade una nueva incidencia con estado "UNASSIGNED", workerEmail=null y la fecha actual
     * a la base de datos.
     */
    @PostMapping(value = "/incidencia")
    public ResponseEntity<?> addIncidencia(@RequestBody CampusIncidence incidence) {
        log.info("Añadiendo nueva incidencia");

        CampusIncidence campusIncidence = new CampusIncidence(incidence.getName(), incidence.getDescription(),
                incidence.getPlace());
        incidenceRepository.save(campusIncidence);

        log.info("Incidencia \"" + campusIncidence.getName() + "\" en \"" + campusIncidence.getPlace() +
                "\" añadida correctamente");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
