package es.unizar.campusManager.dominio.repository;


import es.unizar.campusManager.dominio.entidades.Trabajador;

public interface TrabajadorRepository {

    Trabajador findByEmail (String email);

    boolean save (Trabajador trabajador);

}
