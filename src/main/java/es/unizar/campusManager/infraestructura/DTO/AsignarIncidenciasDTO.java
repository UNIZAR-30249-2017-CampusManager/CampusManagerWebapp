package es.unizar.campusManager.infraestructura.DTO;


import java.util.List;

public class AsignarIncidenciasDTO {

    private String emailTrabajador;
    private List<String> idsIncidencias;

    public AsignarIncidenciasDTO() {
    }

    public AsignarIncidenciasDTO(String emailTrabajador, List<String> idsIncidencias) {
        this.emailTrabajador = emailTrabajador;
        this.idsIncidencias = idsIncidencias;
    }

    public String getEmailTrabajador() {
        return emailTrabajador;
    }

    public List<String> getIdsIncidencias() {
        return idsIncidencias;
    }
}
