package es.unizar.campusManager.model.repository;

import es.unizar.campusManager.model.*;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<CampusUser,String> {

    public CampusUser findByEmail(String email);


}
