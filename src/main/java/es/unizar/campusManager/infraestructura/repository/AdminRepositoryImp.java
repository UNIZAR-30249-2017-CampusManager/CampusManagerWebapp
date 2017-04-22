package es.unizar.campusManager.infraestructura.repository;

import es.unizar.campusManager.dominio.entidades.Administrador;
import es.unizar.campusManager.dominio.repository.AdminRepository;
import es.unizar.campusManager.infraestructura.springData.AdminRepositorySpring;
import es.unizar.campusManager.infraestructura.springDataEntities.UsuarioJPA;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminRepositoryImp implements AdminRepository {

    @Autowired
    private AdminRepositorySpring adminRepositorySpring;

    @Override
    public Administrador encontrarAdmin(String email) {

        UsuarioJPA usuarioJPA = adminRepositorySpring.findByEmail(email);

        if(usuarioJPA == null || usuarioJPA.getRol().equals("trabajador")){
            return null;
        }
        else{
            return new Administrador(usuarioJPA.getId(),usuarioJPA.getEmail(),usuarioJPA.getPassword());
        }
    }

    @Override
    public boolean crearAdmin(String email, String password) {
        Administrador administrador = new Administrador(email,password);

        adminRepositorySpring.save(new UsuarioJPA(administrador.getId(),administrador.getEmail(),
                administrador.getPassword(),"administrador"));

        return true;
    }
}
