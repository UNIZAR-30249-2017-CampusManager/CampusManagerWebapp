package es.unizar.campusManager.infraestructura.springData;

import es.unizar.campusManager.infraestructura.springDataEntities.IncidenciaJPA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IncidenciaRepositorySpring extends CrudRepository<IncidenciaJPA, String> {

    List<IncidenciaJPA> findAll();
    List<IncidenciaJPA> findByEmailTrabajador(String emailTrabajador);


}
