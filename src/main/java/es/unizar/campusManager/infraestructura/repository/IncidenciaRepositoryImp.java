package es.unizar.campusManager.infraestructura.repository;

import es.unizar.campusManager.dominio.entidades.Incidencia;
import es.unizar.campusManager.dominio.objetosValor.Coordenadas;
import es.unizar.campusManager.dominio.objetosValor.Espacio;
import es.unizar.campusManager.dominio.objetosValor.Ubicacion;
import es.unizar.campusManager.dominio.repository.IncidenciaRepository;
import es.unizar.campusManager.infraestructura.springData.IncidenciaRepositorySpring;

import es.unizar.campusManager.infraestructura.springDataEntities.IncidenciaJPA;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class IncidenciaRepositoryImp implements IncidenciaRepository {

    @Autowired
    private IncidenciaRepositorySpring incidenciaRepositorySpring;

    @Override
    public List<Incidencia> obtenerTodasIncidencias() {

        ArrayList<IncidenciaJPA> incidenciaJPAS = (ArrayList<IncidenciaJPA>) incidenciaRepositorySpring.findAll();
        List<Incidencia> devolver = new ArrayList<>();

        for(IncidenciaJPA incidenciaJPA : incidenciaJPAS){
            devolver.add(new Incidencia(incidenciaJPA.getId(),incidenciaJPA.getNombre(),incidenciaJPA.getDescripcion(),
                    incidenciaJPA.getEmailTrabajador(),incidenciaJPA.getFecha(),incidenciaJPA.getEstado(),
                    incidenciaJPA.getGrupo(),new Espacio(incidenciaJPA.getIdUtc(),incidenciaJPA.getNombreEspacio(),
                    incidenciaJPA.getEsReservable(), new Ubicacion(incidenciaJPA.getPlanta(),
                    incidenciaJPA.getNombreEdificio(),new Coordenadas(incidenciaJPA.getX(),incidenciaJPA.getY(),
                    incidenciaJPA.getSistemaReferenciaCoordenadas())))));
        }

        return devolver;
    }

    @Override
    public List<Incidencia> obtenerIncidenciasTrabajador(String emailTrabajador) {
        ArrayList<IncidenciaJPA> incidenciaJPAS = (ArrayList<IncidenciaJPA>) incidenciaRepositorySpring.findByEmailTrabajador(emailTrabajador);
        List<Incidencia> devolver = new ArrayList<>();

        for(IncidenciaJPA incidenciaJPA : incidenciaJPAS){
            devolver.add(new Incidencia(incidenciaJPA.getId(),incidenciaJPA.getNombre(),incidenciaJPA.getDescripcion(),
                    incidenciaJPA.getEmailTrabajador(),incidenciaJPA.getFecha(),incidenciaJPA.getEstado(),
                    incidenciaJPA.getGrupo(),new Espacio(incidenciaJPA.getIdUtc(),incidenciaJPA.getNombreEspacio(),
                    incidenciaJPA.getEsReservable(), new Ubicacion(incidenciaJPA.getPlanta(),
                    incidenciaJPA.getNombreEdificio(),new Coordenadas(incidenciaJPA.getX(),incidenciaJPA.getY(),
                    incidenciaJPA.getSistemaReferenciaCoordenadas())))));
        }

        return devolver;
    }

    @Override
    public Incidencia obtenerIncidenciaId(String id) {
        IncidenciaJPA incidenciaJPA = incidenciaRepositorySpring.findById(id);

        if(incidenciaJPA == null) return null;

        return new Incidencia(incidenciaJPA.getId(),incidenciaJPA.getNombre(),incidenciaJPA.getDescripcion(),
                incidenciaJPA.getEmailTrabajador(),incidenciaJPA.getFecha(),incidenciaJPA.getEstado(),
                incidenciaJPA.getGrupo(),new Espacio(incidenciaJPA.getIdUtc(),incidenciaJPA.getNombreEspacio(),
                incidenciaJPA.getEsReservable(), new Ubicacion(incidenciaJPA.getPlanta(),
                incidenciaJPA.getNombreEdificio(),new Coordenadas(incidenciaJPA.getX(),incidenciaJPA.getY(),
                incidenciaJPA.getSistemaReferenciaCoordenadas()))));
    }

    @Override
    public List<Incidencia> obtenerIncidenciasGrupo(Integer grupo) {
        ArrayList<IncidenciaJPA> incidenciaJPAS = (ArrayList<IncidenciaJPA>) incidenciaRepositorySpring.findByGrupo(grupo);
        List<Incidencia> devolver = new ArrayList<>();

        for(IncidenciaJPA incidenciaJPA : incidenciaJPAS){
            devolver.add(new Incidencia(incidenciaJPA.getId(),incidenciaJPA.getNombre(),incidenciaJPA.getDescripcion(),
                    incidenciaJPA.getEmailTrabajador(),incidenciaJPA.getFecha(),incidenciaJPA.getEstado(),
                    incidenciaJPA.getGrupo(),new Espacio(incidenciaJPA.getIdUtc(),incidenciaJPA.getNombreEspacio(),
                    incidenciaJPA.getEsReservable(), new Ubicacion(incidenciaJPA.getPlanta(),
                    incidenciaJPA.getNombreEdificio(),new Coordenadas(incidenciaJPA.getX(),incidenciaJPA.getY(),
                    incidenciaJPA.getSistemaReferenciaCoordenadas())))));
        }

        return devolver;
    }

    @Override
    public boolean crearIncidencia(String nombre, String descripcion, String fecha, Espacio espacio) {
        Incidencia incidencia = new Incidencia(nombre,descripcion,fecha,espacio);

        IncidenciaJPA incidenciaJPA = new IncidenciaJPA(incidencia.getId(),incidencia.getNombre(),
                incidencia.getDescripcion(),incidencia.getEmailTrabajador(),incidencia.getFecha(),incidencia.getEstado(),
                incidencia.getGrupo(),incidencia.getEspacio().getIdUtc(),incidencia.getEspacio().getNombre(),
                incidencia.getEspacio().isEsReservable(),incidencia.getEspacio().getUbicacion().getPlanta(),
                incidencia.getEspacio().getUbicacion().getNombreEdificio(),
                incidencia.getEspacio().getUbicacion().getCoordenadas().getX(),
                incidencia.getEspacio().getUbicacion().getCoordenadas().getY(),
                incidencia.getEspacio().getUbicacion().getCoordenadas().getSistemaReferenciaCoordenadas());

        incidenciaRepositorySpring.save(incidenciaJPA);

        return true;
    }

    @Override
    public void actualizarIncidencia(Incidencia incidencia) {
        IncidenciaJPA incidenciaJPA = new IncidenciaJPA(incidencia.getId(),incidencia.getNombre(),
                incidencia.getDescripcion(),incidencia.getEmailTrabajador(),incidencia.getFecha(),incidencia.getEstado(),
                incidencia.getGrupo(),incidencia.getEspacio().getIdUtc(),incidencia.getEspacio().getNombre(),
                incidencia.getEspacio().isEsReservable(),incidencia.getEspacio().getUbicacion().getPlanta(),
                incidencia.getEspacio().getUbicacion().getNombreEdificio(),
                incidencia.getEspacio().getUbicacion().getCoordenadas().getX(),
                incidencia.getEspacio().getUbicacion().getCoordenadas().getY(),
                incidencia.getEspacio().getUbicacion().getCoordenadas().getSistemaReferenciaCoordenadas());

        incidenciaRepositorySpring.save(incidenciaJPA);
    }
}
