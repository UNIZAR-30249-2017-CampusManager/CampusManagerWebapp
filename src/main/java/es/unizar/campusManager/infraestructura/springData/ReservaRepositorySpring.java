package es.unizar.campusManager.infraestructura.springData;

import es.unizar.campusManager.dominio.entidades.Reserva;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReservaRepositorySpring extends CrudRepository<Reserva, String> {

    List<Reserva> findByFecha (String fecha);

}
