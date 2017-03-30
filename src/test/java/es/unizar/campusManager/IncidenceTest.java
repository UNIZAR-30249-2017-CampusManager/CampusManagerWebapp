package es.unizar.campusManager;

import es.unizar.campusManager.controller.IncidenceController;
import es.unizar.campusManager.model.CampusIncidence;
import es.unizar.campusManager.model.CampusUser;
import es.unizar.campusManager.model.repository.IncidenceRepository;
import es.unizar.campusManager.model.repository.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest
public class IncidenceTest {

    @Autowired
    private IncidenceRepository incidenceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IncidenceController controller;

    @Before
    public void before () {
        incidenceRepository.deleteAll();

    }

    @Test
    public void addIncidenciaTest () {
        int cantidad = incidenceRepository.findAll().size();

        CampusIncidence incidence = new CampusIncidence("inciTest", "Esto es una incidencia test",
                "Aula 1.14", "Ada Byron");
        assertEquals(new ResponseEntity<>(HttpStatus.CREATED), controller.addIncidencia(incidence));

        assertEquals(cantidad + 1, incidenceRepository.findAll().size());
    }

    @Test
    public void addIncidenciaTestErr () {
        CampusIncidence incidence = new CampusIncidence("inciTest2", "Esto es una incidencia test2",
                "", "Ada Byront");
        assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST), controller.addIncidencia(incidence));
    }

    @Test
    public void getAllIncidenciasTest () {
        int cantidad = incidenceRepository.findAll().size();

        CampusIncidence incidence = new CampusIncidence("inciTest3", "Esto es una incidencia test3",
                "Aula 1.14", "Ada Byron");
        CampusIncidence incidence1 = new CampusIncidence("inciTest3.1", "Esto es una incidencia test3",
                "Aula 1.14", "Ada Byron");
        CampusIncidence incidence2 = new CampusIncidence("inciTest3.2", "Esto es una incidencia test3",
                "Aula 1.14", "Ada Byron");

        incidenceRepository.save(incidence);
        incidenceRepository.save(incidence1);
        incidenceRepository.save(incidence2);

        assertEquals(cantidad + 3, controller.getAllIncidencias().size());

    }

    @Test
    public void getIncidenciaByIdTest () throws JSONException {
        CampusIncidence incidence = new CampusIncidence("inciTest4", "Esto es una incidencia test4",
                "Aula 1.14", "Ada Byron");
        incidenceRepository.save(incidence);

        JSONObject response = new JSONObject(controller.getIncidencia("" + incidence.getId()));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");

        assertEquals(incidence.getName(), response.get("name"));
        assertEquals(sdf.format(incidence.getFecha()), response.get("fecha"));
    }

    @Test
    public void getIncidenciasByWorkerTest () throws JSONException {
        CampusIncidence incidence = new CampusIncidence("inciTest5", "Esto es una incidencia test5",
                "Aula 1.14", "Ada Byron");
        CampusIncidence incidence1 = new CampusIncidence("inciTest5.1", "Esto es una incidencia test5",
                "Aula 1.14", "Ada Byron");
        CampusIncidence incidence2 = new CampusIncidence("inciTest5.2", "Esto es una incidencia test5",
                "Aula 1.14", "Ada Byron");

        incidenceRepository.save(incidence);
        incidenceRepository.save(incidence1);
        incidence2.setWorkerEmail("worker@test.com");
        incidenceRepository.save(incidence2);

        assertEquals("inciTest5.2", controller.getWorkerIncidencias("worker@test.com").get(0).getName());
    }

    @Test
    public void getIncidenciasByStatusTest () throws JSONException {
        CampusIncidence incidence = new CampusIncidence("inciTest6", "Esto es una incidencia test6",
                "Aula 1.14", "Ada Byron");
        CampusIncidence incidence1 = new CampusIncidence("inciTest6.1", "Esto es una incidencia test6",
                "Aula 1.14", "Ada Byron");
        CampusIncidence incidence2 = new CampusIncidence("inciTest6.2", "Esto es una incidencia test6",
                "Aula 1.14", "Ada Byron");

        incidenceRepository.save(incidence);
        incidence1.setStatus("FINALIZED");
        incidenceRepository.save(incidence1);
        incidenceRepository.save(incidence2);

        assertEquals("inciTest6.1", controller.getStatusIncidencias("FINALIZED").get(0).getName());
    }

    @Test
    public void modIncidencia() {
        userRepository.save(new CampusUser("worker@test.com", "1234", "Worker", "test",
                "WORKER"));
        CampusIncidence incidence = new CampusIncidence("inciTest7", "Esto es una incidencia test7",
                "Aula 1.14", "Ada Byron");

        incidenceRepository.save(incidence);

        CampusIncidence incidence2 = new CampusIncidence(null, null, null, null,
                "ASSIGNED", "worker@test.com");

        assertEquals(HttpStatus.OK, controller.modEstadoIncidencia("" + incidence.getId(),
                incidence2).getStatusCode());

        incidence = incidenceRepository.findById(incidence.getId());
        assertEquals("ASSIGNED", incidence.getStatus());
        assertEquals("worker@test.com", incidence.getWorkerEmail());
    }

    @Test
    public void modIncidenciaParamErr() {
        userRepository.save(new CampusUser("worker@test.com", "1234", "Worker", "test",
                "WORKER"));
        CampusIncidence incidence = new CampusIncidence("inciTest8", "Esto es una incidencia test8",
                "Aula 1.14", "Ada Byron");

        incidenceRepository.save(incidence);

        CampusIncidence incidence2 = new CampusIncidence(null, null, null, null,
                "NOEXISTEROL", "worker@test.com");

        assertEquals(HttpStatus.BAD_REQUEST, controller.modEstadoIncidencia("" + incidence.getId(),
                incidence2).getStatusCode());
    }

    @Test
    public void modIncidenciaErr() {
        userRepository.save(new CampusUser("worker@test.com", "1234", "Worker", "test",
                "WORKER"));
        CampusIncidence incidence = new CampusIncidence("inciTest9", "Esto es una incidencia test9",
                "Aula 1.14", "Ada Byron");

        incidenceRepository.save(incidence);

        CampusIncidence incidence2 = new CampusIncidence(null, null, null, null,
                "ASSIGNED", "error@test.com");

        assertEquals(HttpStatus.NOT_FOUND, controller.modEstadoIncidencia("" + incidence.getId(),
                incidence2).getStatusCode());
    }

    @Test
    public void deleteIncidencia() {
        int cantidad = incidenceRepository.findAll().size();
        CampusIncidence incidence = new CampusIncidence("inciTest10", "Esto es una incidencia test10",
                "Aula 1.14", "Ada Byron");

        incidenceRepository.save(incidence);
        cantidad++;
        assertEquals(cantidad, incidenceRepository.findAll().size());

        assertEquals(new ResponseEntity<>(HttpStatus.OK), controller.deleteIncidence("" + incidence.getId()));
        assertEquals(cantidad - 1, incidenceRepository.findAll().size());
    }

    @Test
    public void deleteIncidenciaErr() {
        CampusIncidence incidence = new CampusIncidence("inciTest11", "Esto es una incidencia test11",
                "Aula 1.14", "Ada Byron");
        incidenceRepository.save(incidence);

        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND), controller.deleteIncidence("" + incidence.getId() + 1));
    }
}
