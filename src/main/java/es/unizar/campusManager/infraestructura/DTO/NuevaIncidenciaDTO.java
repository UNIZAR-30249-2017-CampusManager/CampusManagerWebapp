package es.unizar.campusManager.infraestructura.DTO;

public class NuevaIncidenciaDTO {

    private String nombre;
    private String descripcion;
    private String idEspacio;
    private String fechaLimite;

    public NuevaIncidenciaDTO(){}

    public NuevaIncidenciaDTO(String nombre, String descripcion, String idEspacio, String fechaLimite) {
        this.idEspacio = idEspacio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
    }

    public String getIdEspacio() {
        return idEspacio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }
}
