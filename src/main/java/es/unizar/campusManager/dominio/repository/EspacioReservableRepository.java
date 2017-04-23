package es.unizar.campusManager.dominio.repository;

import es.unizar.campusManager.dominio.entidades.EspacioReservable;

import java.util.List;

public interface EspacioReservableRepository {

    List<EspacioReservable> findAll();

    EspacioReservable findById(String id);
}
