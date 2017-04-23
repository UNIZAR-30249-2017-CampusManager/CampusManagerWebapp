package es.unizar.campusManager.infraestructura.repository;

import es.unizar.campusManager.dominio.entidades.Trabajador;
import es.unizar.campusManager.dominio.repository.TrabajadorRepository;
import es.unizar.campusManager.infraestructura.springData.TrabajadorRepositorySpring;
import org.springframework.beans.factory.annotation.Autowired;

public class TrabajadorRepositoryImp implements TrabajadorRepository {

    @Autowired
    private TrabajadorRepositorySpring trabajadorRepositorySpring;

    @Override
    public Trabajador findByEmail(String email) {
        Trabajador trabajador = trabajadorRepositorySpring.findByEmail(email);

        if(trabajador == null){
            return null;
        }
        else{
            return trabajador;
        }
    }

    @Override
    public boolean save(Trabajador trabajador) {

        Trabajador trabajadorResult =  trabajadorRepositorySpring.save(trabajador);

        return trabajadorResult != null;
    }
}
