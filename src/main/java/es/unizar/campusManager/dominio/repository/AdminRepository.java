package es.unizar.campusManager.dominio.repository;


import es.unizar.campusManager.dominio.entidades.Administrador;

public interface AdminRepository {

    Administrador findByEmail(String email);

    boolean save(Administrador administrador);

    long count();

    void delete(String email);


}
