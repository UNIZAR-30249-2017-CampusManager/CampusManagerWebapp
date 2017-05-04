package es.unizar.campusManager;

import es.unizar.campusManager.dominio.entidades.Edificio;
import es.unizar.campusManager.dominio.objetosValor.MenuCafeteria;
import es.unizar.campusManager.infraestructura.DTO.EdificioDTO;
import es.unizar.campusManager.infraestructura.springData.EdificioRepositorySpring;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest
public class EdificioTest {


    @Autowired
    EdificioRepositorySpring edificioRep;

    @Autowired
    private EndpointREST endpoint;



    String idEdificio;



    @Before
    public void crear(){

        List<String> l = new <String>ArrayList();
        l.add("Julio");
        l.add("Agosto");

        MenuCafeteria menu = new MenuCafeteria("fideua","fideua","fideua","fideua","fideua");


        Edificio edificio = new Edificio("Ada Byron","8:00","22:00",l, menu);

        edificioRep.save(edificio);

        idEdificio = edificio.getId();


    }

    @After
    public void borrar(){
        edificioRep.delete(idEdificio);
    }



    @Test
    public void verInfoEdificios() throws Exception {
        List<Edificio> l = endpoint.obtenerEdificios();
        assertEquals(1,l.size());

        assertEquals("Ada Byron",l.get(0).getNombre());
        assertEquals("8:00",l.get(0).getHoraApertura());
        assertEquals("22:00",l.get(0).getHoraCierre());
        assertEquals("fideua",l.get(0).getMenuCafeteria().getLunes());

    }

    @Test
    public void verInfoEdificio() throws Exception {


        ResponseEntity response = (ResponseEntity) endpoint.obtenerEdificioNombre("asdfasdf");
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());


        Edificio edificio = (Edificio) endpoint.obtenerEdificioNombre("Ada Byron");

        assertEquals("Ada Byron",edificio.getNombre());
        assertEquals("8:00",edificio.getHoraApertura());
        assertEquals("22:00",edificio.getHoraCierre());
        assertEquals("fideua",edificio.getMenuCafeteria().getLunes());

    }

    @Test
    public void modInfoEdificioTest () throws Exception {
        List<String> list = new ArrayList<>();
        list.add("Diciembre");

        MenuCafeteria menu = new MenuCafeteria("Paella","Albóndigas","Judías","Sopa","Cerveza");

        EdificioDTO nuevo = new EdificioDTO("10:00", "19:00", list, menu);
        ResponseEntity response = endpoint.actualizarInformacionEdificio("Ada Byron", nuevo);

        Edificio edificio = (Edificio) endpoint.obtenerEdificioNombre("Ada Byron");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Ada Byron", edificio.getNombre());
        assertEquals("10:00", edificio.getHoraApertura());
        assertEquals("19:00", edificio.getHoraCierre());
        assertEquals("Diciembre", edificio.getMesesCerrado().get(0));
        assertEquals("Cerveza", edificio.getMenuCafeteria().getViernes());
    }

    @Test
    public void modInfoEdificioErrTest () throws Exception {
        List<String> list = new ArrayList<>();
        list.add("Diciembre");

        MenuCafeteria menu = new MenuCafeteria("Paella","Albóndigas","Judías","Sopa","Cerveza");

        EdificioDTO nuevo = new EdificioDTO("10:00", "19:00", list, menu);
        ResponseEntity response = endpoint.actualizarInformacionEdificio("Byron", nuevo);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
