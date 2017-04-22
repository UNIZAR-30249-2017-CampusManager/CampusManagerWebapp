package es.unizar.campusManager.dominio.entidades;

public class Administrador extends Entidad {

    private String id;
    private String email;
    private String password;

    public Administrador(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

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
