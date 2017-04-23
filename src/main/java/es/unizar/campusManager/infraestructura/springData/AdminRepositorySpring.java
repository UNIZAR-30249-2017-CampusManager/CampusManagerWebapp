package es.unizar.campusManager.infraestructura.springData;

import es.unizar.campusManager.dominio.entidades.Administrador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepositorySpring extends CrudRepository<Administrador, String> {

    Administrador findByEmail(String email);

}
