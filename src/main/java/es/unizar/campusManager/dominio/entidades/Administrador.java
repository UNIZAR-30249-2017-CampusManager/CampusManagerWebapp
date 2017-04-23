package es.unizar.campusManager.dominio.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Administrador extends Entidad {

    @Id
    private String id;
    private String email;
    private String password;

    public Administrador(){}

    public Administrador(String email, String password){
        super();
        this.id = super.getId();
        this.email = email;
        this.password = password;
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

}
