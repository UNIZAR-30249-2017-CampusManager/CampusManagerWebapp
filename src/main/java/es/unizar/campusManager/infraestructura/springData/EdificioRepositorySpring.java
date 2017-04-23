package es.unizar.campusManager.infraestructura.springData;

import es.unizar.campusManager.dominio.entidades.Edificio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EdificioRepositorySpring extends CrudRepository<Edificio, String> {

    List<Edificio> findAll();
    Edificio findByNombre(String nombre);

}
