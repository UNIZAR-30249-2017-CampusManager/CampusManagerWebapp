package es.unizar.campusManager;

import es.unizar.campusManager.dominio.entidades.Espacio;
import es.unizar.campusManager.dominio.objetosValor.InformacionEspacio;
import es.unizar.campusManager.infraestructura.springData.EspacioRepositorySpring;
import es.unizar.campusManager.puertos.rest.EndpointREST;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest
public class EspacioTest {

    @Autowired
    private EspacioRepositorySpring espacioRepo;

    @Autowired
    private EndpointREST endpoint;

    private String idEspacio1;

    @Before
    public void inicializar() {
        espacioRepo.deleteAll();

        Espacio espacio = new Espacio(new InformacionEspacio("1","EspacioTest","EdificioTest"),
                false);
        idEspacio1 = espacio.getId();
        espacioRepo.save(espacio);
    }

    @After
    public void finalizar() {
        espacioRepo.deleteAll();
    }

    @Test
    public void crearEspacioReservableTest() {
        // Ejecución correcta
        ResponseEntity response = endpoint.crearEspacioReservable(idEspacio1);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Ejecución incorrecta (ya está almacenado el espacio como reservable)
        response = endpoint.crearEspacioReservable(idEspacio1);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
