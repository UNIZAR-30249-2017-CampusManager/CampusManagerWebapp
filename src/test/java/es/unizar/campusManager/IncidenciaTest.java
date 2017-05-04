package es.unizar.campusManager;


import es.unizar.campusManager.dominio.entidades.Espacio;
import es.unizar.campusManager.dominio.entidades.Incidencia;
import es.unizar.campusManager.dominio.entidades.Trabajador;
import es.unizar.campusManager.dominio.objetosValor.InformacionEspacio;
import es.unizar.campusManager.infraestructura.DTO.AsignarIncidenciasDTO;
import es.unizar.campusManager.infraestructura.DTO.IncidenciaDTO;
import es.unizar.campusManager.infraestructura.DTO.NuevaIncidenciaDTO;
import es.unizar.campusManager.infraestructura.DTO.NuevoEstadoIncidenciaDTO;
import es.unizar.campusManager.infraestructura.springData.EspacioRepositorySpring;
import es.unizar.campusManager.infraestructura.springData.IncidenciaRepositorySpring;
import es.unizar.campusManager.infraestructura.springData.TrabajadorRepositorySpring;
import es.unizar.campusManager.puertos.rest.EndpointREST;
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
public class IncidenciaTest {

    @Autowired
    private IncidenciaRepositorySpring incidenciaRep;
    @Autowired
    private EspacioRepositorySpring espacioRep;
    @Autowired
    private TrabajadorRepositorySpring trabajadorRep;
    @Autowired
    private EndpointREST endpoint;

    private Espacio espacio;
    private String idIncidencia2;
    private int grupo = 1;

    @Before
    public void inicializar (){
        incidenciaRep.deleteAll();
        espacioRep.deleteAll();
        trabajadorRep.deleteAll();

        espacio = new Espacio(new InformacionEspacio("1","cafeteria","Ada Byron"),false);
        espacioRep.save(espacio);

        Trabajador trabajador = new Trabajador("test@trabajador.com", "1234");
        trabajadorRep.save(trabajador);

        Incidencia inc = new Incidencia("Incidencia1","No funciona","03/04/2017",espacio);
        inc.setGrupo(grupo);
        inc.setEstado("Asignada");
        incidenciaRep.save(inc);

        inc = new Incidencia("Incidencia2","No va","03/04/2017",espacio);
        inc.setGrupo(grupo);
        inc.setEstado("Asignada");
        incidenciaRep.save(inc);
        idIncidencia2 = inc.getId();

        inc = new Incidencia("Incidencia3","No va","03/04/2017", espacio);
        inc.setGrupo(2);
        inc.setEstado("Asignada");
        inc.setEmailTrabajador(trabajador.getEmail());
        incidenciaRep.save(inc);
    }

    @Test
    public void addIncidenciaTest () throws Exception {
        int cantidad = incidenciaRep.findAll().size();

        NuevaIncidenciaDTO incidenciaDTO = new NuevaIncidenciaDTO("ProblemaTest", "Esto es una " +
                "incidencia test", espacio.getInformacionEspacio().getIdEspacio());
        ResponseEntity response = endpoint.crearNuevaIncidencia(incidenciaDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cantidad + 1, incidenciaRep.findAll().size());
    }

    @Test
    public void addIncidenciaTestErr () throws Exception {
        NuevaIncidenciaDTO incidenciaDTO = new NuevaIncidenciaDTO("ProblemaTest", "Esto es una " +
                "incidencia test", "ID no valido");
        ResponseEntity response = endpoint.crearNuevaIncidencia(incidenciaDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void getAllIncidenciasTest () throws Exception {
        List<IncidenciaDTO> response = endpoint.obtenerTodasIncidencias();

        assertEquals(3, response.size());
    }

    @Test
    public void getIncidenciasTrabajadorTest () throws Exception {
        List<IncidenciaDTO> response = endpoint.obtenerIncidenciasTrabajador("test@trabajador.com");

        assertEquals(1, response.size());
        assertEquals("Incidencia3", response.get(0).getNombre());
    }

    @Test
    public void asigIncidenciaTest () throws Exception {
        Incidencia inc = new Incidencia("Incidencia4","No va","03/04/2017", espacio);
        incidenciaRep.save(inc);

        int cantidad = incidenciaRep.findByEmailTrabajador("test@trabajador.com").size();

        List<String> l = new ArrayList<>();
        l.add(inc.getId());
        AsignarIncidenciasDTO asignarIncidenciasDTO = new AsignarIncidenciasDTO("test@trabajador.com", l);
        ResponseEntity response = endpoint.asignarIncidencia(asignarIncidenciasDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cantidad + 1, incidenciaRep.findByEmailTrabajador("test@trabajador.com").size());
    }

    @Test
    public void asigIncidenciaTestErr () throws Exception {
        List<String> l = new ArrayList<>();
        l.add("err");
        AsignarIncidenciasDTO asignarIncidenciasDTO = new AsignarIncidenciasDTO("no@existe.com", l);
        ResponseEntity response = endpoint.asignarIncidencia(asignarIncidenciasDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void cambiarEstado() throws Exception {

        //Intentar cambiar de estado un grupo a un estado no valido
        ResponseEntity response = endpoint.cambiarEstadoIncidencia(grupo,new NuevoEstadoIncidenciaDTO("No valido"));

        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);



        //Intentar cambiar de estado un grupo que no existe
        response = endpoint.cambiarEstadoIncidencia(4,new NuevoEstadoIncidenciaDTO("En curso"));

        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);



        response = endpoint.cambiarEstadoIncidencia(grupo,new NuevoEstadoIncidenciaDTO("En curso"));

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        response = endpoint.cambiarEstadoIncidencia(grupo,new NuevoEstadoIncidenciaDTO("Finalizado"));

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        response = endpoint.cambiarEstadoIncidencia(grupo,new NuevoEstadoIncidenciaDTO("Invalido"));

        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }
}
