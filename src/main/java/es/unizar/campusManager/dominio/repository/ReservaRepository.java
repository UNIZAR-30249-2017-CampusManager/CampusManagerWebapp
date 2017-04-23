package es.unizar.campusManager.dominio.repository;

import es.unizar.campusManager.dominio.entidades.Reserva;

import java.util.List;

public interface ReservaRepository {

    List<Reserva> findByDate(String date);

    boolean save(Reserva reserva);

    boolean delete(Reserva reserva);

}
