package es.unizar.campusManager.dominio.repository;


import es.unizar.campusManager.dominio.entidades.Administrador;

public interface AdminRepository {

    Administrador encontrarAdmin(String email);

    boolean crearAdmin(String email, String password);

}
