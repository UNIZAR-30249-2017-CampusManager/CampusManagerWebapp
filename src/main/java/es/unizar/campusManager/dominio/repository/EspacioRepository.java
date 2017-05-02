package es.unizar.campusManager.dominio.repository;

import es.unizar.campusManager.dominio.entidades.Espacio;

import java.util.List;

public interface EspacioRepository {

    List<Espacio> findAll();

    Espacio findById(String id);

    Espacio findByIdEspacio(String idEspacio);
}
