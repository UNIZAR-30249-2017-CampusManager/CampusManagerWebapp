package es.unizar.campusManager.controller;


import es.unizar.campusManager.model.CampusIncidence;
import es.unizar.campusManager.model.CampusUser;
import es.unizar.campusManager.model.repository.IncidenceRepository;
import es.unizar.campusManager.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public @ResponseBody List<CampusIncidence> getAllIncidencias() {
        log.info("Consultando todas las incidencias.");

        return incidenceRepository.findAll();
    }

    /**
     * Servicio REST que devuelve la incidencia con id el especificado en el parámetro.
     */
    @GetMapping(value = "/incidencia/{id:.*}",produces = "application/json")
    public @ResponseBody String getIncidencia(@PathVariable String id) {
        log.info("Consultando incidencia.");

        CampusIncidence incidencia = incidenceRepository.findById(Integer.parseInt(id));
        if (incidencia == null) {
            return "{}";
        } else {
            return incidencia.toString();
        }
    }

    /**
     * Servicio REST que devuelve todas las incidencias del worker especificado en el parámetro.
     */
    @GetMapping(value = "/incidencia/worker/{workerEmail:.*}",produces = "application/json")
    public @ResponseBody List<CampusIncidence> getWorkerIncidencias(@PathVariable String workerEmail) {
        log.info("Consultando las incidencias del trabajador " + workerEmail +".");
        if (userRepository.findByEmail(workerEmail) == null) {
            log.info("Error, no existe el trabajador " + workerEmail +".");
        } else {
            log.info("Devueltas las incidencias del trabajador " + workerEmail +".");
        }
        return incidenceRepository.findByWorkerEmail(workerEmail);
    }

    /**
     * Servicio REST que devuelve todas las incidencias con estado especificado en el parámetro.
     */
    @GetMapping(value = "/incidencia/estado/{status:.*}",produces = "application/json")
    public @ResponseBody List<CampusIncidence> getStatusIncidencias(@PathVariable String status) {
        log.info("Consultando las incidencias con estado " + status +".");

        return incidenceRepository.findByStatus(status);
    }

    /**
     * Añade una nueva incidencia con estado "UNASSIGNED", workerEmail=null y la fecha actual
     * a la base de datos.
     */
    @PostMapping(value = "/incidencia")
    public ResponseEntity<?> addIncidencia(@RequestBody CampusIncidence incidence) {
        log.info("Añadiendo nueva incidencia");

        String name = incidence.getName();
        String description = incidence.getDescription();
        String place = incidence.getPlace();
        String building = incidence.getBuilding();

        if (name == null || name.trim().equals("") || description == null || description.trim().equals("")
                || place == null || place.trim().equals("") || building == null || building.trim().equals("")) {
            log.info("Error al añadir la nueva incidencia: parámetro erróneo.");

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            CampusIncidence campusIncidence = new CampusIncidence(name, description, place, building);
            incidenceRepository.save(campusIncidence);

            log.info("Incidencia \"" + name + "\" en \"" + place + " " + building +
                    "\" añadida correctamente");

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /**
     * Modifica el estado y workerEmail de la incidencia con id pasado en la url.
     */
    @PutMapping(value = "/incidencia/{id:.*}")
    public ResponseEntity<?> modEstadoIncidencia(@PathVariable String id, @RequestBody CampusIncidence incidence) {

        log.info("Modificando incidencia");

        String status = incidence.getStatus();
        String workerEmail = incidence.getWorkerEmail();
        int requestId = incidence.getRequestId();

        if (!status.equals("UNASSIGNED") && !status.equals("ASSIGNED") && !status.equals("INPROGRESS") &&
                !status.equals("INVALID") && !status.equals("FINALIZED")) {
            log.info("Error al añadir la nueva incidencia: parámetro erróneo.");

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            incidence = incidenceRepository.findById(Integer.parseInt(id));
            CampusUser user = userRepository.findByEmail(workerEmail);

            if (incidence == null || user == null) {
                log.info("Error al añadir la nueva incidencia: no existe worker o incidencia.");

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                incidence.setStatus(status);
                incidence.setWorkerEmail(workerEmail);
                incidence.setRequestId(requestId);

                incidenceRepository.save(incidence);
                log.info("Incidencia actualizada correctamente");

                return new ResponseEntity<>(incidence, HttpStatus.OK);
            }
        }
    }

    /**
     * Servicio REST que elimina la incidencia con id pasado como parámetro. Devuelve OK en ese caso.
     * NOT_FOUND si no existe incidencia con ese id.
     */
    @DeleteMapping(value = "/incidencia/{id:.*}")
    public ResponseEntity<?> deleteIncidence (@PathVariable String id) {
        log.info("Eliminando incidencia");

        CampusIncidence incidence = incidenceRepository.findById(Integer.parseInt(id));

        if (incidence == null) {
            log.info("Error al eliminar la incidencia: no existe incidencia con ese id.");

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            incidenceRepository.delete(incidence);
            log.info("Incidencia con id: " + id + " eliminada correctamente.");

            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
