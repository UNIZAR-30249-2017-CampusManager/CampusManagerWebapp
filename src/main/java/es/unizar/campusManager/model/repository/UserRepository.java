package es.unizar.campusManager.model.repository;

import es.unizar.campusManager.model.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<CampusUser,String> {

    public CampusUser findByEmail(String email);
    public List<CampusUser> findAll();

}
