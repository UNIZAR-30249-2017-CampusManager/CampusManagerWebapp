package es.unizar.campusManager.infraestructura.springData;

import es.unizar.campusManager.dominio.entidades.Trabajador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrabajadorRepositorySpring extends CrudRepository<Trabajador, String> {

    Trabajador findByEmail(String email);

}
