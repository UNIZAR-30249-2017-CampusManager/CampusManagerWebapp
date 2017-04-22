package es.unizar.campusManager.dominio.repository;

import es.unizar.campusManager.dominio.entidades.Incidencia;
import es.unizar.campusManager.dominio.objetosValor.Espacio;

import java.util.List;

public interface IncidenciaRepository {

    List<Incidencia> obtenerTodasIncidencias();

    List<Incidencia> obtenerIncidenciasTrabajador(String emailTrabajador);

    boolean crearIncidencia(String nombre, String descripcion, String fecha, Espacio espacio);

}
