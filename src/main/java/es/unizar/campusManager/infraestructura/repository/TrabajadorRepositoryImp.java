package es.unizar.campusManager.infraestructura.repository;

import es.unizar.campusManager.dominio.entidades.Trabajador;
import es.unizar.campusManager.dominio.repository.TrabajadorRepository;
import es.unizar.campusManager.infraestructura.springData.TrabajadorRepositorySpring;
import es.unizar.campusManager.infraestructura.springDataEntities.UsuarioJPA;
import org.springframework.beans.factory.annotation.Autowired;

public class TrabajadorRepositoryImp implements TrabajadorRepository {

    @Autowired
    private TrabajadorRepositorySpring trabajadorRepositorySpring;

    @Override
    public Trabajador encontrarTrabajador(String email) {
        UsuarioJPA usuarioJPA = trabajadorRepositorySpring.findByEmail(email);

        if(usuarioJPA == null || usuarioJPA.getRol().equals("administrador")){
            return null;
        }
        else{
            return new Trabajador(usuarioJPA.getId(),usuarioJPA.getEmail(),usuarioJPA.getPassword());
        }
    }

    @Override
    public boolean crearTrabajador(String email, String password) {
        Trabajador administrador = new Trabajador(email,password);

        trabajadorRepositorySpring.save(new UsuarioJPA(administrador.getId(),administrador.getEmail(),
                administrador.getPassword(),"trabajador"));

        return true;
    }
}
