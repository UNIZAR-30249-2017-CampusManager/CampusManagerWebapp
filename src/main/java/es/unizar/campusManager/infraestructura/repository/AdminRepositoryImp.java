package es.unizar.campusManager.infraestructura.repository;

import es.unizar.campusManager.dominio.entidades.Administrador;
import es.unizar.campusManager.dominio.repository.AdminRepository;
import es.unizar.campusManager.infraestructura.springData.AdminRepositorySpring;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminRepositoryImp implements AdminRepository {

    @Autowired
    private AdminRepositorySpring adminRepositorySpring;

    @Override
    public Administrador findByEmail(String email) {

        Administrador administrador = adminRepositorySpring.findByEmail(email);

        if(administrador == null){
            return null;
        }
        else{
            return administrador;
        }
    }

    @Override
    public boolean save(Administrador administrador) {

        Administrador administradorResult = adminRepositorySpring.save(administrador);

        return administradorResult != null;
    }

    @Override
    public long count() {
        return adminRepositorySpring.count();
    }

    @Override
    public void delete(String email) {
        Administrador admin = findByEmail(email);
        if(admin != null){
            adminRepositorySpring.delete(admin);
        }
    }







}
