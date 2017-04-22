package es.unizar.campusManager.infraestructura.DTO;

public class AdministradorDTO {

    private String emailAdministrador;
    private String passwordAdministrador;
    private String emailAdmin;
    private String passwordAdmin;

    public AdministradorDTO(){}

    public AdministradorDTO(String emailAdministrador, String passwordAdministrador, String emailAdmin, String passwordAdmin) {
        this.emailAdministrador = emailAdministrador;
        this.passwordAdministrador = passwordAdministrador;
        this.emailAdmin = emailAdmin;
        this.passwordAdmin = passwordAdmin;
    }

    public String getEmailAdministrador() {
        return emailAdministrador;
    }

    public String getPasswordAdministrador() {
        return passwordAdministrador;
    }

    public String getEmailAdmin() {
        return emailAdmin;
    }

    public String getPasswordAdmin() {
        return passwordAdmin;
    }
}
