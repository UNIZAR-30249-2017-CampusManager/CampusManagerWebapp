package es.unizar.campusManager.dominio.repository;

import es.unizar.campusManager.dominio.entidades.Edificio;

import java.util.List;

public interface EdificioRepository {

    Edificio findByName(String name);

    List<Edificio> findAll();

    void update(Edificio edificio);

}
