package es.unizar.campusManager.infraestructura.springDataEntities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UsuarioJPA {

    @Id
    private String id;

    private String email;
    private String password;
    private String rol;

    public UsuarioJPA(){}

    public UsuarioJPA(String id, String email, String password, String rol) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return rol;
    }
}
