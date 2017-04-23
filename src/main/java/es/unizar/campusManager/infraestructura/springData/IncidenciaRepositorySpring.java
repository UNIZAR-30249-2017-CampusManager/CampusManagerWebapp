package es.unizar.campusManager.infraestructura.springData;

import es.unizar.campusManager.dominio.entidades.Incidencia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IncidenciaRepositorySpring extends CrudRepository<Incidencia, String> {

    List<Incidencia> findAll();
    List<Incidencia> findByEmailTrabajador(String emailTrabajador);
    List<Incidencia> findByGrupo(Integer grupo);
    Incidencia findById(String id);

}
