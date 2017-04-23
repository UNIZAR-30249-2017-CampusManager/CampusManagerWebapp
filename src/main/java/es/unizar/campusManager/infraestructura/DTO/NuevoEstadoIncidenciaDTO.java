package es.unizar.campusManager.infraestructura.DTO;

public class NuevoEstadoIncidenciaDTO {

    private String estado;

    public NuevoEstadoIncidenciaDTO() {
    }

    public NuevoEstadoIncidenciaDTO(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
