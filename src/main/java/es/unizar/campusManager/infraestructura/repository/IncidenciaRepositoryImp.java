package es.unizar.campusManager.infraestructura.repository;

import es.unizar.campusManager.dominio.entidades.Incidencia;
import es.unizar.campusManager.dominio.repository.IncidenciaRepository;
import es.unizar.campusManager.infraestructura.springData.IncidenciaRepositorySpring;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class IncidenciaRepositoryImp implements IncidenciaRepository {

    @Autowired
    private IncidenciaRepositorySpring incidenciaRepositorySpring;

    @Override
    public List<Incidencia> findAll() {
        return incidenciaRepositorySpring.findAll();
    }

    @Override
    public List<Incidencia> findByWorkerEmail(String emailTrabajador) {
        return incidenciaRepositorySpring.findByEmailTrabajador(emailTrabajador);
    }

    @Override
    public List<Incidencia> findByGroup(Integer grupo) {
        return incidenciaRepositorySpring.findByGrupo(grupo);
    }

    @Override
    public Incidencia findById(String id) {
        return incidenciaRepositorySpring.findById(id);
    }

    @Override
    public boolean save(Incidencia incidencia) {
        Incidencia incidenciaResult = incidenciaRepositorySpring.save(incidencia);

        return incidenciaResult != null;
    }

    @Override
    public void update(Incidencia incidencia) {
        Incidencia incidenciaResult = incidenciaRepositorySpring.save(incidencia);
    }
}
