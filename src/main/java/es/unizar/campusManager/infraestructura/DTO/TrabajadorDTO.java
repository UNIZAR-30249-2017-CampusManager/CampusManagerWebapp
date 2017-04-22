package es.unizar.campusManager.infraestructura.DTO;

public class TrabajadorDTO {

    private String emailTrabajador;
    private String passwordTrabajador;
    private String emailAdmin;
    private String passwordAdmin;

    public TrabajadorDTO(){}

    public TrabajadorDTO(String emailTrabajador, String passwordTrabajador, String emailAdmin, String passwordAdmin) {
        this.emailTrabajador = emailTrabajador;
        this.passwordTrabajador = passwordTrabajador;
        this.emailAdmin = emailAdmin;
        this.passwordAdmin = passwordAdmin;
    }

    public String getEmailTrabajador() {
        return emailTrabajador;
    }

    public String getPasswordTrabajador() {
        return passwordTrabajador;
    }

    public String getEmailAdmin() {
        return emailAdmin;
    }

    public String getPasswordAdmin() {
        return passwordAdmin;
    }
}
