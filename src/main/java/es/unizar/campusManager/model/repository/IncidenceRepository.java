package es.unizar.campusManager.model.repository;

import es.unizar.campusManager.model.CampusIncidence;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IncidenceRepository extends CrudRepository<CampusIncidence,String> {

    public List<CampusIncidence> findAll();
    public CampusIncidence findById(int id);
    public List<CampusIncidence> findByWorkerEmail(String workerEmail);
    public List<CampusIncidence> findByStatus(String status);
}
