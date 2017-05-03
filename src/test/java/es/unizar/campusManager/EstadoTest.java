package es.unizar.campusManager;


import es.unizar.campusManager.dominio.entidades.Espacio;
import es.unizar.campusManager.dominio.entidades.Incidencia;
import es.unizar.campusManager.dominio.objetosValor.InformacionEspacio;
import es.unizar.campusManager.dominio.repository.EspacioRepository;
import es.unizar.campusManager.infraestructura.DTO.NuevoEstadoIncidenciaDTO;
import es.unizar.campusManager.infraestructura.springData.EspacioRepositorySpring;
import es.unizar.campusManager.infraestructura.springData.IncidenciaRepositorySpring;
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
public class EstadoTest {

    @Autowired
    IncidenciaRepositorySpring incidenciaRep;
    @Autowired
    EspacioRepositorySpring espacioRep;

    @Autowired
    private EndpointREST endpoint;



    String idEspacio;

    String idIncidencia1;
    String idIncidencia2;

    int grupo = 1;

    @Before
    public void crear(){


        Espacio espacio = new Espacio(new InformacionEspacio("1","cafeteria","Ada Byron"),false);

        espacioRep.save(espacio);
        idEspacio = espacio.getId();


        Incidencia inc = new Incidencia("Incidencia1","No funciona","03/04/2017",espacio);

        inc.setGrupo(grupo);
        inc.setEstado("Asignada");
        incidenciaRep.save(inc);

        idIncidencia1 = inc.getId();

        inc = new Incidencia("Incidencia2","No va","03/04/2017",espacio);

        inc.setGrupo(grupo);
        inc.setEstado("Asignada");
        incidenciaRep.save(inc);


        idIncidencia2 = inc.getId();

    }

    @After
    public void borrar(){
        incidenciaRep.delete(idIncidencia1);
        incidenciaRep.delete(idIncidencia2);
        espacioRep.delete(idEspacio);

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
