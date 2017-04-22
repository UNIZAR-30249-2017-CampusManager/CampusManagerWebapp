package es.unizar.campusManager.dominio.repository;


import es.unizar.campusManager.dominio.entidades.Trabajador;

public interface TrabajadorRepository {

    Trabajador encontrarTrabajador (String email);

    boolean crearTrabajador (String email, String password);

}
