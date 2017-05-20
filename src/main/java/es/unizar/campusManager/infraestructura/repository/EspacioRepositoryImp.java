package es.unizar.campusManager.infraestructura.repository;

import es.unizar.campusManager.dominio.entidades.Espacio;
import es.unizar.campusManager.dominio.repository.EspacioRepository;
import es.unizar.campusManager.infraestructura.springData.EspacioRepositorySpring;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EspacioRepositoryImp implements EspacioRepository {

    @Autowired
    private EspacioRepositorySpring espacioRepositorySpring;


    @Override
    public List<Espacio> findAll() {
        return espacioRepositorySpring.findAll();
    }

    @Override
    public Espacio findById(String id) {
        return espacioRepositorySpring.findById(id);
    }

    @Override
    public Espacio findByIdEspacio(String idEspacio) {

        List<Espacio> espacios = espacioRepositorySpring.findAll();

        Espacio result = null;

        for(Espacio espacio : espacios){
            if(espacio!= null && espacio.getInformacionEspacio().getIdEspacio().equals(idEspacio)){
                result = espacio;
                break;
            }
        }

        return result;
    }

    @Override
    public void update(Espacio espacio) {
        espacioRepositorySpring.save(espacio);
    }
}
