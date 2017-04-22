package es.unizar.campusManager.infraestructura.springData;

import es.unizar.campusManager.infraestructura.springDataEntities.UsuarioJPA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepositorySpring extends CrudRepository<UsuarioJPA, String> {

    UsuarioJPA findByEmail(String email);

}
