package es.unizar.campusManager;

import es.unizar.campusManager.dominio.entidades.Edificio;
import es.unizar.campusManager.dominio.entidades.Espacio;
import es.unizar.campusManager.dominio.objetosValor.InformacionEspacio;
import es.unizar.campusManager.dominio.objetosValor.MenuCafeteria;
import es.unizar.campusManager.infraestructura.DTO.EdificioDTO;
import es.unizar.campusManager.infraestructura.DTO.ReservaDTO;
import es.unizar.campusManager.infraestructura.springData.EdificioRepositorySpring;
import es.unizar.campusManager.infraestructura.springData.EspacioRepositorySpring;
import es.unizar.campusManager.infraestructura.springData.ReservaRepositorySpring;
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
public class ReservaTest {


    @Autowired
    EspacioRepositorySpring espacioRep;

    @Autowired
    ReservaRepositorySpring reservaRep;

    @Autowired
    private EndpointREST endpoint;



    String idEspacio1;
    String idEspacio2;



    @Before
    public void crear(){


        Espacio espacio = new Espacio(new InformacionEspacio("1","Cafeteria","Ada Byron"),false);
        idEspacio1 = espacio.getId();
        espacioRep.save(espacio);

        espacio = new Espacio(new InformacionEspacio("2","Lab","Ada Byron"),true);
        idEspacio2 = espacio.getId();
        espacioRep.save(espacio);

    }

    @After
    public void borrar(){
        reservaRep.deleteAll();
        espacioRep.delete(idEspacio1);
        espacioRep.delete(idEspacio2);
    }



    @Test
    public void crearReserva() throws Exception {

        long initSize = reservaRep.count();


        //Crear reserva correctamente
        ResponseEntity response = (ResponseEntity) endpoint.crearReserva(new ReservaDTO("pepe@unizar.es","10/05/2017","10:00",idEspacio2));
        assertEquals(response.getStatusCode(),HttpStatus.OK);
        assertEquals(initSize + 1 , reservaRep.count());

        //Crear reserva en una fecha ocupada
        response = (ResponseEntity) endpoint.crearReserva(new ReservaDTO("pepe@unizar.es","10/05/2017","10:00",idEspacio2));
        assertEquals(response.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(initSize + 1 , reservaRep.count());


        //Crear reserva con un correo que no es de la universidad
        response = (ResponseEntity) endpoint.crearReserva(new ReservaDTO("pepe@gmail.com","10/05/2017","10:00",idEspacio2));
        assertEquals(response.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(initSize + 1 , reservaRep.count());


        //Crear reserva con un correo de un alumno
        response = (ResponseEntity) endpoint.crearReserva(new ReservaDTO("123456@unizar.es","10/05/2017","10:00",idEspacio2));
        assertEquals(response.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(initSize + 1 , reservaRep.count());



        //Crear reserva en un espacio no reservable
        response = (ResponseEntity) endpoint.crearReserva(new ReservaDTO("pepe@unizar.es","10/05/2017","10:00",idEspacio1));
        assertEquals(response.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(initSize + 1 , reservaRep.count());



        //Crear reserva en un espacio que no existe
        response = (ResponseEntity) endpoint.crearReserva(new ReservaDTO("pepe@unizar.es","10/05/2017","10:00","4"));
        assertEquals(response.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(initSize + 1 , reservaRep.count());



    }

}
