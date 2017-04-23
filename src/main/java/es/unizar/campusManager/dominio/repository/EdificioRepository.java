package es.unizar.campusManager.dominio.repository;

import es.unizar.campusManager.dominio.entidades.Edificio;

public interface EdificioRepository {

    Edificio findByName(String name);

    void update(Edificio edificio);

}
