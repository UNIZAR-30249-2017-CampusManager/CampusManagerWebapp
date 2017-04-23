package es.unizar.campusManager.infraestructura.repository;

import es.unizar.campusManager.dominio.entidades.EspacioReservable;
import es.unizar.campusManager.dominio.repository.EspacioReservableRepository;
import es.unizar.campusManager.infraestructura.springData.EspacioReservableRepositorySpring;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EspacioReservableRepositoryImp implements EspacioReservableRepository {

    @Autowired
    private EspacioReservableRepositorySpring espacioReservableRepositorySpring;


    @Override
    public List<EspacioReservable> findAll() {
        return espacioReservableRepositorySpring.findAll();
    }

    @Override
    public EspacioReservable findById(String id) {
        return espacioReservableRepositorySpring.findById(id);
    }
}
