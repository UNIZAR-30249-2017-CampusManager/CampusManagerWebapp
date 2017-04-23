package es.unizar.campusManager.infraestructura.springData;

import es.unizar.campusManager.dominio.entidades.EspacioReservable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EspacioReservableRepositorySpring extends CrudRepository<EspacioReservable, String> {

    List<EspacioReservable> findAll();

    EspacioReservable findById(String id);
}
