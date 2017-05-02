package es.unizar.campusManager.infraestructura.springData;

import es.unizar.campusManager.dominio.entidades.Espacio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EspacioRepositorySpring extends CrudRepository<Espacio, String> {

    List<Espacio> findAll();

    Espacio findById(String id);
}
