package es.unizar.campusManager.dominio.repository;

import es.unizar.campusManager.dominio.entidades.Incidencia;

import java.util.List;

public interface IncidenciaRepository {

    List<Incidencia> findAll();

    List<Incidencia> findByWorkerEmail(String emailTrabajador);

    List<Incidencia> findByGroup(Integer grupo);

    Incidencia findById(String id);

    boolean save(Incidencia incidencia);

    void update(Incidencia incidencia);

}
