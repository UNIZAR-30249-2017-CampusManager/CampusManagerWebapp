package es.unizar.campusManager.infraestructura.repository;


import es.unizar.campusManager.dominio.entidades.Edificio;
import es.unizar.campusManager.dominio.repository.EdificioRepository;
import es.unizar.campusManager.infraestructura.springData.EdificioRepositorySpring;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EdificioRepositoryImp implements EdificioRepository {

    @Autowired
    private EdificioRepositorySpring edificioRepositorySpring;

    @Override
    public Edificio findByName(String nombre) {
        return edificioRepositorySpring.findByNombre(nombre);
    }

    @Override
    public List<Edificio> findAll() {
        return edificioRepositorySpring.findAll();
    }

    @Override
    public void update(Edificio edificio) {
        edificioRepositorySpring.save(edificio);
    }
}
