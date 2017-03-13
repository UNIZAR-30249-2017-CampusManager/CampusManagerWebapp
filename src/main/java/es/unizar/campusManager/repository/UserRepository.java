package es.unizar.campusManager.repository;

import es.unizar.campusManager.model.*;
/**
 * Created by jorge on 12/03/17.
 */
public interface UserRepository {

    public User findByEmail(String email);
    public User save(User u);

}
