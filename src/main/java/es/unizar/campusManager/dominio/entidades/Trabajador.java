package es.unizar.campusManager.dominio.entidades;

public class Trabajador extends Entidad{

    private String id;
    private String email;
    private String password;

    public Trabajador(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Trabajador(String email, String password){
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
